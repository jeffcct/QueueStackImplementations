import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.size = 0;
        this.items = (Item[]) new Object[1];
    }

    private void swap(int index) {
        Item temp = this.items[index];
        this.items[index] = this.items[this.size - 1];
        this.items[size - 1] = temp;
    } 

    private void resize(int capacity) {
        if (this.size >= capacity) {
            return;
        }

        Item[] newItems = (Item[]) new Object[capacity];
        for (int i = 0; i < this.size; i++) {
            newItems[i] = this.items[i];
        }
        this.items = newItems;
    }

    private void doubleSize() {
        this.resize(this.items.length * 2);
    }

    private void halveSize() {
        this.resize(this.items.length / 2);
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("attempted to enqueue a null object in RandomizedQueue");
        }
        if (this.size >= this.items.length) {
            this.doubleSize();
        }
        this.items[this.size] = item;
        this.size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Attempted to dequeue from empty randomizedQueue.");
        }

        int index = StdRandom.uniformInt(this.size);
        this.swap(index);

        this.size--;
        Item out = this.items[this.size];
        if (this.size <= this.items.length / 4) {
            this.halveSize();
        }
        return out;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("Attempted to sample an empty randomizedQueue.");
        }
        int index = StdRandom.uniformInt(this.size);
        return this.items[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        int index;
        int[] positions;

        private RandomQueueIterator() {
            this.positions = new int[size];
            for (int i = 0; i < size; i++) {
                positions[i] = i;
            }
            StdRandom.shuffle(this.positions);
        }

        @Override
        public boolean hasNext() {
            return this.index < this.positions.length;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException("RandomizedQueue Iterator has looped through all values.");
            }
            Item out = items[this.positions[this.index]];
            this.index++;
            return out;
        }

        public void remove() {
            throw new UnsupportedOperationException("RandomQueue does not support iterative removal.");
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> items = new RandomizedQueue<String>();
        items.enqueue("Hello");
        StdOut.printf("There are %d items in items.%n", items.size());
        StdOut.printf("After sampling: '%s', There were %d items left.%n", items.sample(), items.size());
        StdOut.printf("After dequeueing: '%s', there were %d items left.%n", items.dequeue(), items.size());
        items.enqueue("Thing");
        items.enqueue("Item");
        items.enqueue("This");
        items.enqueue("That");
        items.enqueue("Hello");
        StdOut.printf("Started looping over elements.%n");
        for (String item : items) {
            StdOut.printf("Found '%s'%n", item);
        }
        StdOut.printf("Finished looping over elements.%n");
        while (!(items.isEmpty())) {
            StdOut.printf("RandomizedQueue wasn't empty, found '%s'. There are %d items left.%n", items.dequeue(), items.size());
        }

    }

}