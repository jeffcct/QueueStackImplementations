import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueOfStrings {
    int size;
    int head;
    int tail;
    int capacity;
    String[] items;

    public QueueOfStrings() {
        this.size = 0;
        this.tail = 0;
        this.head = 0;
        this.capacity = 1;
        this.items = new String[this.capacity];
    }    

    private void halfSize() {
        //StdOut.printf("%nStarted to half size with size %d.%n", this.size);
        //StdOut.printf("start: %d, end: %d.%n", this.head, this.tail);
        //StdOut.printf("items: %s%n", Arrays.toString(items));
        if (this.size >= this.capacity / 2) {
            return;
        }
        String[] newItems = new String[this.capacity / 2];
        for (int off = 0; off < this.size; off++) {
            int index = (this.head + off) % this.capacity; 
            newItems[off] = this.items[index];
            //StdOut.printf("moved %s to position %d%n", newItems[index], off);
        }
        this.capacity /= 2;
        this.tail = this.size;
        this.head = 0;
        this.items = newItems;
        //StdOut.printf("start: %d, end: %d.%n", this.head, this.tail);
        //StdOut.printf("items: %s%n", Arrays.toString(items));
        //StdOut.printf("Finished Halving size%n%n");
    }

    private void doubleSize() {
        //StdOut.printf("%nStarted Doubling Size with size %d%n", this.size);
        //StdOut.printf("start: %d, end: %d.%n", this.head, this.tail);
        //StdOut.printf("items: %s%n", Arrays.toString(items));
        String[] newItems = new String[this.capacity * 2];
        for (int off = 0; off < this.size; off++) {
            int index = (this.head + off) % this.capacity; 
            newItems[off] = this.items[index];
            //StdOut.printf("moved %s to position %d%n", newItems[off], off);
        }
        this.capacity *= 2;
        this.tail = this.size;
        this.head = 0;
        this.items = newItems;
        //StdOut.printf("start: %d, end: %d.%n", this.head, this.tail);
        //StdOut.printf("items: %s%n", Arrays.toString(items));
        //StdOut.printf("Finished Doubling Size%n%n");
    }



    public void enqueue(String item) {
        if (this.size >= this.capacity - 1) {
            this.doubleSize();
        }
        this.items[this.tail] = item;
        this.tail = (this.tail + 1) % this.capacity;
        this.size += 1;
    }

    public String dequeue() {
        if (this.isEmpty()) {
            return "No items in Queue.";
        }

        if (this.size <= this.capacity / 4) {
            this.halfSize();
        }

        String out = this.items[this.head];
        this.head = (this.head + 1) % this.capacity;
        this.size -= 1;
        return out;
    }

    public boolean isEmpty() {
        return this.tail == this.head;
    }

    public int size() {
        return this.size;
    }

    public int memorySize() {
        return this.capacity;
    }

    public void debug() {
        StdOut.printf("assumed capacity: %d, real capacity: %d.%n", this.capacity, this.items.length);
        StdOut.printf("start: %d, end: %d.%n", this.head, this.tail);
        StdOut.printf("items: %s%n", Arrays.toString(items));
    }

    public static void main(String[] args) {
        String[] instructions = StdIn.readAllLines();
        QueueOfStrings items = new QueueOfStrings();

        for (String instruction : instructions) {
            String[] parts = instruction.split(" ");
            switch (parts[0]) {
                case "push":
                    items.enqueue(parts[1]);
                    StdOut.printf("Pushed String '%s'.%n", parts[1]);
                    break;
                case "pop":
                    String popped = items.dequeue();
                    StdOut.printf("Popped String was '%s'.%n", popped);
                    break;
                case "size":
                    int size = items.size();
                    StdOut.printf("There are %d items in items.%n", size);
                    break;
                case "isEmpty":
                    if (items.isEmpty()) {
                        StdOut.printf("Items is empty.%n");
                    } else {
                        StdOut.printf("Items is not empty.%n");
                    }
                    break;
                case "memorySize":
                    StdOut.printf("Memory allocated is %d.%n", items.memorySize());
                    break;
                case "debug":
                    items.debug();
                    break;
                default:
                    break;
            }
        }
    }

}
