import java.util.Stack;

public class BalancedParentheses {
    public static void main(String[] args) {
        String s = "[()]{}{[()()]()}";
        boolean balanced = isBalanced(s);
        System.out.println(balanced);
    }

    private static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else {
                char popped = stack.pop();
                if(! (s.charAt(i) == ')' && popped == '(' 
                    || s.charAt(i) == ']' && popped == '['
                    || s.charAt(i) == '}' && popped == '{')) {
                    return false;
                }
            }
        }

        return true;
    }
}