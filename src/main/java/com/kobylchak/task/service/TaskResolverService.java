package com.kobylchak.task.service;

import com.kobylchak.task.data.TaskData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;

public class TaskResolverService {
    private final String pathToFile;
    
    public TaskResolverService(String pathToFile) {
        this.pathToFile = pathToFile;
    }
    
    public TaskData solve() {
        TaskData result = new TaskData();
        int size = 0;
        int currSum = 0;
        int[] numbers;
        File file = new File(pathToFile);
        int lineCount = getLineCount(file);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            numbers = new int[lineCount];
            String line;
            while ((line = br.readLine()) != null) {
                int curr = Integer.parseInt(line, 10);
                numbers[size++] = curr;
                currSum += curr;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error were throw during file reading", e);
        }
        
        result.setAscendingSequence(getMaxAscendingSequence(numbers));
        result.setDescendingSequence(getMaxDescendingSequence(numbers));
        Arrays.sort(numbers);
        result.setMaxNumber(numbers[numbers.length - 1]);
        result.setMinNumber(numbers[0]);
        result.setAvg(currSum / lineCount);
        int median = (numbers.length % 2 == 0)
                     ? (numbers[numbers.length / 2 - 1] + numbers[numbers.length / 2]) / 2
                     : numbers[numbers.length / 2];
        result.setMedian(median);
        return result;
    }
    
    private int[] getMaxDescendingSequence(int[] numbers) {
        int[] res = new int[0];
        int left = 0;
        int right = left + 1;
        int curr = 0;
        while (right < numbers.length) {
            if (numbers[curr++] < numbers[right]) {
                if (right - left > res.length) {
                    res = Arrays.copyOfRange(numbers, left, right);
                }
                left = right;
            }
            right++;
        }
        if (right - left > res.length) {
            res = Arrays.copyOfRange(numbers, left, right);
        }
        return res;
    }
    
    private int[] getMaxAscendingSequence(int[] numbers) {
        int[] res = new int[0];
        int left = 0;
        int right = left + 1;
        int curr = 0;
        while (right < numbers.length) {
            if (numbers[curr++] > numbers[right]) {
                if (right - left > res.length) {
                    res = Arrays.copyOfRange(numbers, left, right);
                }
                left = right;
            }
            right++;
        }
        if (right - left > res.length) {
            res = Arrays.copyOfRange(numbers, left, right);
        }
        return res;
    }
    
    private int getLineCount(File file) {
        try (LineNumberReader lineNumberReader =
                     new LineNumberReader(new FileReader(file))) {
            lineNumberReader.skip(Long.MAX_VALUE);
            return lineNumberReader.getLineNumber();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
