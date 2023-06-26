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
            String methodName, Integer commandIdThis, List<Integer> commandIds
    ) throws InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Double[] args = new Double[commandIds.size()];
        Class<?>[] argTypes = new Class[commandIds.size()];
        for (int i = 0; i < commandIds.size(); ++i) {
            Integer commandId = commandIds.get(i);
            while (results.get(commandId) == null) {
                wait();
            }
            args[i] = results.get(commandId);
            argTypes[i] = Double.class;
        }
        Method method = handler.getDeclaredMethod(methodName, argTypes);
        results.set(commandIdThis, (Double) method.invoke(handlerInstance, (Object[]) args));
        notifyAll();
    }

    public List<Double> getResults() {
        return results;
    }

    public Double getResult(int index) {
        return results.get(index);
    }

}
