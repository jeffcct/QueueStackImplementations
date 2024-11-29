import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class GenericBag<Item> implements Iterable<Item> {
    int size;
    Item[] items;

    @SuppressWarnings("unchecked")
    public GenericBag() {
        this.size = 0;
        this.items = (Item[]) new Object[1];
    }

    private void doubleSize() {
        @SuppressWarnings("unchecked")
        Item[] newItems = (Item[]) new Object[this.items.length * 2];
        for (int i = 0; i < this.size; i++) {
            newItems[i] = this.items[i];
        }
        this.items = newItems;
    }

    public void push(Item item) {
        if (this.size >= this.items.length) {
            this.doubleSize();
        }
        this.items[this.size] = item;
        this.size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public int memorySize() {
        return this.items.length;
    }

    public void debug() {
        StdOut.printf("items: %s%n", Arrays.toString(items));
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator() {
            
        };
    }

    private class ListIterator implements Iterator<Item> {
        int index;

        private ListIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return this.index < size;
        }

        @Override
        public Item next() {
            Item out = items[this.index];
            this.index += 1;
            return out;
        }
    }

    public static void main(String[] args) {
        //String[] instructions = StdIn.readAllLines();
        ArrayStack<String> items = new ArrayStack<String>();

        while (true) {
            String[] parts = StdIn.readLine().split(" ");
            switch (parts[0]) {
                case "push":
                    items.push(parts[1]);
                    StdOut.printf("Pushed String '%s'.%n", parts[1]);
                    break;
                case "pop":
                    String popped = items.pop();
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
