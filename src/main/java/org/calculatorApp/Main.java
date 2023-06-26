package org.calculatorApp;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String str;
        String class_fqcn;
        int command_id;

        // Creating ThreadPool and FileReader

        try (
            FileReader fileReader = new FileReader(args[0]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ExecutorService executorService = Executors.newCachedThreadPool()
        ) {

            // Reading first line

            if ((str = bufferedReader.readLine()) != null) {
                String[] splitted = str.split(",");
                class_fqcn = splitted[0];
                command_id = Integer.parseInt(splitted[1]);
            } else {
                System.out.println("File " + args[0] + " is empty");
                return;
            }

            // Creating array for commands
            List<String> commands = new ArrayList<>();

            // Reading commands
            while ((str = bufferedReader.readLine()) != null) {
                commands.add(str);
            }

            // Creating Calculator instance
            Calculator calculator = new Calculator(class_fqcn, commands.size());

            // Parsing commands and creating threads for each
            for (int i = 0; i < commands.size(); ++i) {
                String[] splitted = commands.get(i).split(",");
                String method = splitted[0];
                String[] paramstrings = Arrays.copyOfRange(splitted, 1, splitted.length);
                int[] params = Arrays.stream(paramstrings).mapToInt(Integer::parseInt).toArray();
                CalculationThread calculationThread = new CalculationThread(method, i, params, calculator);
                // Running created thread
                executorService.submit(calculationThread);
            }

            // Prohibit accepting new tasks
            executorService.shutdown();

            // Waiting termination of tasks
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }

            // Just to make sure each command worked
            //System.out.println(calculator.getResults());
            // Printing final result
            System.out.println(calculator.getResult(command_id));

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}