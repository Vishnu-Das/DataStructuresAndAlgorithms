import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int [] nums = {2, 7, 11, 15};
        int[] pair = twoSum(nums, 9);
        System.out.println("[" + pair[0] + ", " + pair[1] + "]");
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] pair = new int[2];
        Map<Integer,Integer> previousMap = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            int neededValue = target - nums[i];
            int prevIndex = previousMap.getOrDefault(neededValue, -1);
            if(prevIndex != -1) {
                pair[0] = prevIndex;
                pair[1] = i;
            } else {
                previousMap.put(nums[i], i);
            }
        }
        return pair;
    }
}