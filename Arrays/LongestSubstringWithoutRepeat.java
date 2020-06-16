import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestSubstringWithoutRepeat {
    public static void main(String[] args) {
        String s = "acdeefghiijklmm";
        int sub = longestNonRepeatSubstring(s);
        System.out.println(sub);
    }

    private static int longestNonRepeatSubstring(String s) {
        HashSet<Character> hSet = new HashSet<>();
        int start=0, end=0, max =0;
        while(end < s.length()) {
            if( ! hSet.contains(s.charAt(end))) {
                hSet.add(s.charAt(end));
                max = Math.max(max, hSet.size());
                end++;
            } else {
                hSet.remove(s.charAt(start));
                start++;
            }
        }
        return max;
    }

}