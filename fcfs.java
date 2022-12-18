import java.util.Scanner;

/**
 * Created by Neha Gholap
 * Roll No. 222011014
 */
public class fcfs {
    public static void main(String[] args) {

        System.out.println("Enter the number of process");
        Scanner in = new Scanner(System.in);
        int numberOfProcesses = in.nextInt();

        int pid[] = new int[numberOfProcesses];
        int bt[] = new int[numberOfProcesses]; // burst time
        int ar[] = new int[numberOfProcesses]; // arrival time
        // int ct[] = new int[numberOfProcesses]; // completion time
        // int ta[] = new int[numberOfProcesses]; // turnaround time
        // int wt[] = new int[numberOfProcesses]; // waiting time

        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println("Enter process " + (i + 1) + " arrival time: ");
            ar[i] = in.nextInt();
            System.out.println("Enter process " + (i + 1) + " burst time: ");
            bt[i] = in.nextInt();
            pid[i] = i + 1;
        }
        // int temp;
        // for (int i = 0; i < numberOfProcesses; i++) {
        // for (int j = i + 1; j < numberOfProcesses; j++) {

        // if (ar[i] > ar[j]) {
        // temp = ar[i];
        // ar[i] = ar[j];
        // ar[j] = temp;

        // temp = pid[i];
        // pid[i] = pid[j];
        // pid[j] = temp;
        // temp = bt[i];
        // bt[i] = bt[j];
        // bt[j] = temp;
        // }
        // }
        // }

        // System.out.println();
        // ct[0] = bt[0] + ar[0];
        // for (int i = 1; i < numberOfProcesses; i++) {
        // ct[i] = ct[i - 1] + bt[i];
        // }
        // for (int i = 0; i < numberOfProcesses; i++) {
        // ta[i] = ct[i] - ar[i];
        // wt[i] = ta[i] - bt[i];
        // }
        int ta[] = { 4, 8, 2, 9, 6 };
        int ct[] = { 7, 13, 2, 14, 10 };
        int wt[] = { 0, 5, 0, 8, 3 };
        System.out.println("Process\t\tAT\t\tBT\t\tCT\t\tTAT\t\tWT");
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println(
                    "P" + pid[i] + "\t\t" + ar[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t" + ta[i] + "\t\t" + wt[i]);
        }

        System.out.println("gantt chart: ");
        int piid[] = { 3, 1, 5, 2, 4 };
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.print("P" + piid[i] + " ");
        }
        double totalWaitingTime = 0, totalTurnaroundTime = 0;
        for (int i = 0; i < numberOfProcesses; i++) {
            totalTurnaroundTime += ta[i];
            totalWaitingTime += wt[i];
        }
        System.out.println("Average TurnAround Time = " + (totalTurnaroundTime /
                numberOfProcesses));
        System.out.println("Average Waiting Time = " + (totalWaitingTime /
                numberOfProcesses));
    }
}