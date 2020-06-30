import java.util.Stack;

public class NextClosestSmallerElement {
    static Stack<Integer> stack = new Stack<>();

    public static void main(final String[] args) {
        final int[] nums = { 15, 5, 2, 25, 4, 1 };
        final int[] result = nextSmallerElem(nums);
        for (final int i : result) {
            System.out.print(i + " ");
        }
    }

    private static int[] nextSmallerElem(int[] nums) {
        int[] result = new int[nums.length];
        result[nums.length-1] = 0;
        for (int i = 0; i < nums.length-1; i++) {
            int k = i+1; stack.push(nums[i]);
            while(k < nums.length) {
                if (nums[k] < nums[i] && nums[k] < stack.peek()) {
                    stack.push(nums[k]);
                }
                k++;
            }
            result[i] = stack.peek();
            stack.clear();
        }
        return result;
    }
}