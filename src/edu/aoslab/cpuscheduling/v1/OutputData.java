package edu.aoslab.cpuscheduling.v1;

public class OutputData {

    private double totalWaitingTime;
    private double totalTurnaround;
    private double avgWaitingTime;
    private double avgTurnaround;

    public OutputData() {
    }

    public OutputData(double totalWaitingTime, double totalTurnaround, double avgWaitingTime, double avgTurnaround) {
        this.totalWaitingTime = totalWaitingTime;
        this.totalTurnaround = totalTurnaround;
        this.avgWaitingTime = avgWaitingTime;
        this.avgTurnaround = avgTurnaround;
    }

    public double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public double getTotalTurnaround() {
        return totalTurnaround;
    }

    public double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public double getAvgTurnaround() {
        return avgTurnaround;
    }
}
