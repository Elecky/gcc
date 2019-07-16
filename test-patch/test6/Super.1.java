import java.lang.System;

class Hyper {
    public void Print() {}
}

public class Super extends Hyper {
    public int Field = 0;
    public void Print() {
        super.Print();
        System.out.println(Field);
    }
}