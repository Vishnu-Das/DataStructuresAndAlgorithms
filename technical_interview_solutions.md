# Technical Interview Solutions - Detailed Analysis (Java Implementation)

## Overview
This document provides comprehensive Java implementations for all technical interview rounds. Each solution includes:
- Clean, production-ready Java code
- Detailed time/space complexity analysis
- Explanation of key design decisions
- Follow-up implementations

## Key Java Features Utilized
- **Collections Framework**: HashMap, HashSet, ArrayList, LinkedList
- **Generics**: Type-safe collections with proper generic declarations
- **Lambda Expressions**: Modern sorting with custom comparators
- **Exception Handling**: Proper try-catch blocks for error scenarios
- **Stream API**: Where applicable for clean, functional programming style

## Round 1 - DSA: Music Player like Spotify

### Problem Statement
Design a Music Player like Spotify with the following methods:
- `int addSong(string songTitle)` - add a song with incremental IDs starting from 1
- `void playSong(int songId, int userId)` - user plays a song
- `void printMostPlayedSongs()` - print songs in decreasing order of unique users' plays
- **Follow-up**: `vector<int> getLastThreeSongs(int userId)` - get last 3 unique songs played by a user

### Solution Approach

**Key Data Structures:**
1. `unordered_map<int, string> songs` - songId to songTitle mapping
2. `unordered_map<int, unordered_set<int>> songPlays` - songId to set of unique userIds
3. `unordered_map<int, vector<int>> userHistory` - userId to list of played songIds

### Implementation

```java
import java.util.*;

class MusicPlayer {
    private Map<Integer, String> songs;                     // songId -> songTitle
    private Map<Integer, Set<Integer>> songPlays;           // songId -> set of unique userIds
    private Map<Integer, List<Integer>> userHistory;        // userId -> list of songIds played
    private int nextSongId;
    
    public MusicPlayer() {
        this.songs = new HashMap<>();
        this.songPlays = new HashMap<>();
        this.userHistory = new HashMap<>();
        this.nextSongId = 1;
    }
    
    public int addSong(String songTitle) {
        songs.put(nextSongId, songTitle);
        return nextSongId++;
    }
    
    public void playSong(int songId, int userId) {
        if (!songs.containsKey(songId)) {
            return; // Song doesn't exist
        }
        
        // Add user to the song's play set
        songPlays.computeIfAbsent(songId, k -> new HashSet<>()).add(userId);
        
        // Add song to user's history
        userHistory.computeIfAbsent(userId, k -> new ArrayList<>()).add(songId);
    }
    
    public void printMostPlayedSongs() {
        List<int[]> songPlayCounts = new ArrayList<>(); // {playCount, songId}
        
        for (Map.Entry<Integer, Set<Integer>> entry : songPlays.entrySet()) {
            int songId = entry.getKey();
            int uniquePlayCount = entry.getValue().size();
            songPlayCounts.add(new int[]{uniquePlayCount, songId});
        }
        
        // Sort by play count (descending), then by songId (ascending) for tie-breaking
        songPlayCounts.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(b[0], a[0]);
            return Integer.compare(a[1], b[1]);
        });
        
        for (int[] entry : songPlayCounts) {
            System.out.println(songs.get(entry[1]) + " (" + entry[0] + " unique plays)");
        }
    }
    
    public List<Integer> getLastThreeSongs(int userId) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        
        if (!userHistory.containsKey(userId)) {
            return result;
        }
        
        List<Integer> history = userHistory.get(userId);
        
        // Traverse from the end to get last 3 unique songs
        for (int i = history.size() - 1; i >= 0 && result.size() < 3; i--) {
            int songId = history.get(i);
            if (!seen.contains(songId)) {
                seen.add(songId);
                result.add(songId);
            }
        }
        
        return result;
    }
}
```

### Time Complexities:
- `addSong()`: O(1)
- `playSong()`: O(1) average
- `printMostPlayedSongs()`: O(n log n) where n is number of unique songs played
- `getLastThreeSongs()`: O(h) where h is the history length for the user

### Space Complexity: O(S + U*H) where S is songs, U is users, H is average history length

### Example Usage:
```java
public class TestMusicPlayer {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        
        int song1 = player.addSong("Bohemian Rhapsody");
        int song2 = player.addSong("Shape of You");
        int song3 = player.addSong("Blinding Lights");
        
        player.playSong(song1, 101); // User 101 plays song 1
        player.playSong(song2, 101); // User 101 plays song 2
        player.playSong(song1, 102); // User 102 plays song 1
        player.playSong(song1, 103); // User 103 plays song 1
        
        player.printMostPlayedSongs(); // Outputs: Bohemian Rhapsody (3 unique plays)
        
        List<Integer> lastSongs = player.getLastThreeSongs(101); // Returns [2, 1]
    }
}
```

