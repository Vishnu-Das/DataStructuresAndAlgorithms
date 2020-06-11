public class RepeatAndMissing {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 1, 3, 6, 6};
        findDuplicateAndRepeating(a);        
    }

    public static void findDuplicateAndRepeating (int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[Math.abs(a[i])-1] >= 0) {
                a[Math.abs(a[i])-1] = -a[Math.abs(a[i])-1];
            } else {
                System.out.print(Math.abs(a[i]) + " ");
            }
        }
        System.out.println("");
        // {-1, -2, -3, 1, 3, -6, 6}
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                System.out.print((i+1) + " ");
            }
        }
    }
}