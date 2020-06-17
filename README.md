# DataStructures and Algorithms

1. ## Sort an array of 0’s 1’s 2’s without using extra space or sorting algo O(n) O(1)
<details>
    <summary>snippet</summary>

```
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
```
</details>

2. ## Find the duplicate in an array of N integers O(n) O(1)
<details>
    <summary>snippet</summary>

    ### this does not work when a[i] = 0; a[i] = negative; a[i] > a.length-1;
```
    /** time O(n) Space O(1)*/
    public static void findDuplicate (int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[Math.abs(a[i])] >= 0) { // trick
                a[Math.abs(a[i])] = -a[Math.abs(a[i])];
            } else {
                System.out.print(Math.abs(a[i]) + " ");
            }
        }
    }
```
</details>

3. ## Repeat and Missing Number O(n) O(1)
<details>
    <summary>snippet</summary>

```
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
```
</details>

4. ## Merge two sorted Arrays without extra space O(n*m) O(1)
<details>
    <summary>snippet</summary>

```
    public static void mergeSortedArrays(int a1, int a2) {
        for (int i = a2-1; i>=0; i--) {
            int j, last = ar1[a1-1];
            for (j=a1-2; j>=0 && ar1[j] > ar2[i]; j--) {
                ar1[j+1] = ar1[j];
            }
            if (j != a1-2 || last > ar2[i]) { /** j != a1-2, I feel this condition is not required. */
                ar1[j+1] = ar2[i];
                ar2[i] = last;
            }
        } 
    }
```
</details>

5. ## Kadane's Algorithm
<details>
    <summary>snippet</summary>

    ### Largest Sum contiguous subarray.
```
    public static int maximumSumSubarray(int[] a) {
        int max_till_here = 0, max_so_far = 0;
        for (int i = 0; i < a.length; i++) {
            max_till_here += a[i];
            if(max_till_here < 0) max_till_here = 0;
            if(max_so_far < max_till_here) max_so_far = max_till_here;
        }
        return max_so_far;
    }
```
</details>

6. ## Merge Overlapping Subintervals
<details>
    <summary>snippet</summary>

```
    public static List<int[]> mergeOverlappingIntervals(final int[][] intervals) {
       if (intervals.length <= 1) return Arrays.asList(intervals) ;
       // sort intervals based on start value
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        /**
         * put the first interval in result.
         * check for overlap from second interval
         * if yes replace the result inteval last with max of result interval and current interval
         * if no add current interval to result and make result interval = current interval.
         */
        final List<int[]> result = new ArrayList<>();
        int[] lastInterval = intervals[0];
        result.add(lastInterval);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= lastInterval[1]) {
                lastInterval[1] = Math.max(lastInterval[1], intervals[i][1]);
            } else {
                lastInterval = intervals[i];
                result.add(lastInterval);
            }
        }
        return result;
    }
```
</details>

7. ## Set col and row zero for a zero element in matrix 
<details>
    <summary>snippet</summary>

```
    private static void setMatrixToZero(int[][] matrix) {
        boolean rowzero = false;
        boolean colzero =false;
        int r = matrix.length;
        int c = matrix[0].length;
        /** check first row contains 0 */
        for(int i=0; i< r; i++) {
            if(matrix[i][0] == 0) {
                colzero = true;
            }
        }
        /** check first column contains 0 */
        for(int i=0; i< c; i++) {
            if(matrix[0][i] == 0) {
                rowzero = true;
            }
        }
        /** check for zero elements and use first row and column as flags */
        for(int i = 1; i< r; i++) {
            for(int j =1; j<c; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        /** Using first row and column mark elements as zero */
        for(int i = 1; i< r; i++) {
            for(int j =1; j<c; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        /** check and convert first row*/
        if(rowzero == true) {
            for(int i=0; i<c ; i++) {
                matrix[0][i] = 0;
            }
        }
        /** check and convert first column*/
        if(colzero == true) {
            for(int i=0; i<r ; i++) {
                matrix[i][0] = 0;
            }
        }
    }
```
</details>

8. ## Pascal's Triangle creation for a given number.
<details>
    <summary>snippet</summary>

```
public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>();
        List<Integer> firstList = new ArrayList<>();
        if (numRows == 0) {
            return pascalTriangle;
        }
        firstList.add(1);
        pascalTriangle.add(firstList);
        int count = 1;
        while (count < numRows) {
            List<Integer> nextList = createNextList(firstList);
            pascalTriangle.add(nextList);
            firstList = nextList;
            count++;
        }
        return pascalTriangle;
    }
    
    private static List<Integer> createNextList(List<Integer> pseq) {
        List<Integer> nseq = new ArrayList<Integer>();
        for (int i = 0; i<=pseq.size(); i++) {
            if(i == 0) {
                nseq.add(pseq.get(0));
            } else if(i == pseq.size()) {
                nseq.add(pseq.get(pseq.size()-1));
            } else {
                nseq.add(pseq.get(i-1) + pseq.get(i));
            }
        }
        return nseq;
    }
```
</details>

9. ## Next Permutation of Lexographical order.
<details>
    <summary>snippet</summary>

```
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
```
</details>

10. ## Stock Buy and Sell - Eazy
<details>
    <summary>snippet</summary>

