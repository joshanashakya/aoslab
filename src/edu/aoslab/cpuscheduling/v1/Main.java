package edu.aoslab.cpuscheduling.v1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Menu");
            System.out.println("1. FCFS");
            System.out.println("2. SJF");
            System.out.println("3. Priority");
            System.out.println("4. Round Robin");
            System.out.println("5. Variants of Round Robin");
            System.out.print("Choose CPU scheduling method: ");
            int methodValue = sc.nextInt();

            Main main = new Main();
            main.schedule(methodValue);
        }
    }

    private void schedule (int methodValue) {
        CpuSchedule cpuSchedule = new CpuScheduleImpl();
        InputData inputData;
        OutputData outputData;
        switch (methodValue) {
            case 1:
                inputData = cpuSchedule.getData();
                outputData = cpuSchedule.fcfsSchedule(inputData);
                cpuSchedule.print(outputData);
                break;
            case 2:
                inputData = cpuSchedule.getData();
                outputData = cpuSchedule.sjfSchedule(inputData);
                cpuSchedule.print(outputData);
                break;
            case 3:
                inputData = cpuSchedule.getDataForPriorityBased();
                outputData = cpuSchedule.prioritySchedule(inputData);
                cpuSchedule.print(outputData);
                break;
            case 4:
                inputData = cpuSchedule.getDataWithQuantum();
                outputData = cpuSchedule.roundRobinSchedule(inputData);
                cpuSchedule.print(outputData);
                break;
            case 5:
                break;
        }
    }
}