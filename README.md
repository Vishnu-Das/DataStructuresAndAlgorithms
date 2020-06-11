# DataStructures and Algorithms

1. ## Sort an array of 0’s 1’s 2’s without using extra space or sorting algo O(n) O(1)
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

2. ## Find the duplicate in an array of N integers O(n) O(1)
    /** time O(n) Space O(1)*/
    ### this does not work when a[i] = 0; a[i] = negative; a[i] > a.length-1;
    public static void findDuplicate (int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[Math.abs(a[i])] >= 0) { // trick
                a[Math.abs(a[i])] = -a[Math.abs(a[i])];
            } else {
                System.out.print(Math.abs(a[i]) + " ");
            }
        }
    }

3. ## Repeat and Missing Number O(n) O(1)
    public static void findDuplicateAndRepeating (int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[Math.abs(a[i])-1] >= 0) { // trick
                a[Math.abs(a[i])-1] = -a[Math.abs(a[i])-1];
            } else {
                System.out.print(Math.abs(a[i]) + " ");
            }
        }
        System.out.println("");
        // {-1, -2, -3, 1, 3, -6, 6}
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) { // trick
                System.out.print((i+1) + " ");
            }
        }
    }

4. ## Merge two sorted Arrays without extra space O(n*m) O(1)

    public static void mergeSortedArrays(int a1, int a2) {
        for (int i = a2-1; i>=0; i--) {
            int j, last = ar1[a1-1];
            for (j=a1-2; j>=0 && ar1[j] > ar2[i]; j--) {
                ar1[j+1] = ar1[j];
            }
            if (j != a1-2 || last > ar2[i]) { // j != a1-2, I feel this condition is not required.
                ar1[j+1] = ar2[i];
                ar2[i] = last;
            }
        } 
    }