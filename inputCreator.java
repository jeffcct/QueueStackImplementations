import edu.princeton.cs.algs4.StdOut;

public class inputCreator {
    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            StdOut.println("memorySize");
            StdOut.println("size");
            StdOut.printf("push %d%n", i);
        }
        for (int i = 0; i < 100; i++) {
           StdOut.println("pop");
           StdOut.printf("push Hello%d%n", i);
        }
        for (int i = 0; i < 100; i++) {
            StdOut.printf("push Bye%d%n", i);
        }
        StdOut.printf("debug");   
    }
}
