import java.lang.System;

class Hyper {
    public void Print(double arg) {
        System.out.println("print from Hyper");
    }
}

public class Super extends Hyper {
    public void Print(double arg) {
        System.out.println("print from Super");
    }
}