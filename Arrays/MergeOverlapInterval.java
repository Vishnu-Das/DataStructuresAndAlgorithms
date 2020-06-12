import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeOverlapInterval {
    public static void main(final String[] args) {
        final int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        //final int[][] intervals = {{1,4},{4,5}};
        //final int[][] intervals = {{1,4}};


        mergeOverlappingIntervals(intervals).forEach(
            interval -> System.out.print("(" + interval[0] + "," + interval[1] + ") ")
        );
    }

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
}