---

## Round 3 - DSA: Key Value Store with Transactions

### Problem Statement
Design a Key Value Store with:
- `string get(string key)`
- `void set(string key, string value)`
- `void deleteKey(string key)`
- **Follow-up 1**: Support transactions (begin, commit, rollback) - single level
- **Follow-up 2**: Support nested transactions

### Solution Approach

**Key Insight**: Use a stack of transaction states. Each transaction maintains its own view of changes.

### Implementation

```java
import java.util.*;

class KeyValueStore {
    private Map<String, String> mainStore;
    private List<Map<String, String>> transactionStack;    // Stack of transaction states
    private List<Set<String>> deletedInTransaction;        // Track deletions per transaction
    
    public KeyValueStore() {
        this.mainStore = new HashMap<>();
        this.transactionStack = new ArrayList<>();
        this.deletedInTransaction = new ArrayList<>();
    }
    
    public String get(String key) {
        // Search from most recent transaction to main store
        for (int i = transactionStack.size() - 1; i >= 0; i--) {
            if (deletedInTransaction.get(i).contains(key)) {
                return null; // Key was deleted in this transaction
            }
            if (transactionStack.get(i).containsKey(key)) {
                return transactionStack.get(i).get(key);
            }
        }
        
        return mainStore.get(key); // Returns null if key not found
    }
    
    public void set(String key, String value) {
        if (transactionStack.isEmpty()) {
            mainStore.put(key, value);
        } else {
            // Set in current transaction
            int currentTransaction = transactionStack.size() - 1;
            transactionStack.get(currentTransaction).put(key, value);
            deletedInTransaction.get(currentTransaction).remove(key); // Remove from deleted set if present
        }
    }
    
    public void deleteKey(String key) {
        if (transactionStack.isEmpty()) {
            mainStore.remove(key);
        } else {
            // Mark as deleted in current transaction
            int currentTransaction = transactionStack.size() - 1;
            deletedInTransaction.get(currentTransaction).add(key);
            transactionStack.get(currentTransaction).remove(key); // Remove from transaction changes
        }
    }
    
    public void begin() {
        transactionStack.add(new HashMap<>());
        deletedInTransaction.add(new HashSet<>());
    }
    
    public void commit() {
        if (transactionStack.isEmpty()) {
            return; // No transaction to commit
        }
        
        Map<String, String> changes = transactionStack.get(transactionStack.size() - 1);
        Set<String> deletions = deletedInTransaction.get(deletedInTransaction.size() - 1);
        
        transactionStack.remove(transactionStack.size() - 1);
        deletedInTransaction.remove(deletedInTransaction.size() - 1);
        
        if (transactionStack.isEmpty()) {
            // Commit to main store
            for (Map.Entry<String, String> entry : changes.entrySet()) {
                mainStore.put(entry.getKey(), entry.getValue());
            }
            for (String key : deletions) {
                mainStore.remove(key);
            }
        } else {
            // Commit to parent transaction
            int parentTransaction = transactionStack.size() - 1;
            for (Map.Entry<String, String> entry : changes.entrySet()) {
                transactionStack.get(parentTransaction).put(entry.getKey(), entry.getValue());
                deletedInTransaction.get(parentTransaction).remove(entry.getKey());
            }
            for (String key : deletions) {
                deletedInTransaction.get(parentTransaction).add(key);
                transactionStack.get(parentTransaction).remove(key);
            }
        }
    }
    
    public void rollback() {
        if (transactionStack.isEmpty()) {
            return; // No transaction to rollback
        }
        
        transactionStack.remove(transactionStack.size() - 1);
        deletedInTransaction.remove(deletedInTransaction.size() - 1);
    }
}
```

### Time Complexities:
- `get()`: O(T) where T is transaction depth
- `set()`: O(1)
- `deleteKey()`: O(1)
- `begin()`: O(1)
- `commit()`: O(C) where C is changes in current transaction
- `rollback()`: O(1)

