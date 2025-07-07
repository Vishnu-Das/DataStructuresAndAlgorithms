# Technical Interview Solutions - Detailed Analysis

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

```cpp
class MusicPlayer {
private:
    unordered_map<int, string> songs;                    // songId -> songTitle
    unordered_map<int, unordered_set<int>> songPlays;    // songId -> set of unique userIds
    unordered_map<int, vector<int>> userHistory;         // userId -> list of songIds played
    int nextSongId;
    
public:
    MusicPlayer() : nextSongId(1) {}
    
    int addSong(string songTitle) {
        songs[nextSongId] = songTitle;
        return nextSongId++;
    }
    
    void playSong(int songId, int userId) {
        if (songs.find(songId) == songs.end()) {
            return; // Song doesn't exist
        }
        
        // Add user to the song's play set
        songPlays[songId].insert(userId);
        
        // Add song to user's history
        userHistory[userId].push_back(songId);
    }
    
    void printMostPlayedSongs() {
        vector<pair<int, int>> songPlayCounts; // {playCount, songId}
        
        for (auto& entry : songPlays) {
            int songId = entry.first;
            int uniquePlayCount = entry.second.size();
            songPlayCounts.push_back({uniquePlayCount, songId});
        }
        
        // Sort by play count (descending), then by songId (ascending) for tie-breaking
        sort(songPlayCounts.begin(), songPlayCounts.end(), 
             [](const pair<int, int>& a, const pair<int, int>& b) {
                 if (a.first != b.first) return a.first > b.first;
                 return a.second < b.second;
             });
        
        for (auto& entry : songPlayCounts) {
            cout << songs[entry.second] << " (" << entry.first << " unique plays)" << endl;
        }
    }
    
    vector<int> getLastThreeSongs(int userId) {
        vector<int> result;
        unordered_set<int> seen;
        
        if (userHistory.find(userId) == userHistory.end()) {
            return result;
        }
        
        vector<int>& history = userHistory[userId];
        
        // Traverse from the end to get last 3 unique songs
        for (int i = history.size() - 1; i >= 0 && result.size() < 3; i--) {
            int songId = history[i];
            if (seen.find(songId) == seen.end()) {
                seen.insert(songId);
                result.push_back(songId);
            }
        }
        
        return result;
    }
};
```

### Time Complexities:
- `addSong()`: O(1)
- `playSong()`: O(1) average
- `printMostPlayedSongs()`: O(n log n) where n is number of unique songs played
- `getLastThreeSongs()`: O(h) where h is the history length for the user

### Space Complexity: O(S + U*H) where S is songs, U is users, H is average history length

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

```cpp
class KeyValueStore {
private:
    unordered_map<string, string> mainStore;
    vector<unordered_map<string, string>> transactionStack; // Stack of transaction states
    vector<unordered_set<string>> deletedInTransaction;     // Track deletions per transaction
    
public:
    string get(string key) {
        // Search from most recent transaction to main store
        for (int i = transactionStack.size() - 1; i >= 0; i--) {
            if (deletedInTransaction[i].count(key)) {
                return ""; // Key was deleted in this transaction
            }
            if (transactionStack[i].count(key)) {
                return transactionStack[i][key];
            }
        }
        
        if (mainStore.count(key)) {
            return mainStore[key];
        }
        return ""; // Key not found
    }
    
    void set(string key, string value) {
        if (transactionStack.empty()) {
            mainStore[key] = value;
        } else {
            // Set in current transaction
            int currentTransaction = transactionStack.size() - 1;
            transactionStack[currentTransaction][key] = value;
            deletedInTransaction[currentTransaction].erase(key); // Remove from deleted set if present
        }
    }
    
    void deleteKey(string key) {
        if (transactionStack.empty()) {
            mainStore.erase(key);
        } else {
            // Mark as deleted in current transaction
            int currentTransaction = transactionStack.size() - 1;
            deletedInTransaction[currentTransaction].insert(key);
            transactionStack[currentTransaction].erase(key); // Remove from transaction changes
        }
    }
    
    void begin() {
        transactionStack.push_back(unordered_map<string, string>());
        deletedInTransaction.push_back(unordered_set<string>());
    }
    
    void commit() {
        if (transactionStack.empty()) {
            return; // No transaction to commit
        }
        
        unordered_map<string, string> changes = transactionStack.back();
        unordered_set<string> deletions = deletedInTransaction.back();
        
        transactionStack.pop_back();
        deletedInTransaction.pop_back();
        
        if (transactionStack.empty()) {
            // Commit to main store
            for (auto& pair : changes) {
                mainStore[pair.first] = pair.second;
            }
            for (string key : deletions) {
                mainStore.erase(key);
            }
        } else {
            // Commit to parent transaction
            int parentTransaction = transactionStack.size() - 1;
            for (auto& pair : changes) {
                transactionStack[parentTransaction][pair.first] = pair.second;
                deletedInTransaction[parentTransaction].erase(pair.first);
            }
            for (string key : deletions) {
                deletedInTransaction[parentTransaction].insert(key);
                transactionStack[parentTransaction].erase(key);
            }
        }
    }
    
    void rollback() {
        if (transactionStack.empty()) {
            return; // No transaction to rollback
        }
        
        transactionStack.pop_back();
        deletedInTransaction.pop_back();
    }
};
```

