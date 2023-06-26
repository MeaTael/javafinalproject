package handlers;

public class advancedHandler extends basicHandler {

    public Double sqr(Double a) { return a * a; }

    public Double sqrt(Double a) { return Math.sqrt(a); }

    public Double sin(Double a) { return Math.sin(a); }

    public Double cos(Double a) { return Math.cos(a); }

    public Double tg(Double a) { return Math.tan(a); }

    public Double asin(Double a) { return Math.asin(a); }

    public Double acos(Double a) { return Math.acos(a); }

    public Double arctg(Double a) { return Math.atan(a); }

    public Double abs(Double a) { return a >= 0 ? a : -a; }

}