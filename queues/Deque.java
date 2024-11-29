// import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private int tail;
    private int head;
    private int capacity;
    private Item[] items;
    private int size;

    public Deque() {
        this.tail = 1;
        this.head = 0;
        this.size = 0;
        this.capacity = 2;
        this.items = (Item[]) new Object[this.capacity];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private void resize(int newCapacity) {
        if (this.size >= newCapacity) {
            return;
        }

        Item[] newItems = (Item[]) new Object[newCapacity];
        for (int off = 0; off < this.size; off++) {
            int index = (this.tail + off) % this.capacity; 
            newItems[off] = this.items[index];
        }
        this.capacity = newCapacity;
        this.tail = this.size == 0 ? 1 : 0; // edge case around size being 0.
        this.head = this.size;
        this.items = newItems;
    } 

    private void halfSize() {
        this.resize(this.capacity / 2);
    }

    private void doubleSize() {
        this.resize(this.capacity * 2);
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Attempted to add null to Deque.");
        }
        if (this.size >= this.capacity) {
            this.doubleSize();
        }

        if (this.size == 0) { // If tail = head, then we want to push both the tail and the head.
            this.tail = (this.capacity + this.tail - 1) % this.capacity;
        }

        this.items[head] = item;
        this.head = (this.head + 1) % this.capacity;
        this.size += 1;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Attempted to add null to Deque.");
        }
        if (this.size >= this.capacity) {
            this.doubleSize();
        }

        if (this.size == 0) {
            this.head = (this.head + 1) % this.capacity;
        }

        this.tail = (this.capacity + this.tail - 1) % this.capacity;
        this.items[tail] = item;
        this.size += 1;
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty.");
        }
        if (this.size <= this.capacity / 4) {
            this.halfSize();
        }

        this.head = (this.capacity + this.head - 1) % this.capacity;
        Item out = this.items[this.head];
        this.items[this.head] = null;
        this.size -= 1;
        if (this.size == 0) {
            this.tail = 1;
            this.head = 0;
        }
        return out;
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty.");
        }
        if (this.size <= this.capacity / 4) {
            this.halfSize();
        }

        Item out = this.items[this.tail];
        this.items[this.tail] = null;
        this.tail = (this.tail + 1) % this.capacity;
        this.size -= 1;
        if (this.size == 0) {
            this.tail = 1;
            this.head = 0;
        }
        return out;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // public void debug() {
    //   StdOut.printf("%s%n", Arrays.toString(this.items));
    // }

    private class DequeIterator implements Iterator<Item> {
        int index;

        private DequeIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return this.index < size;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException("Deque Iterator tried to iterate past end of Deque.");
            }
            Item out = items[(capacity + head - this.index - 1) % capacity];
            this.index++;
            return out;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Deque Iterator does not support remove method.");
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        StdOut.printf("%s%n", deque.isEmpty());
        StdOut.printf("%s%n", deque.size());
        deque.addFirst("This");
        deque.addFirst("is");
        deque.addFirst("a");
        deque.addFirst("sentence");
        StdOut.printf("%s%n", deque.isEmpty());
        StdOut.printf("%s%n", deque.size());
        StdOut.printf("%s%n", deque.removeFirst());
        StdOut.printf("%s%n", deque.removeFirst());
        StdOut.printf("%d%n", deque.size());
        StdOut.printf("%s%n", deque.removeFirst());
        StdOut.printf("%s%n", deque.removeFirst());
        deque.addFirst("This");
        deque.addFirst("is");
        deque.addFirst("a");
        deque.addFirst("sentence");
        for (String item : deque) {
            StdOut.printf("%s%n", item);
        }
        StdOut.printf("%s%n", deque.removeLast());
        StdOut.printf("%s%n", deque.removeLast());
        StdOut.printf("%s%n", deque.removeLast());
        StdOut.printf("%s%n", deque.removeLast());

        deque.addLast("1");
        deque.addLast("2");
        deque.addLast("3");
        deque.addLast("4");
        deque.addLast("5");
        deque.addLast("6");
        StdOut.printf("%s%n", deque.removeFirst());
        deque.addLast("7");
        StdOut.printf("%s%n", deque.removeFirst());
        deque.addLast("8");
        StdOut.printf("%s%n", deque.removeFirst());
        deque.addLast("9");
        StdOut.printf("%s%n", deque.removeFirst());
        deque.addLast("10");
        StdOut.printf("%s%n", deque.removeFirst());
        deque.addLast("11");
        deque.addLast("12");
        deque.addLast("13");
        deque.addLast("14");
        deque.addLast("15");
        deque.addLast("16");
        for (String item : deque) {
            StdOut.printf("%s%n", item);
        }
    }
}