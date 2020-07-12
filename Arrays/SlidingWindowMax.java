public class SlidingWindowMax {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}, k = 4;

        int res = maxSubArraySizeK(a, k);
        System.out.println(res);
    }

    private static int maxSubArraySizeK(int[] a, int k) {
        int start =0, j=0;
        int curr_sum = 0, max_sum = 0;
        for (j = 0; j < k; j++) {
            curr_sum += a[j];
        }
        max_sum = curr_sum;
        while(j<a.length) {
            curr_sum += a[j++];
            curr_sum -= a[start++];
            max_sum =  Math.max(max_sum, curr_sum);
        }
        return max_sum;
    }

    
}