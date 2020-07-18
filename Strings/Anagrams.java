
public class Anagrams {
    public static void main(final String[] args) {
        System.out.println(isAnagram("aa", "bb"));
    }

    public static boolean isAnagram(final String s, final String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] characters = new int[26];
        for(char smallChar : s.toCharArray()) {
            characters[smallChar - 'a'] = characters[smallChar - 'a'] + 1; 
        }
        for(char smallChar : t.toCharArray()) {
            characters[smallChar - 'a'] = characters[smallChar - 'a'] - 1;
        }
        for (int i : characters) {
            if(i != 0) {
                return false;
            }
        }
        return true;
    }
}