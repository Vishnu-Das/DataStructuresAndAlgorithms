import java.util.HashMap;
import java.util.Map;

public class CountSubArraysWithGivenXOR {
    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 6, 4};
        int m = 6;
        long countOfSubArraysHavingXORm = countSubArraysHavingXOR(nums, m);
        System.out.println(countOfSubArraysHavingXORm);
    }

    private static long countSubArraysHavingXOR(int[] nums, int target) { 
        // Sub1 ^ Sub2 = m => Sub1 ^ m = Sub2; => numsXOR[i] ^ target = temp 
        long countOfSubarrays = 0;
        int[] numsXOR = new int[nums.length];
        Map<Integer, Integer> checkMap =  new HashMap<>();
        numsXOR[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            numsXOR[i] = numsXOR[i-1] ^ nums[i];
        }
        for (int i = 0; i < numsXOR.length; i++) {
            int temp = target ^ numsXOR[i];
            if (checkMap.containsKey(temp)) {
                countOfSubarrays += (long) checkMap.get(temp); // doubt here - add 1 or not
            }

            if (numsXOR[i] == target) {
                countOfSubarrays++;
            }

            if(checkMap.containsKey(numsXOR[i])) {
                int n = checkMap.get(numsXOR[i]) + 1;
                checkMap.put(numsXOR[i], n);
            } else {
                checkMap.put(numsXOR[i], 1);
            }
        }
        return countOfSubarrays;
    }    
}