### Time Complexities:
- `get()`: O(T) where T is transaction depth
- `set()`: O(1)
- `deleteKey()`: O(1)
- `begin()`: O(1)
- `commit()`: O(C) where C is changes in current transaction
- `rollback()`: O(1)

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

```cpp
class ExcelSheet {
private:
    struct Cell {
        string rawValue;
        double computedValue;
        bool hasFormula;
        set<string> dependencies; // Cells this cell depends on
        
        Cell() : rawValue(""), computedValue(0), hasFormula(false) {}
    };
    
    unordered_map<string, Cell> cells;
    unordered_map<string, set<string>> dependents; // Cells that depend on this cell
    
    // Parse cell reference like "A1" to coordinates
    pair<int, int> parseCellRef(const string& cellRef) {
        int col = cellRef[0] - 'A';
        int row = stoi(cellRef.substr(1)) - 1;
        return {row, col};
    }
    
    // Check if string is a valid cell reference
    bool isCellRef(const string& token) {
        if (token.length() < 2) return false;
        if (token[0] < 'A' || token[0] > 'Z') return false;
        for (int i = 1; i < token.length(); i++) {
            if (!isdigit(token[i])) return false;
        }
        return true;
    }
    
    // Parse and evaluate expression
    double evaluateExpression(const string& expr, const string& currentCell) {
        if (expr.empty() || expr[0] != '=') {
            return stod(expr);
        }
        
        string formula = expr.substr(1); // Remove '='
        return parseFormula(formula, currentCell);
    }
    
    // Simple expression parser
    double parseFormula(const string& formula, const string& currentCell) {
        vector<string> tokens = tokenize(formula);
        double result = 0;
        char op = '+';
        
        for (const string& token : tokens) {
            double value;
            
            if (isCellRef(token)) {
                if (token == currentCell) {
                    throw runtime_error("Circular reference detected");
                }
                
                // Add dependency
                cells[currentCell].dependencies.insert(token);
                dependents[token].insert(currentCell);
                
                // Get value from referenced cell
                if (cells.count(token)) {
                    value = cells[token].computedValue;
                } else {
                    value = 0; // Empty cell
                }
            } else if (token == "+" || token == "-") {
                op = token[0];
                continue;
            } else {
                value = stod(token);
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
    vector<string> tokenize(const string& formula) {
        vector<string> tokens;
        string current = "";
        
        for (char c : formula) {
            if (c == '+' || c == '-') {
                if (!current.empty()) {
                    tokens.push_back(current);
                    current = "";
                }
                tokens.push_back(string(1, c));
            } else if (c != ' ') {
                current += c;
            }
        }
        
        if (!current.empty()) {
            tokens.push_back(current);
        }
        
        return tokens;
    }
    
    // Update all dependent cells
    void updateDependents(const string& cellRef) {
        queue<string> toUpdate;
        set<string> visited;
        
        toUpdate.push(cellRef);
        
        while (!toUpdate.empty()) {
            string current = toUpdate.front();
            toUpdate.pop();
            
            if (visited.count(current)) continue;
            visited.insert(current);
            
            for (const string& dependent : dependents[current]) {
                if (cells.count(dependent)) {
                    try {
                        cells[dependent].computedValue = 
                            evaluateExpression(cells[dependent].rawValue, dependent);
                        toUpdate.push(dependent);
                    } catch (const exception& e) {
                        cells[dependent].computedValue = 0; // Error value
                    }
                }
            }
        }
    }
    
public:
    void set(string cell, string value) {
        // Clear old dependencies
        if (cells.count(cell)) {
            for (const string& dep : cells[cell].dependencies) {
                dependents[dep].erase(cell);
            }
            cells[cell].dependencies.clear();
        }
        
        cells[cell].rawValue = value;
        cells[cell].hasFormula = (value.length() > 0 && value[0] == '=');
        
        try {
            cells[cell].computedValue = evaluateExpression(value, cell);
        } catch (const exception& e) {
            cells[cell].computedValue = 0; // Error value
        }
        
        // Update all dependent cells
        updateDependents(cell);
    }
    
    void reset(string cell) {
        if (!cells.count(cell)) return;
        
        // Clear dependencies
        for (const string& dep : cells[cell].dependencies) {
            dependents[dep].erase(cell);
        }
        
        cells.erase(cell);
        dependents.erase(cell);
        
        // Update dependents with empty value
        updateDependents(cell);
    }
    
    void print() {
        vector<string> sortedCells;
        for (const auto& pair : cells) {
            sortedCells.push_back(pair.first);
        }
        sort(sortedCells.begin(), sortedCells.end());
        
        for (const string& cellRef : sortedCells) {
            const Cell& cell = cells[cellRef];
            cout << cellRef << ": Raw='" << cell.rawValue 
                 << "', Computed=" << cell.computedValue << endl;
        }
    }
};
```

### Time Complexities:
- `set()`: O(D) where D is number of dependent cells
- `reset()`: O(D)
- `print()`: O(n log n) where n is number of cells

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