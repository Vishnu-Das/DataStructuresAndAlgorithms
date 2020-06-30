import java.util.Stack;

public class NextClosestGreatorElement {

    static Stack<Integer> stack = new Stack<>();

    public static void main(final String[] args) {
        final int[] nums = { 1, 5, 2, 25, 4, 26 };
        final int[] result = nextGreatorElem(nums);
        for (final int i : result) {
            System.out.print(i + " ");
        }
    }

    private static int[] nextGreatorElem(final int[] nums) {
        if (nums.length == 0) return nums;
        final int[] result = new int[nums.length];
        result[nums.length-1] = -1;
        for (int i = 0; i < nums.length-1; i++) {
            int k = i + 1;
            stack.push(Integer.MAX_VALUE);
            while(k < nums.length) {
                if(nums[k] > nums[i] && nums[k] < stack.peek()) {
                    stack.push(nums[k]);
                }
                k++;
            }
            if (stack.peek() == Integer.MAX_VALUE) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }      
            stack.clear();
        }

        return result;
    }
}