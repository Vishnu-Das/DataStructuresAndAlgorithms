import java.util.Arrays;

public class CombinationSum1 {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8};
        int sum = 8;
        combinationSum(arr, sum);
    }

	private static void combinationSum(int[] arr, int sum) {
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++) {
            //combinationHelper(arr, )
        }
	}

    
}