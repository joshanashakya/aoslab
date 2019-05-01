package edu.aoslab.cpuscheduling.v1;

public interface CpuSchedule {

    InputData getData ();
    InputData getDataForPriorityBased ();
    InputData getDataWithQuantum ();
    OutputData fcfsSchedule (InputData inputData);
    OutputData sjfSchedule (InputData inputData);
    OutputData prioritySchedule (InputData inputData);
    OutputData roundRobinSchedule (InputData inputData);
    void print (OutputData outputData);
}
