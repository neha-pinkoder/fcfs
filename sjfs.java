import java.util.Scanner;

class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int waitingTime;
    int turnAroundTime;
    int timetoComplete;
    int CompletionTime = 0;

    Process(int pid, int arrt, int bur) {
        this.pid = pid;
        this.arrivalTime = arrt;
        this.burstTime = bur;
        this.timetoComplete = burstTime;
    }
}

public class sjfs {
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of processes: ");
        int n = s.nextInt();
        Process[] myProcess = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time and Burst Time");
            int arrt = s.nextInt();
            int bur = s.nextInt();
            myProcess[i] = new Process(i + 1, arrt, bur);
        }

        // SJF(myProcess);
        SJF(myProcess);
        s.close();
    }

    static void SRTF(Process myProcess[]) {
        int curTimeInterval = 0, completedProcesses = 0;
        float totalTurnaroundTime = 0, totalWaitingTime = 0;
        Process curProcess;

        // traverse till all process gets completely executed.
        curProcess = myProcess[0];
        while (completedProcesses < myProcess.length) {
            for (int i = 0; i < myProcess.length; i++) {
                if (myProcess[i].timetoComplete > 0) {
                    curProcess = myProcess[i];
                    break;
                }
            }
            System.out.println("Current Time Interval = " + curTimeInterval);
            System.out.println("No. of processes completed = " + completedProcesses);

            // find process with minimum remaining time at every single time lap

            for (int i = 0; i < myProcess.length; i++) {
                if (myProcess[i].arrivalTime > curTimeInterval || myProcess[i].timetoComplete == 0) {
                    continue;
                }
                if (myProcess[i].timetoComplete < curProcess.timetoComplete) {
                    curProcess = myProcess[i];
                }
            }

            // Reduce time by 1
            curProcess.timetoComplete -= 1;
            // check if its remaining time becomes 0
            if (curProcess.timetoComplete == 0) {
                // increment the counter of process completion
                completedProcesses++;
                // completion time of current process = current_time +1;
                curProcess.CompletionTime = curTimeInterval + 1;
            }
            curTimeInterval++;
        }
        for (int i = 0; i < myProcess.length; i++) {
            // calculate waiting time for each process
            // waitingTime time = completion time - arrival time - burst_time
            myProcess[i].waitingTime = myProcess[i].CompletionTime - myProcess[i].arrivalTime - myProcess[i].burstTime;

            // find turnAroundTime time = waiting time - burst time
            myProcess[i].turnAroundTime = myProcess[i].waitingTime + myProcess[i].burstTime;
            System.out.println("Process " + myProcess[i].pid + ": ");
            System.out.println("turnAroundTime\tCompletion\twaitingTime");
            System.out.println(myProcess[i].turnAroundTime + "\t\t\t" + myProcess[i].CompletionTime + "\t\t"
                    + myProcess[i].waitingTime);
        }
        for (int n = 0; n < myProcess.length; n++) {
            totalTurnaroundTime += myProcess[n].turnAroundTime;
            totalWaitingTime += myProcess[n].waitingTime;
        }
        System.out.println("Average TurnAround Time = " + (totalTurnaroundTime / myProcess.length));
        System.out.println("Average Waiting Time = " + (totalWaitingTime / myProcess.length));
    }

    static void FCFS(Process myProcess[]) {
        int x = 0;
        float totalTurnaroundTime = 0, totalWaitingTime = 0;
        // arrange prcoess acc to their arrival time in ascending order
        Process temp;
        for (int i = 0; i < myProcess.length; i++) {
            for (int j = i; j < myProcess.length; j++) {
                if (myProcess[i].arrivalTime > myProcess[j].arrivalTime) {
                    temp = myProcess[i];
                    myProcess[j] = myProcess[i];
                    myProcess[i] = temp;
                }
            }
        }
        for (int i = 0; i < myProcess.length; i++) {
            x = x + myProcess[i].burstTime;
            myProcess[i].CompletionTime = x;
            myProcess[i].turnAroundTime = myProcess[i].CompletionTime - myProcess[i].arrivalTime;
            myProcess[i].waitingTime = myProcess[i].turnAroundTime - myProcess[i].burstTime;
            System.out.println("Process " + myProcess[i].pid + ": ");
            System.out.println("turnAroundTime\tCompletion\twaitingTime");
            System.out.println(myProcess[i].turnAroundTime + "\t\t\t" + myProcess[i].CompletionTime + "\t\t"
                    + myProcess[i].waitingTime);
        }

        for (int n = 0; n < myProcess.length; n++) {
            totalTurnaroundTime += myProcess[n].turnAroundTime;
            totalWaitingTime += myProcess[n].waitingTime;
        }
        System.out.println("Average TurnAround Time = " + (totalTurnaroundTime / myProcess.length));
        System.out.println("Average Waiting Time = " + (totalWaitingTime / myProcess.length));

    }

    static void SJF(Process myProcess[]) {
        int curTimeInterval = 0, completedProcesses = 0;
        float totalTurnaroundTime = 0, totalWaitingTime = 0;
        Process curProcess;

        // traverse till all process gets completely executed.
        curProcess = myProcess[0];
        while (completedProcesses < myProcess.length) {
            for (int i = 0; i < myProcess.length; i++) {
                if (myProcess[i].timetoComplete > 0) {
                    curProcess = myProcess[i];
                    break;
                }
            }
            System.out.println("Current Time Interval = " + curTimeInterval);
            System.out.println("No. of processes completed = " + completedProcesses);

            // Reduce time by 1
            curProcess.timetoComplete -= 1;
            // check if its remaining time becomes 0
            if (curProcess.timetoComplete == 0) {
                // increment the counter of process completion
                completedProcesses++;
                // completion time of current process = current_time +1;
                curProcess.CompletionTime = curTimeInterval + 1;
            }
            curTimeInterval++;
        }
        for (int i = 0; i < myProcess.length; i++) {
            // calculate waiting time for each process
            // waitingTime time = completion time - arrival time - burst_time
            myProcess[i].waitingTime = myProcess[i].CompletionTime - myProcess[i].arrivalTime - myProcess[i].burstTime;

            // find turnAroundTime time = waiting time - burst time
            myProcess[i].turnAroundTime = myProcess[i].waitingTime + myProcess[i].burstTime;
            System.out.println("Process " + myProcess[i].pid + ": ");
            System.out.println("turnAroundTime\tCompletion\twaitingTime");
            System.out.println(myProcess[i].turnAroundTime + "\t\t\t" + myProcess[i].CompletionTime + "\t\t"
                    + myProcess[i].waitingTime);
        }
        for (int n = 0; n < myProcess.length; n++) {
            totalTurnaroundTime += myProcess[n].turnAroundTime;
            totalWaitingTime += myProcess[n].waitingTime;
        }
        System.out.println("Average TurnAround Time = " + (totalTurnaroundTime / myProcess.length));
        System.out.println("Average Waiting Time = " + (totalWaitingTime / myProcess.length));
    }

}
