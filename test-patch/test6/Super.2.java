import java.lang.System;

class Hyper {
    int Padding = 0;
    public void Print() {
        System.out.println(Padding);
    }
}

public class Super extends Hyper {
    public int Field = 0;
    public void Print() {
        super.Print();
        System.out.println(Field);
    }
}