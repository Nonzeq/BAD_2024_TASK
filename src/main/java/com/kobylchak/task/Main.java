package com.kobylchak.task;

import com.kobylchak.task.data.TaskData;
import com.kobylchak.task.service.TaskResolverService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        System.out.print("Absolute path to file: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathToFile = reader.readLine();
        
        long start = System.currentTimeMillis();
        TaskResolverService taskResolver = new TaskResolverService(pathToFile);
        TaskData data = taskResolver.solve();
        logger.info("Data: {}", data);
        long end = System.currentTimeMillis();
        logger.info("Working time: {}s", (end - start) / 1000.0);
        System.out.println(data);
    }
}
