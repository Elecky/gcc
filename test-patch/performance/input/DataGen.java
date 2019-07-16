import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;;

class DataGen {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Require One Argument");
        }
        Random rand = new Random();
        int length = Integer.parseInt(args[0]);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("data" + length + ".txt"));
            writer.write(Integer.toString(length));
            writer.write('\n');
            for (int idx = 0; idx < length; ++idx) {
                writer.write(Integer.toString(rand.nextInt(0x7fffffff)));
                writer.write('\n');  
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}