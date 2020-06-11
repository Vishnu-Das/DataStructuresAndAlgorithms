# DataStructures and Algorithms
Data Structures and Algorithms
1. Find the duplicate in an array of N integers
	/** Dutch Flag Concept / Three way partistioning*/
	public void sortColors(int[] nums) {
        int left = 0; int mid = 0; int right = nums.length-1;
        while(mid <= right) {
            switch(nums[mid]) {
                case 0: {
                    int temp = nums[left];
                    nums[left] = nums[mid];
                    nums[mid] = temp;
                    left++; mid++;break;
                }
                case 1: {
                    mid++; break;
                }
                case 2: {
                    int temp = nums[right];
                    nums[right] = nums[mid];
                    nums[mid] = temp;
                    right--; break;
                }
            }
        }
    }