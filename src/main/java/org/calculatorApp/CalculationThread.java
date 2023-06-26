package org.calculatorApp;

import java.lang.reflect.InvocationTargetException;

public class CalculationThread extends Thread {
    private final String method;
    private final Integer commandIdThis;
    private Integer commandId1 = null;
    private Integer commandId2 = null;

    private final Calculator calculator;

    public CalculationThread(String method, Integer commandIdThis, Calculator calculator) {
        this.method = method;
        this.commandIdThis = commandIdThis;
        this.calculator = calculator;
    }

    public CalculationThread(String method, Integer commandIdThis, Integer commandId1, Integer commandId2, Calculator calculator) {
        this.method = method;
        this.commandIdThis = commandIdThis;
        this.commandId1 = commandId1;
        this.commandId2 = commandId2;
        this.calculator = calculator;
    }

    @Override
    public void run() {
        try {
            if (commandId1 == null || commandId2 == null) {
                calculator.compute(method, commandIdThis);
            } else {
                calculator.compute(method, commandIdThis, commandId1, commandId2);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Thread " + commandIdThis + " was interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
