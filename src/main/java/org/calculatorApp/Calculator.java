package org.calculatorApp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    private final Class<?> handler;
    private final Object handlerInstance;
    private final List<Double> results;

    public Calculator(
            String handlerName, int size
    ) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.handler = Class.forName(handlerName);
        this.handlerInstance = handler.getConstructor().newInstance();
        this.results = Arrays.asList(new Double[size]);
    }

    synchronized public void compute(
            String methodName, Integer commandIdThis
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = handler.getDeclaredMethod(methodName);
        results.set(commandIdThis, (Double) method.invoke(handlerInstance));
        notifyAll();
    }

    synchronized public void compute( // Мб немного перенести sync, чтобы получения методов и создания массивов находились вне него
            String methodName, Integer commandIdThis, Integer commandId1, Integer commandId2
    ) throws InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Double[] args = new Double[2];
        while (results.get(commandId1) == null) {
            wait();
        }
        args[0] = results.get(commandId1);
        while (results.get(commandId2) == null) {
            wait();
        }
        args[1] = results.get(commandId2);
        Class<?>[] argTypes = new Class[2];
        argTypes[0] = Double.class;
        argTypes[1] = Double.class;
        Method method = handler.getDeclaredMethod(methodName, argTypes);
        results.set(commandIdThis, (Double) method.invoke(handlerInstance, (Object[]) args));
    }

    public List<Double> getResults() {
        return results;
    }

    public Double getResult(int index) {
        return results.get(index);
    }

}
