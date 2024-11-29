import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class StackOfStrings {
    int size;
    LinkedList<String> items;

    public StackOfStrings() {
        this.size = 0;
        this.items = new LinkedList<String>(null);
    }

    public void push(String item) {
        this.size++;
        this.items = new LinkedList<String>(item, this.items);
    }

    public String pop() {
        if (this.isEmpty()) {
            return "No string in Stack";
        }
        this.size--;
        String out = this.items.item;
        this.items = this.items.next;
        return out;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        String[] instructions = StdIn.readAllLines();
        StackOfStrings items = new StackOfStrings();

        for (String instruction : instructions) {
            String[] parts = instruction.split(" ");
            switch (parts[0]) {
                case "push":
                    items.push(parts[1]);
                    StdOut.printf("Pushed String %s%n", parts[1]);
                    break;
                case "pop":
                    String popped = items.pop();
                    StdOut.printf("Popped String was '%s'%n", popped);
                    break;
                case "size":
                    int size = items.size();
                    StdOut.printf("There are %d items in items%n", size);
                    break;
                case "isEmpty":
                    if (items.isEmpty()) {
                        StdOut.printf("Items is empty%n");
                    } else {
                        StdOut.printf("Items is not empty%n");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

class LinkedList<T> {
    T item;
    LinkedList<T> next;
 
    public LinkedList(T item, LinkedList<T> next) {
        this.item = item;
        this.next = next;
    }

    public LinkedList(T item) {
        this.item = item;
        this.next = null;
    }

    public boolean hasNext() {
        return this.next != null && this.next.item != null;
    }
}