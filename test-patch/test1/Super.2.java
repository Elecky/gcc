import java.lang.System;

class Hyper {
    void hello() { System.out.println("hello from Hyper"); }
}

public class Super extends Hyper {
    void hello() { System.out.println("hello from Super"); }    
}