```
    private static int maxProfit(final int[] prices) {
        if (prices.length == 0) {
			 return 0 ;
		 }		
		 int max = 0 ;
		 int sofarMin = prices[0] ;
	     for (int i = 0 ; i < prices.length ; ++i) {
	    	 if (prices[i] > sofarMin) {
	    		 max = Math.max(max, prices[i] - sofarMin) ;
	    	 } else{
	    		sofarMin = prices[i];  
	    	 }
	     }	     
	    return  max ;
    }
```
</details>

11. ## Stock Buy and Sell - Medium
<details>
    <summary>snippet</summary>

```
    private static int maximumProfit(int[] stocks) {
        boolean bought = false;
        int i =0, profit = 0;
        while(i < stocks.length-1) {
            if (stocks[i] < stocks[i+1] && !bought) {
                profit -= stocks[i];
                bought = true;
            } else
            if (stocks[i] > stocks[i+1] && bought) {
                profit += stocks[i];
                bought = false;
            }
            i++;
        }

        if(bought && i == stocks.length-1) {
            profit += stocks[i];
            bought = false;
        }

		return profit;
	}
```
</details>

12. ## Two SUm - Eazy - Linear time. using Hash Map.
<details>
    <summary>snippet</summary>

```
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
```
</details>

13. ## Four Sum - Medium.
<details>
    <summary>snippet</summary>

```
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
```
</details>

14. ## Longest Consecutive Sequence O(n)
<details>
    <summary>snippet</summary>

```
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
```
</details>

15. ## Longest SubArray with sum 0. O(n)
<details>
    <summary>snippet</summary>

```
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
```
</details>

16. ## Count of SubArrays with a given XOR.
<details>
    <summary>snippet</summary>

```
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
```
</details>

17. ## Longest unique character Substring
<details>
    <summary>snippet</summary>

```
    private static int longestNonRepeatSubstring(String s) {
        HashSet<Character> hSet = new HashSet<>();
        int start=0, end=0, max =0;
        while(end < s.length()) {
            if( ! hSet.contains(s.charAt(end))) {
                hSet.add(s.charAt(end));
                max = Math.max(max, hSet.size());
                end++;
            } else {
                hSet.remove(s.charAt(start));
                start++;
            }
        }
        return max;
    }
```
</details>

18. ## Reverse a LinkedList.
<details>
    <summary>snippet</summary>

```
    public static Node reverse(Node head) {
        Node prev = null;
        Node next = null;
        Node current = head;
        while(current != null) {
            next =current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;
    }
```
</details>

19. ## MiddleOfLinkedList
<details>
    <summary>snippet</summary>

```
    public static ListNode middleNode(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        if(head != null) {
            while (fastPtr != null && fastPtr.next != null) {
                slowPtr = slowPtr.next;
                fastPtr = fastPtr.next.next;
            }
        }
        return slowPtr;
    }
```
</details>

19. ## Is Valid IP Address.
<details>
    <summary>snippet</summary>

```
    public static String validIPAddress(String IP) {
        if(IP.length() == 0 || IP.charAt(IP.length()-1) == ':' || IP.charAt(IP.length()-1) == '.' || IP.contains("-"))
         return "Neither";
        String[] ipv4 = IP.split("\\.");
        if(ipv4.length == 4) {
            for (int i=0; i<ipv4.length; i++) {
                if(ipv4[i].length() == 0 || ipv4[i].length() != 1 && ipv4[i].charAt(0) == '0') {
                    return "Neither";
                }
                try {
                    int val = Integer.parseInt(ipv4[i]);
                    if(val > 255 || val < 0 ) {
                        return "Neither";
                    }    
                } catch (Exception e) {
                    return "Neither";
                }
            }
            return "IPv4";
        } else {
            String[] ipv6 = IP.split(":");
            if (ipv6.length == 8) {
                for (int i = 0; i < ipv6.length; i++) {
                    if(ipv6[i].length() == 0 || ! isHexadecimal(ipv6[i])
                        || ipv6[i].length() > 4) return "Neither";
                }
                return "IPv6";
            } else {
                return "Neither";
            }
        }
    }

    private static boolean isHexadecimal(String input) {
        final Matcher matcher = HEXADECIMAL_PATTERN.matcher(input);
        return matcher.matches();
    }
```
</details>

20. ## Merge two sorted LinkedLists
<details>
    <summary>snippet</summary>

```
    public static ListNode mergeSortedLL(ListNode h1, ListNode h2) {
        ListNode i = h1, j = h2; head = null; prev = null;
        while(i != null && j != null) {
            if(i.val <= j.val) {
                add(i);
                i= i.next;
            } else {
                add(j);
                j= j.next;
            }
        }

        while(i != null) {
            add(i); i = i.next;
        }
        while(j != null) {
            add(j); j = j.next;
        }

        return head;
    }

    public static void add (ListNode node) {
        if (head == null) {
            head = node; prev = node;
        } else {
            prev.next = node;
            prev = node;
        }
    }

    class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {this.val = val;}
    ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }
```
</details>

21. ## Delete Nth Node from End of Linked List.
<details>
    <summary>snippet</summary>

```
    public static LiNode removeNthFromEnd(LiNode head, int n) {
        LiNode first = head, second = head;
        for(int i=0; i<n; i++) {
                if(second.next == null) {
                    if (i==n-1){
                    head = head.next;
                    return head;
                }
            }
            second = second.next;
        }
        
        while(second.next != null) {
            first = first.next;
            second = second.next;
        }
        
        first.next = first.next.next;
        return head;
    }
}
```
</details>