package com.kobylchak.task.data;

import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskData {
    private int maxNumber;
    private int minNumber;
    private int median;
    private int avg;
    private int[] descendingSequence;
    private int[] ascendingSequence;
    
    @Override
    public String toString() {
        return "Result: \n" +
               "maxNumber=" + maxNumber + "\n" +
               "minNumber=" + minNumber + "\n" +
               "median=" + median + "\n" +
               "avg=" + avg + "\n" +
               "max descending sequence=" + Arrays.toString(descendingSequence) + "\n" +
               "max ascending sequence=" + Arrays.toString(ascendingSequence) + "\n";
    }
}
