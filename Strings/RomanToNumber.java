import java.util.HashMap;

public class RomanToNumber {
    static HashMap<Character, Integer> romanToNum = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(romanToInt("LVIII"));
    }
    
    public static int romanToInt(String s) {  
        romanToNum.put('I', 1);
        romanToNum.put('V', 5);
        romanToNum.put('X', 10);
        romanToNum.put('L', 50);
        romanToNum.put('C', 100);
        romanToNum.put('D', 500);
        romanToNum.put('M', 1000); 
        if (s == null || s.length()<1) return 0;
        int integerValue = 0;
        for(int i = 0; i < s.length(); i++) {
            if(i != s.length()-1) {
                if(s.charAt(i) == 'I' && (s.charAt(i+1) == 'X' || s.charAt(i+1) == 'V')) {
                    integerValue += 
                    romanToNum.get(s.charAt(i+1)) - romanToNum.get(s.charAt(i++));
                } else
                if(s.charAt(i) == 'X' && (s.charAt(i+1) == 'L' || s.charAt(i+1) == 'C')) {
                    integerValue += 
                    romanToNum.get(s.charAt(i+1)) - romanToNum.get(s.charAt(i++));
                } else
                if(s.charAt(i) == 'C' && (s.charAt(i+1) == 'D' || s.charAt(i+1) == 'M')) {
                    integerValue += 
                    romanToNum.get(s.charAt(i+1)) - romanToNum.get(s.charAt(i++));
                } else {
                    integerValue += romanToNum.get(Character.valueOf(s.charAt(i)));   
                }
            } else {
                integerValue += romanToNum.get(Character.valueOf(s.charAt(i)));   
            }
        }   
        return integerValue;
    }
}