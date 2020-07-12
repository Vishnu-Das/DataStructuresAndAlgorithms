public class ReverseWordsInString {

    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example"));
    }

    public static String reverseWords(String s) {
        String[] strArr = s.split(" ");
        StringBuffer buffStr = new StringBuffer();
        for(int i = strArr.length-1; i>=0; i--) {
            if ( ! strArr[i].equals("")) {
                buffStr.append(" " + strArr[i].trim());
            }
        }
        return buffStr.toString().trim();
    }
}