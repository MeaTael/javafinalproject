package handlers;

import java.util.concurrent.ThreadLocalRandom;

public class basicHandler {

    public Double rnd() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public Double mul(Double a, Double b) {
        return a * a;
    }

    public Double sum(Double a, Double b) {
        return a + b;
    }

}
