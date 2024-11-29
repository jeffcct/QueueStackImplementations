import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class tester {
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        int n = 16;
        for (int i = 0; i < n; i++) {
            deque.addLast(i);
            deque.debug();
        }
        Iterator<Integer> it = deque.iterator();
        while (it.hasNext()) {
            StdOut.printf("%d%n", it.next());
        }
    }
}
