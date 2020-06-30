import java.util.Arrays;

public class MinimumPlatform {
    public static void main(String[] args) {
        int[] start = {900, 940, 950, 1100, 1500, 1800};
        int[] end = {910, 1200, 1120, 1130, 1900, 2000};

        int minimumPlatforms = minPlatform(start, end);
        System.out.println(minimumPlatforms);
    }

    private static int minPlatform(int[] start, int[] end) {
        Arrays.sort(start);
        Arrays.sort(end);
        int i = 1, minPlatform = 1, j=0;
        // while(i < start.length && j < end.length) {
        //     if(start[i] < end[j]) {
        //         minPlatform++; i++;
        //     } else if (start[i] > end[j]) {
        //         minPlatform--; j++;
        //     }
        // }
        return minPlatform;
    }


}