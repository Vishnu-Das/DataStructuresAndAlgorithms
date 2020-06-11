# DataStructures and Algorithms
Data Structures and Algorithms
1. ## Sort an array of 0’s 1’s 2’s without using extra space or sorting algo
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

2. ## Find the duplicate in an array of N integers
    /** time O(n) Space O(1)*/
    ### this does not work when a[i] = 0; a[i] = negative; a[i] > a.length-1;
    public static void findDuplicate (int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[Math.abs(a[i])] >= 0) {
                a[Math.abs(a[i])] = -a[Math.abs(a[i])];
            } else {
                System.out.print(Math.abs(a[i]) + " ");
            }
        }
    }