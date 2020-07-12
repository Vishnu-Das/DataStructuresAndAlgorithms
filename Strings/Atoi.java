public class Atoi { // not handled inputs like "2745 wow boo" => 2745

    public static void main(String[] args) {
        int result = myAtoi("   -42");
        System.out.println(result);
    }
    
    public static int myAtoi(String str) {
        str = str.trim();
        Integer integer = Integer.valueOf(str);
        return integer.intValue();
    }
}