### Example Usage:
```java
public class TestKeyValueStore {
    public static void main(String[] args) {
        KeyValueStore store = new KeyValueStore();
        
        store.set("name", "John");
        System.out.println(store.get("name")); // Output: John
        
        store.begin(); // Start transaction
        store.set("name", "Jane");
        store.set("age", "25");
        System.out.println(store.get("name")); // Output: Jane
        
        store.rollback(); // Rollback transaction
        System.out.println(store.get("name")); // Output: John
        System.out.println(store.get("age"));  // Output: null
        
        // Nested transactions
        store.begin();
        store.set("city", "NYC");
        store.begin(); // Nested transaction
        store.set("country", "USA");
        store.commit(); // Commit nested
        store.commit(); // Commit outer
        
        System.out.println(store.get("city"));    // Output: NYC
        System.out.println(store.get("country")); // Output: USA
    }
}
```

---

## Round 4 - DSA: Excel Sheet

### Problem Statement
Design an Excel Sheet with:
- `void set(string cell, string value)` - cell can be "A1", "B2", value can be "10" or "=9+10"
- `void reset(string cell)` - reset the cell
- `void print()` - print all cells with raw and computed values
- **Follow-up**: Support values like "=A1+10" where A1 is a cell reference

### Solution Approach

**Key Components:**
1. Cell storage with raw and computed values
2. Expression parser for formulas
3. Dependency tracking for cell references
4. Cycle detection for circular references

### Implementation

```java
import java.util.*;

class ExcelSheet {
    private static class Cell {
        String rawValue;
        double computedValue;
        boolean hasFormula;
        Set<String> dependencies; // Cells this cell depends on
        
        Cell() {
            this.rawValue = "";
            this.computedValue = 0.0;
            this.hasFormula = false;
            this.dependencies = new HashSet<>();
        }
    }
    
    private Map<String, Cell> cells;
    private Map<String, Set<String>> dependents; // Cells that depend on this cell
    
    public ExcelSheet() {
        this.cells = new HashMap<>();
        this.dependents = new HashMap<>();
    }
    
    // Parse cell reference like "A1" to coordinates
    private int[] parseCellRef(String cellRef) {
        int col = cellRef.charAt(0) - 'A';
        int row = Integer.parseInt(cellRef.substring(1)) - 1;
        return new int[]{row, col};
    }
    
    // Check if string is a valid cell reference
    private boolean isCellRef(String token) {
        if (token.length() < 2) return false;
        if (token.charAt(0) < 'A' || token.charAt(0) > 'Z') return false;
        for (int i = 1; i < token.length(); i++) {
            if (!Character.isDigit(token.charAt(i))) return false;
        }
        return true;
    }
    
    // Parse and evaluate expression
    private double evaluateExpression(String expr, String currentCell) throws Exception {
        if (expr.isEmpty() || expr.charAt(0) != '=') {
            return Double.parseDouble(expr);
        }
        
        String formula = expr.substring(1); // Remove '='
        return parseFormula(formula, currentCell);
    }
    
    // Simple expression parser
    private double parseFormula(String formula, String currentCell) throws Exception {
        List<String> tokens = tokenize(formula);
        double result = 0;
        char op = '+';
        
        for (String token : tokens) {
            double value;
            
            if (isCellRef(token)) {
                if (token.equals(currentCell)) {
                    throw new Exception("Circular reference detected");
                }
                
                // Add dependency
                cells.get(currentCell).dependencies.add(token);
                dependents.computeIfAbsent(token, k -> new HashSet<>()).add(currentCell);
                
                // Get value from referenced cell
                if (cells.containsKey(token)) {
                    value = cells.get(token).computedValue;
                } else {
                    value = 0; // Empty cell
                }
            } else if (token.equals("+") || token.equals("-")) {
                op = token.charAt(0);
                continue;
            } else {
                value = Double.parseDouble(token);
            }
            
            if (op == '+') {
                result += value;
            } else if (op == '-') {
                result -= value;
            }
        }
        
        return result;
    }
    
    // Tokenize formula string
    private List<String> tokenize(String formula) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        
        for (char c : formula.toCharArray()) {
            if (c == '+' || c == '-') {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else if (c != ' ') {
                current.append(c);
            }
        }
        
        if (current.length() > 0) {
            tokens.add(current.toString());
        }
        
        return tokens;
    }
    
    // Update all dependent cells
    private void updateDependents(String cellRef) {
        Queue<String> toUpdate = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        toUpdate.offer(cellRef);
        
        while (!toUpdate.isEmpty()) {
            String current = toUpdate.poll();
            
            if (visited.contains(current)) continue;
            visited.add(current);
            
            Set<String> currentDependents = dependents.get(current);
            if (currentDependents != null) {
                for (String dependent : currentDependents) {
                    if (cells.containsKey(dependent)) {
                        try {
                            cells.get(dependent).computedValue = 
                                evaluateExpression(cells.get(dependent).rawValue, dependent);
                            toUpdate.offer(dependent);
                        } catch (Exception e) {
                            cells.get(dependent).computedValue = 0; // Error value
                        }
                    }
                }
            }
        }
    }
    
    public void set(String cell, String value) {
        // Clear old dependencies
        if (cells.containsKey(cell)) {
            for (String dep : cells.get(cell).dependencies) {
                Set<String> depSet = dependents.get(dep);
                if (depSet != null) {
                    depSet.remove(cell);
                }
            }
            cells.get(cell).dependencies.clear();
        } else {
            cells.put(cell, new Cell());
        }
        
        cells.get(cell).rawValue = value;
        cells.get(cell).hasFormula = (value.length() > 0 && value.charAt(0) == '=');
        
        try {
            cells.get(cell).computedValue = evaluateExpression(value, cell);
        } catch (Exception e) {
            cells.get(cell).computedValue = 0; // Error value
        }
        
        // Update all dependent cells
        updateDependents(cell);
    }
    
    public void reset(String cell) {
        if (!cells.containsKey(cell)) return;
        
        // Clear dependencies
        for (String dep : cells.get(cell).dependencies) {
            Set<String> depSet = dependents.get(dep);
            if (depSet != null) {
                depSet.remove(cell);
            }
        }
        
        cells.remove(cell);
        dependents.remove(cell);
        
        // Update dependents with empty value
        updateDependents(cell);
    }
    
    public void print() {
        List<String> sortedCells = new ArrayList<>(cells.keySet());
        Collections.sort(sortedCells);
        
        for (String cellRef : sortedCells) {
            Cell cell = cells.get(cellRef);
            System.out.println(cellRef + ": Raw='" + cell.rawValue + 
                             "', Computed=" + cell.computedValue);
        }
    }
}
```

