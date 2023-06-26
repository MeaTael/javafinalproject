package org.calculatorApp;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CalculationThread extends Thread {
    private final String method;
    private final Integer commandIdThis;
    private final List<Integer> commandIds;

    private final Calculator calculator;

    public CalculationThread(String method, Integer commandIdThis, List<Integer> commandIds, Calculator calculator) {
        this.method = method;
        this.commandIdThis = commandIdThis;
        this.commandIds = commandIds;
        this.calculator = calculator;
    }

    @Override
    public void run() {
        try {
            calculator.compute(method, commandIdThis, commandIds);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Thread " + commandIdThis + " was interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
