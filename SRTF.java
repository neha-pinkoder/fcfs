import java.util.Scanner;

public class SRTF {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of process:");
        int n = sc.nextInt(); // number of processes
        int pid[] = new int[n];
        int at[] = new int[n]; // arrival time
        int bt[] = new int[n]; // burst time
        int wt[] = new int[n]; // waiting time
        int rt[] = new int[n]; // remaining time

        for (int i = 0; i < n; i++) {
            System.out.println("enter process " + (i + 1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println("enter process " + (i + 1) + " burst time:");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
        }

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = bt[i];

        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        // Process until all processes gets completed
        while (complete != n) {

            // Find process with minimum remaining time among the processes that arrives
            // till the current time`
            for (int j = 0; j < n; j++) {
                if ((at[j] <= t) &&
                        (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }

            // Reduce remaining time by one
            rt[shortest]--;

            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If a process gets completely executed
            if (rt[shortest] == 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;

                // Calculate waiting time
                wt[shortest] = finish_time -
                        bt[shortest] -
                        at[shortest];

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
            // gantt chart
            System.out.println("P" + pid[shortest]);
        }

        // calculating turnaround time by adding
        // bt[i] + wt[i]
        int tat[] = new int[n];
        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];

        // Method to calculate average time
        float total_wt = 0, total_tat = 0;

        // Display processes along with all details
        System.out.println("Processes " + " Arrival time " + " Burst time " + " Waiting time " + " Turn around time");

        // Calculate total waiting time and total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(
                    " " + pid[i] + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t " + wt[i] + "\t\t" + tat[i] + "\t\t");
        }
        System.out.println("Average Waiting Time = " + (total_wt / n));
        System.out.println("Average TurnAround Time = " + (total_tat / n));
    }
}
