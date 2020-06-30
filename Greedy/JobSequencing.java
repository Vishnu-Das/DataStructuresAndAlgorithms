import java.util.Arrays;
import java.util.Comparator;

public class JobSequencing {

    static int maxDeadLine = 0;

    public static void main(String[] args) {
       Job[] jobs = new Job[5];
       jobs[0] = new Job("a", 2, 100);
       jobs[2] = new Job("c", 2, 27);
       jobs[3] = new Job("d", 1, 25);
       jobs[1] = new Job("b", 1, 19);
       jobs[4] = new Job("e", 3, 15);
       int profit = maxProfit(jobs);
       System.out.println(profit);
    }

    private static int maxProfit(Job[] jobs) {
        Comparator<Job> profitComp = new Comparator<JobSequencing.Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                maxDeadLine = Math.max(Math.max(o1.deadline, o2.deadline), maxDeadLine);
                if(o1.profit > o2.profit) return 1;
                return -1;
            }
        };
        Arrays.sort(jobs, profitComp.reversed());
        Job[] scheduled = new Job[maxDeadLine];
        int maxProfit = 0;
        for (int i = 0; i < jobs.length; i++) {
            int currDeadline = Math.min(maxDeadLine, jobs[i].deadline);
            while(currDeadline > 0) {
                if(scheduled[currDeadline-1] == null) {
                    scheduled[currDeadline-1] = jobs[i];
                    maxProfit += jobs[i].profit;
                    break;
                }
                currDeadline--;
            }
        }
        return maxProfit;
    }

    static class Job {
    String id;
    int deadline;
    int profit;

    Job(String id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}
}