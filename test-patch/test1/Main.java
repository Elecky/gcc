// import Super;

public class Main extends Super {
    public static void main(String[] args) {
        (new Main()).hello();
    }

    void hello() {
        super.hello();
    }
}