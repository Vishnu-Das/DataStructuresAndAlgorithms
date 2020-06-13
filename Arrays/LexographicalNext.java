class LexographicalNext {

    // 6 2 1 5 4 3 0  -> 6 2 3 0 1 4 5 
    public static void main(String[] args) {
        int[] nums = {6,2,1,5,4,3,0};
        //int[] nums = {6,5,4,3,2,1,0};
        //int[] nums = {1};
        nextPermutation(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void nextPermutation(final int[] nums) {
        if (nums.length > 1) {
            final int pivot = findPivot(nums);
            if (pivot == -1) {
                reverseFromTo(nums, 0, nums.length-1);
            } else {
                int swapIndex = findReplacementForPivot(nums, pivot);
                swapElements(nums, pivot, swapIndex);
                reverseFromTo(nums, pivot+1, nums.length-1);
            }
        }
    }

    /** find the pivot -> number just before strictly decreasing sequence*/
    private static int findPivot(final int[] nums) {
        int i = nums.length - 2;
        while ( i >= 0 && nums[i] > nums[i + 1]) {
            i--;
        }
        return i;
    }

    private static int[] reverseFromTo(final int[] nums, int leftIndex, int rightIndex) {
        while (leftIndex < rightIndex) {
            final int temp = nums[leftIndex];
            nums[leftIndex++] = nums[rightIndex];
            nums[rightIndex--] = temp;
        }
        return nums;
    }

    private static int[] swapElements(final int[] nums, final int index1, final int index2) {
        final int temp = nums[index1];
        nums[index1]= nums[index2];
        nums[index2] = temp;
        return nums;
    }

    /** Finds the index of number to be swapped with pivot */
    private static int findReplacementForPivot(int[] nums, int pivotIndex) {
        int swapIndex = nums.length-1;
        while ( swapIndex >pivotIndex && nums[swapIndex] < nums[pivotIndex]) {
            swapIndex--;
        }
        return swapIndex;
    }
}