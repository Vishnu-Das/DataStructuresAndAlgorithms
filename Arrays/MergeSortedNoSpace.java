public class MergeSortedNoSpace {

    static int[] ar1 = {1, 5, 9, 10, 15, 20};
    static int[] ar2 = {2, 3, 8, 13};

    public static void main(String[] args) {
        mergeSortedArrays(ar1.length, ar2.length);
        for (int i = 0; i < ar1.length; i++) {
            System.out.print(ar1[i] + " ");
        }
        for (int i = 0; i < ar2.length; i++) {
            System.out.print(ar2[i] + " ");
        }
    }

    public static void mergeSortedArrays(int a1, int a2) {
        for (int i = a2-1; i>=0; i--) {
            int j, last = ar1[a1-1];
            for (j=a1-2; j>=0 && ar1[j] > ar2[i]; j--) {
                ar1[j+1] = ar1[j];
            }
            if (j != a1-2 || last > ar2[i]) {
                ar1[j+1] = ar2[i];
                ar2[i] = last;
            }
        } 
    }
}