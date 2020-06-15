import java.util.HashMap;
import java.util.Map;

/** In order to get the sub array we can store k = prefixMap.get(curr_sum) and l = i in two variables 
 * whenever we have new longest and at last iterate the input array from prefixMap.get(curr_sum) to i
 * to get the longest subarray.
*/
public class LongestSubarrayWith0Sum {
    public static void main(String[] args) {
        int[] nums = {15, -2, 2, -8, 1, 7, 10, 23};
        int target = 0;
        int longest = longestSubArrayLength(nums, target);
        System.out.println(longest);  
    }

    private static int longestSubArrayLength(int[] nums, int target) {
        int longest = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int curr_sum = 0;
        for(int i = 0; i < nums.length; i++) {
            curr_sum += nums[i];
            if (prefixMap.containsKey(curr_sum)) {
                longest = Math.max(longest, i-prefixMap.get(curr_sum));
            } else {
                prefixMap.put(curr_sum, i);
            }
        }
        return longest;
    }
    

}