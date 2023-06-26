package org.calculatorApp;

import java.lang.reflect.InvocationTargetException;

public class CalculationThread extends Thread {
    private final String method;
    private final int commandIdThis;
    private final int[] commandIds;

    private final Calculator calculator;

    public CalculationThread(String method, int commandIdThis, int[] commandIds, Calculator calculator) {
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
