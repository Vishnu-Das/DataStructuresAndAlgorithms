import java.util.ArrayList;
import java.util.List;

public class AllAnagramsInString {
    public static void main(String[] args) {
        findAnagrams("cbaebabacd", "abc").forEach(System.out::print);
    }

    public static List<Integer> findAnagrams(String s, String p) {
               
        List<Integer> indices = new ArrayList<>();
        if (s == null || s.isEmpty()) return indices;
        int[] char_count = new int[26];
        
        for (char c : p.toCharArray()) {
            char_count[c - 'a']++;
        }
        
        int left = 0, right = 0, count = p.length();
        
        while(right < s.length()) {
            if(char_count[s.charAt(right++) - 'a']-- >= 1) count--;
            
            if(count == 0) indices.add(left);
            
            if(right - left == p.length() && char_count[s.charAt(left++) - 'a']++ >= 0) count++;
        }
        return indices;        
    }
}