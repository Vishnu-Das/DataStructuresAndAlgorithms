import java.util.*;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int longest = longestConsecutive(nums);
        System.out.println(longest);
    }

    public static int longestConsecutive(int[] nums) {
        int longest = 0;
        Set<Integer> checkSet = new HashSet<>();
        for (int i=0; i<nums.length; ++i) {
            checkSet.add(nums[i]);
        }

        for(int i = 0; i < nums.length; ++i) {
            if ( ! checkSet.contains(nums[i] - 1)) {
                int first = nums[i];
                while(checkSet.contains(first)) {
                    first++;
                }
                longest = Math.max(longest, (first - nums[i]));
            }
        }

        return longest;
    }
}