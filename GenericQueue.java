import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


interface OrderedList<Item> {
    abstract Item pop();
    abstract void push(Item item);
    abstract boolean isEmpty();
    abstract int size();
    abstract int memorySize();

}

public class GenericQueue<Item> implements OrderedList<Item>, Iterable<Item> {
    int size;
    int head;
    int tail;
    int capacity;
    Item[] items;

    @SuppressWarnings("unchecked")
    public GenericQueue() {
        this.size = 0;
        this.tail = 0;
        this.head = 0;
        this.capacity = 1;
        this.items = (Item[]) new Object[this.capacity];
    }    

    private void halfSize() {
        //this.debug();
        if (this.size >= this.capacity / 2) {
            return;
        }
        @SuppressWarnings("unchecked")
        Item[] newItems = (Item[]) new Object[this.capacity / 2];
        for (int off = 0; off < this.size; off++) {
            int index = (this.head + off) % this.capacity; 
            newItems[off] = this.items[index];
        }
        this.capacity /= 2;
        this.tail = this.size;
        this.head = 0;
        this.items = newItems;
        //this.debug();
    }

    private void doubleSize() {
        //this.debug();
        @SuppressWarnings("unchecked")
        Item[] newItems = (Item[]) new Object[this.capacity * 2];
        for (int off = 0; off < this.size; off++) {
            int index = (this.head + off) % this.capacity; 
            newItems[off] = this.items[index];
        }
        this.capacity *= 2;
        this.tail = this.size;
        this.head = 0;
        this.items = newItems;
        //this.debug();
    }



    public void enqueue(Item item) {
        if (this.size >= this.capacity - 1) {
            this.doubleSize();
        }
        this.items[this.tail] = item;
        this.tail = (this.tail + 1) % this.capacity;
        this.size += 1;
    }

    public Item dequeue() {
        if (this.isEmpty()) {
            return null;
        }

        if (this.size <= this.capacity / 4) {
            this.halfSize();
        }

        Item out = this.items[this.head];
        this.head = (this.head + 1) % this.capacity;
        this.size -= 1;
        return out;
    }

    public void push(Item T) {
        this.enqueue(T);
    }

    public Item pop() {
        return this.dequeue();
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

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator() {
            
        };
    }

    private class ListIterator implements Iterator<Item> {
        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public Item next() {
            return pop();
        }
    }

    public static void main(String[] args) {
        //String[] instructions = StdIn.readAllLines();
        GenericQueue<String> items = new GenericQueue<String>();

        while (true) {
            String[] parts = StdIn.readLine().split(" ");
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
                case "popall":
                    for (String item : items) {
                        StdOut.printf("Popped %s%n", item);
                    }
                    break;
                case "stop":
                case "terminate":
                case "end":
                    return;
                default:
                    break;
            }
        }
    }
}
