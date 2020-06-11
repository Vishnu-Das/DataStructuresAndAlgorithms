class ThreeWayPartition {
    public static void main(String[] args) {

        int[] a = {1,2,0,1,1,2,0,0};
        ThreeWayPartitionSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }   
    }

    public static void ThreeWayPartitionSort (int[] a) {

        int left = 0; int mid = 0;  int right = a.length-1;

        while(mid <= right) {
            switch(a[mid]) {
                case 0 : {
                    int temp = a[left];
                    a[left] = a[mid];
                    a[mid] = temp;
                    left++; mid++; break;
                }
                case 1 : {
                    mid++; break;
                }
                case 2 : {
                    int temp = a[right];
                    a[right] = a[mid];
                    a[mid] = temp;
                    right--; break;
                }
            }
        }
    }
}