### Time Complexities:
- `set()`: O(D) where D is number of dependent cells
- `reset()`: O(D)
- `print()`: O(n log n) where n is number of cells

### Example Usage:
```java
public class TestExcelSheet {
    public static void main(String[] args) {
        ExcelSheet sheet = new ExcelSheet();
        
        sheet.set("A1", "10");
        sheet.set("B1", "20");
        sheet.set("C1", "=A1+B1"); // Formula with cell references
        sheet.set("D1", "=C1+5");  // Dependent formula
        
        sheet.print();
        // Output:
        // A1: Raw='10', Computed=10.0
        // B1: Raw='20', Computed=20.0
        // C1: Raw='=A1+B1', Computed=30.0
        // D1: Raw='=C1+5', Computed=35.0
        
        sheet.set("A1", "15"); // Update A1, triggers cascade update
        sheet.print();
        // Output:
        // A1: Raw='15', Computed=15.0
        // B1: Raw='20', Computed=20.0
        // C1: Raw='=A1+B1', Computed=35.0
        // D1: Raw='=C1+5', Computed=40.0
        
        sheet.reset("A1"); // Reset A1, dependents become 0
        sheet.print();
        // C1 and D1 will recalculate with A1 as 0
    }
}
```

---

## Round 5 - System Design: Google News

### High-Level Architecture

```
[Load Balancer] → [API Gateway] → [Microservices]
                                      ↓
[News Aggregation Service] ← → [Content Database]
[User Service] ← → [User Database]
[Recommendation Service] ← → [ML Models]
[Search Service] ← → [Search Index (Elasticsearch)]
[Notification Service] ← → [Message Queue]
```

### Core Components

#### 1. News Aggregation Service
- **RSS Feed Crawlers**: Continuously crawl news sources
- **Content Parser**: Extract and normalize article content
- **Duplicate Detection**: Use content hashing and similarity algorithms
- **Content Quality Filter**: ML models to filter spam/low-quality content

#### 2. Data Storage Strategy

**Content Database (Cassandra/MongoDB)**:
```
Article {
  id: UUID,
  title: string,
  content: string,
  source: string,
  publishedAt: timestamp,
  category: string,
  tags: [string],
  imageUrl: string,
  sentiment: float
}
```

**User Database (PostgreSQL)**:
```
User {
  id: UUID,
  preferences: {
    categories: [string],
    sources: [string],
    languages: [string]
  },
  readHistory: [articleId],
  interactions: [interaction]
}
```

#### 3. Recommendation Engine
- **Collaborative Filtering**: User-based recommendations
- **Content-Based**: Article similarity matching
- **Trending Algorithm**: Real-time trending topic detection
- **Personalization**: ML models using user behavior

