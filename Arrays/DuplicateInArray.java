public class DuplicateInArray {
    // {1, 2, 3, 1, 3, 6, 6} 
    // this does not work for
    // a[i] = 0;
    // a[i] = negative;
    // a[i] > a.length-1;
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 1, 3, 6, 6};
        findDuplicate(a);        
    }

    public static void findDuplicate (int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[Math.abs(a[i])] >= 0) {
                a[Math.abs(a[i])] = -a[Math.abs(a[i])];
            } else {
                System.out.print(Math.abs(a[i]) + " ");
            }
        }
    }
}