package edu.aoslab.cpuscheduling.v1;

public class InputData {

    private int noOfProcess;
    private int[] bursttime;
    private boolean isPriorityBased;
    private int[] priorities;
    private int largestPriorityValue;
    private int quantum;

    public InputData(int noOfProcess, int[] bursttime) {
        this.noOfProcess = noOfProcess;
        this.bursttime = bursttime;
    }

    public InputData(int noOfProcess, int[] bursttime, int quantum) {
        this.noOfProcess = noOfProcess;
        this.bursttime = bursttime;
        this.quantum = quantum;
    }

    public InputData(int noOfProcess, int[] bursttime, boolean isPriorityBased, int[] priorities, int largestPriorityValue) {
        this.noOfProcess = noOfProcess;
        this.bursttime = bursttime;
        this.isPriorityBased = isPriorityBased;
        this.priorities = priorities;
        this.largestPriorityValue = largestPriorityValue;
    }

    public int getNoOfProcess() {
        return noOfProcess;
    }

    public int[] getBursttime() {
        return bursttime;
    }

    public boolean isPriorityBased() {
        return isPriorityBased;
    }

    public int[] getPriorities() {
        return priorities;
    }

    public int getLargestPriorityValue() {
        return largestPriorityValue;
    }

    public int getQuantum() {
        return quantum;
    }
}
