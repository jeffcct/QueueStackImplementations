import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int numWords = Integer.parseInt(args[0]);
        RandomizedQueue<String> items = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            items.enqueue(StdIn.readString());
        }
        for (int i = 0; i < numWords; i++) {
            StdOut.printf("%s%n", items.dequeue());
        }
    }
}
