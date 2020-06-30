import java.util.Stack;

public class MaxAreaRectangle {
    public static void main(String[] args) {
        int hist[] = { 6, 2, 5, 4, 5, 1, 6 };
        int maxArea = getMaxAreaRectangle(hist);
        System.out.println(maxArea);
    }

    private static int getMaxAreaRectangle(int[] hist) {
        Stack<Integer> stk = new Stack<>();
        int max_area = 0; 
        int top;
        int area_with_top;
        int i = 0;
        while(i < hist.length) {
            if(stk.isEmpty() || hist[stk.peek()] <= hist[i]) {
                stk.push(i++);
            } else {
                top = stk.pop();
                area_with_top = hist[top] * (stk.isEmpty() ? i : i - stk.peek() - 1);

                max_area = Math.max(max_area, area_with_top);
            }
        }

        while( ! stk.isEmpty()) {
            top = stk.peek();
                stk.pop();
                area_with_top = hist[top] * (stk.isEmpty() ? i : i - stk.peek() - 1);

                max_area = Math.max(max_area, area_with_top);
        }

        return max_area;
    }
}