#### 4. Search Infrastructure
- **Elasticsearch Cluster**: Full-text search with relevance scoring
- **Auto-complete**: Trie-based suggestion system
- **Faceted Search**: Filter by date, source, category, sentiment

#### 5. Caching Strategy
- **CDN**: Static content and images
- **Redis Cluster**: 
  - Hot articles cache
  - User session data
  - Search results cache
  - Trending topics cache

### Scalability Considerations

#### Horizontal Scaling
- **Microservices**: Independent scaling of components
- **Database Sharding**: Partition by geography/topic
- **Read Replicas**: Multiple read-only database instances

#### Performance Optimizations
- **Lazy Loading**: Load content on-demand
- **Pagination**: Efficient content browsing
- **Content Delivery**: Edge caching worldwide
- **Async Processing**: Background jobs for heavy operations

### Data Pipeline

```
News Sources → Kafka → [Processing Pipeline] → Storage
                ↓
        [Real-time Analytics] → [ML Training] → [Model Updates]
```

#### Real-time Processing (Kafka Streams)
1. **Content Ingestion**: Parallel processing of multiple sources
2. **Deduplication**: Real-time duplicate detection
3. **Classification**: Automatic categorization using ML
4. **Sentiment Analysis**: Real-time sentiment scoring
5. **Trend Detection**: Identify emerging topics

### API Design

```python
# Core APIs
GET /api/v1/articles?category=tech&page=1&limit=20
GET /api/v1/articles/{id}
GET /api/v1/search?q=keyword&filters=...
GET /api/v1/trending
GET /api/v1/personalized-feed/{userId}

# User APIs  
POST /api/v1/users/{userId}/preferences
GET /api/v1/users/{userId}/history
POST /api/v1/users/{userId}/interactions
```

### Advanced Features

#### 1. Real-time Updates
- **WebSocket Connections**: Live news updates
- **Push Notifications**: Breaking news alerts
- **Progressive Loading**: Smooth infinite scroll

#### 2. Content Personalization
- **A/B Testing**: Experiment with recommendation algorithms
- **Feedback Loop**: Learn from user interactions
- **Bias Detection**: Ensure diverse content exposure

#### 3. Analytics & Monitoring
- **User Engagement Metrics**: Click-through rates, time spent
- **Content Performance**: Article popularity tracking
- **System Health**: Service monitoring and alerting

### Security & Compliance

- **Content Moderation**: AI-powered content filtering
- **Rate Limiting**: Prevent API abuse
- **Data Privacy**: GDPR compliance for user data
- **Source Verification**: Combat fake news with source credibility scoring

This comprehensive solution addresses scalability, performance, and user experience requirements for a Google News-like platform while maintaining clean architecture and efficient data flow.

---

## Interview Tips and Key Takeaways

### For DSA Rounds:
1. **Start Simple**: Begin with basic functionality, then add follow-ups
2. **Think About Edge Cases**: Empty inputs, invalid data, boundary conditions
3. **Optimize Incrementally**: Start with working solution, then optimize
4. **Explain Time/Space Complexity**: Always analyze and discuss trade-offs
5. **Use Appropriate Data Structures**: HashMap for O(1) lookups, TreeMap for sorted data
6. **Handle Concurrency**: Mention thread-safety considerations if asked

### For System Design:
1. **Clarify Requirements**: Ask about scale, features, constraints
2. **Start High-Level**: Draw basic architecture first
3. **Deep Dive Gradually**: Focus on core components, then add details
4. **Consider Scalability**: Horizontal scaling, load balancing, caching
5. **Think About Trade-offs**: CAP theorem, consistency vs availability
6. **Real-World Examples**: Reference actual systems (Netflix, Twitter, etc.)

### Common Patterns Used:
- **Observer Pattern**: For dependent cell updates in Excel
- **Command Pattern**: For transaction operations in KV store
- **Strategy Pattern**: For different recommendation algorithms
- **Facade Pattern**: For simplified API interfaces
- **Factory Pattern**: For creating different types of cells/objects

### Performance Optimizations:
- **Lazy Evaluation**: Compute values only when needed
- **Memoization**: Cache expensive computations
- **Batch Operations**: Group multiple operations for efficiency
- **Asynchronous Processing**: Use background threads for heavy operations

### Error Handling Best Practices:
- **Fail Fast**: Validate inputs early
- **Graceful Degradation**: Handle errors without crashing
- **Meaningful Messages**: Provide clear error descriptions
- **Recovery Mechanisms**: Allow system to recover from failures