import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class Caching {
    public static void main(String[] args) {
        LRUCache che = new LRUCache(6);
        che.refer(1);
        che.refer(2);
        che.refer(4);
        che.refer(2);
        che.refer(3);
        // 3 2 4 1
        displayCacheSequence(che);
    }

    public static void displayCacheSequence(LRUCache cache) {
        LRUCache.dq.forEach(
            x -> System.out.println(x + " ")
        );
    }
}

class LRUCache {
    static Deque<Integer> dq;
    static HashMap<Integer, String> map;
    static int cacheSize;

    public LRUCache (int n) {
        dq = new LinkedList<Integer>();
        map = new HashMap<Integer, String>();
        cacheSize = n;
    }

    public String refer (int x) {
        if(map.containsKey(x)) {
            if (dq.size() == map.size()) {
                dq.remove(x);
                dq.addFirst(x);
            }
        } else {
            map.put(x,"data" + x);
            if( ! dq.isEmpty() && dq.size() > map.size()) dq.removeLast();
            dq.addFirst(x);
        }
        return map.get(x);
    }

    public void clearCache() {
        dq.clear(); map.clear();
    }

}