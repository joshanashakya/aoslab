package edu.aoslab.cpuscheduling.v1;

import java.util.Scanner;

public class CpuScheduleImpl implements CpuSchedule {

    @Override
    public InputData getData() {
        Scanner sc = new Scanner(System.in);
        int noOfProcess;
        System.out.print("Enter the number of processes: ");
        noOfProcess = sc.nextInt();
        System.out.println("Enter burst time for processes: ");
        int[] burstTime = new int[noOfProcess];
        for (int i = 0; i< noOfProcess; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
        }
        return new InputData(noOfProcess, burstTime);
    }

    @Override
    public InputData getDataForPriorityBased() {
        Scanner sc = new Scanner(System.in);
        int noOfProcess;
        System.out.print("Enter the number of processes: ");
        noOfProcess = sc.nextInt();
        System.out.println("Enter burst time and priority for processes: ");
        int[] burstTime = new int[noOfProcess];
        int[] priorities = new int[noOfProcess];
        int largestValue = 0;
        for (int i = 0; i< noOfProcess; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            priorities[i] = sc.nextInt();
            if (largestValue < priorities[i]) {
                largestValue = priorities[i];
            }
        }
        return new InputData(noOfProcess, burstTime, true, priorities, largestValue);
    }

    @Override
    public InputData getDataWithQuantum() {
        Scanner sc = new Scanner(System.in);
        int noOfProcess;
        System.out.print("Enter the number of processes: ");
        noOfProcess = sc.nextInt();
        System.out.println("Enter burst time for processes: ");
        int[] burstTime = new int[noOfProcess];
        for (int i = 0; i< noOfProcess; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
        }
        System.out.println("Enter value of quantum: ");
        int quantum = sc.nextInt();
        return new InputData(noOfProcess, burstTime, quantum);
    }

    @Override
    public OutputData fcfsSchedule(InputData inputData) {
        int noOfProcess = inputData.getNoOfProcess();
        int[] burstTime = inputData.getBursttime();
        int[] waitingTime = new int[noOfProcess];
        double totalWaitingTime = 0;
        double totalTurnaround = 0;
        waitingTime[0] = 0;

        for (int i = 1; i< noOfProcess; i++) {
            waitingTime[i] = waitingTime[i-1] + burstTime[i-1];
            totalWaitingTime += waitingTime[i];
        }

        for (int i = 0; i< noOfProcess; i++) {
            totalTurnaround += waitingTime[i] + burstTime[i];
        }

        double avgWaitingTime = totalWaitingTime/noOfProcess;
        double avgTurnaround = totalTurnaround/noOfProcess;

        return new OutputData(totalWaitingTime, totalTurnaround, avgWaitingTime, avgTurnaround);
    }

    @Override
    public OutputData sjfSchedule(InputData inputData) {
        int noOfProcess = inputData.getNoOfProcess();
        int[] burstTime = inputData.getBursttime();
        int[] waitingTime = new int[noOfProcess];
        double totalWaitingTime = 0;
        double totalTurnaround = 0;
        waitingTime[0] = 0;

        for (int i = 0; i< noOfProcess -1; i++) {
            for (int j = 1; j< noOfProcess; j++) {
                if(burstTime[i] > burstTime[j]) {
                    int temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;
                }
            }
        }

        for (int i = 1; i< noOfProcess; i++) {
            waitingTime[i] = waitingTime[i-1] + burstTime[i-1];
            totalWaitingTime += waitingTime[i];
        }

        for (int i = 0; i< noOfProcess; i++) {
            totalTurnaround += waitingTime[i] + burstTime[i];
        }

        double avgWaitingTime = totalWaitingTime/noOfProcess;
        double avgTurnaround = totalTurnaround/noOfProcess;
        return new OutputData(totalWaitingTime, totalTurnaround, avgWaitingTime, avgTurnaround);
    }

    @Override
    public OutputData prioritySchedule(InputData inputData) {
        int noOfProcess = inputData.getNoOfProcess();
        int[] burstTime = inputData.getBursttime();
        int[] priorities = inputData.getPriorities();
        int largestValue = inputData.getLargestPriorityValue();
        int[] waitingTime = new int[noOfProcess];
        double totalWaitingTime = 0;
        double totalTurnaround = 0;
        int calculatedWaitingTime = 0;
        waitingTime[0] = 0;

        for (int i = 1; i<= largestValue; i++) {
            for (int j = 0; j < noOfProcess; j++) {
                if (priorities[j] == i) {
                    waitingTime[j] = calculatedWaitingTime;
                    calculatedWaitingTime += burstTime[j];
                    totalWaitingTime += waitingTime[j];
                }
            }
        }

        double avgWaitingTime = totalWaitingTime/noOfProcess;
        for (int i = 0; i< noOfProcess; i++) {
            totalTurnaround += waitingTime[i] + burstTime[i];
        }
        double avgTurnaround = totalTurnaround/noOfProcess;
        return new OutputData(totalWaitingTime, totalTurnaround, avgWaitingTime, avgTurnaround);
    }

    @Override
    public OutputData roundRobinSchedule(InputData inputData) {
        int noOfProcess = inputData.getNoOfProcess();
        int quantum = inputData.getQuantum();
        int[] burstTime = inputData.getBursttime();
        int[] waitingTime = new int[noOfProcess];
        double totalWaitingTime = 0;
        double totalTurnaround = 0;
        waitingTime[0] = 0;

        int[] remainingBurstTime = new int[noOfProcess];
        for (int i = 0; i< noOfProcess; i++) {
            remainingBurstTime[i] = burstTime[i];
        }
        int queue = noOfProcess;
        int i = 0;
        boolean[] isCompleted = new boolean[noOfProcess];
        int currentTime = 0;
        while (queue != 0) {
            int timespent;
            if(isCompleted[i]) {
                i = (i == noOfProcess-1) ? 0 : i + 1;
            }
            if(remainingBurstTime[i] >= quantum) {
                remainingBurstTime[i] -= quantum;
                timespent = quantum;
            } else {
                timespent = remainingBurstTime[i];
                remainingBurstTime[i] = 0;
            }
            currentTime += timespent;
            if(remainingBurstTime[i] == 0) {
                totalTurnaround = totalTurnaround + currentTime;
                totalWaitingTime = totalWaitingTime + (currentTime - burstTime[i]);
                isCompleted[i] = true;
                queue--;
            }
            i = (i == noOfProcess-1) ? 0 : i + 1;
        }

        double avgWaitingTime = totalWaitingTime/noOfProcess;
        double avgTurnaround = totalTurnaround/noOfProcess;
        return new OutputData(totalWaitingTime, totalTurnaround, avgWaitingTime, avgTurnaround);
    }

    @Override
    public void print(OutputData outputData) {
        System.out.println("Total waiting time: " + outputData.getTotalWaitingTime());
        System.out.println("Average waiting time: " + outputData.getAvgWaitingTime());
        System.out.println("Total turnaround:  " + outputData.getTotalTurnaround());
        System.out.println("Average turnaround: " + outputData.getAvgTurnaround());
    }
}
