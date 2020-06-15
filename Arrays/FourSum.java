import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public static void main(String[] args) {
        int[] nums = {-1,0,-5,-2,-2,-4,0,1,-2};
        int target = -9;
        fourSum(nums, target);
        System.out.println("Done");
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < nums.length-3; i++) {
            if (i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for(int j = i + 1; j < nums.length - 2; j++) {
               if (j != i + 1 && nums[j] == nums[j-1]) {
                continue;
                }
                int left = j+1;
                int right = nums.length-1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        List<Integer> quadruple = new ArrayList<>();
                        quadruple.add(nums[i]);
                        quadruple.add(nums[j]);
                        quadruple.add(nums[left]);
                        quadruple.add(nums[right]);
                        result.add(quadruple);
                        left++; right--;
                                           
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    }
                }
            }
        }
        return result;
    }
}