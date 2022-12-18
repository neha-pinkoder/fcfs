import java.util.Scanner;

/**
 * Created by Neha Gholap
 * Roll no.: 222011014
 */

public class sjfs {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of process:");
        int n = sc.nextInt(); // number of processes
        int pid[] = new int[n];
        int at[] = new int[n]; // arrival time
        int bt[] = new int[n]; // burst time
        int ct[] = new int[n]; // completion time
        int ta[] = new int[n]; // turnaround time
        int wt[] = new int[n]; // waiting time
        int f[] = new int[n];

        int st = 0, tot = 0;
        float totalWaitingTime = 0, totalTurnaroundTime = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("enter process " + (i + 1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println("enter process " + (i + 1) + " burst time:");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
            f[i] = 0;
        }

        while (true) {
            int c = n, min = 999999;

            if (tot == n)
                break;

            for (int i = 0; i < n; i++) {

                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
                }
            }
            if (c == n)
                st++;
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c] - at[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                pid[tot] = c + 1;
                tot++;
            }
        }

        System.out.println("Process\t\tAT\t\tBT\t\tCT\t\tTAT\t\tWT");
        for (int i = 0; i < n; i++) {
            totalTurnaroundTime += wt[i];
            totalWaitingTime += ta[i];
            System.out.println(
                    "P" + pid[i] + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t" + ta[i] + "\t\t" + wt[i]);
        }
        System.out.println("Average TurnAround Time = " + (totalTurnaroundTime / n));
        System.out.println("Average Waiting Time = " + (totalWaitingTime / n));
        sc.close();
        System.out.println("gantt chart: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + pid[i] + " ");
        }
    }
}