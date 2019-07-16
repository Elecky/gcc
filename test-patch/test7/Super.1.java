import java.lang.System;

class Hyper {
    public void Bar() {
        System.out.println("Bar from Hyper");
    }
}

public class Super extends Hyper {
    public void Foo() {
        System.out.println("Foo from Super");
    }
}