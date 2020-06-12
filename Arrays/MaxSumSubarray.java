 public class MaxSumSubarray {
    public static void main(String[] args) {
        int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(maximumSumSubarray(a));
    }

    public static int maximumSumSubarray(int[] a) {
        int max_till_here = 0, max_so_far = 0;
        for (int i = 0; i < a.length; i++) {
            max_till_here += a[i];
            if(max_till_here < 0) max_till_here = 0;
            if(max_so_far < max_till_here) max_so_far = max_till_here;
        }
        return max_so_far;
    }
}