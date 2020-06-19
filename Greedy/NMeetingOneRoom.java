import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class NMeetingOneRoom {
    public static void main(String[] args) {
        Meeting[] meetings = new Meeting[6];
        meetings[0] = new Meeting(1,2);
        meetings[1] = new Meeting(3,4);
        meetings[2] = new Meeting(0,6);
        meetings[3] = new Meeting(5,7);
        meetings[4] = new Meeting(8,9);
        meetings[5] = new Meeting(5,9);
        List<Meeting> posMeetings = possibleMeetings(meetings);
        posMeetings.forEach(meeting -> System.out.println(meeting.toString()));
    }

    private static List<Meeting> possibleMeetings(Meeting[] meetings) {
        Arrays.sort(meetings, Comparator.comparing( m -> m.end));
        List<Meeting> result = new ArrayList<>();
        result.add(meetings[0]); int count = 0;
         for (int i = 1; i < meetings.length; i++) {
             if (meetings[i].start > result.get(count).end) {
                result.add(meetings[i]);
                count++;
             }
         }

        return result;
    }
}
class Meeting {
    int start;
    int end;
    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + this.start + ", " + this.end + ")";
    }
}

