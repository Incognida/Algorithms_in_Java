import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int n = 0;
    private int size = 0;

    public RandomizedQueue() {
        s = (Item[]) new Object[1];
    }

    public Iterator<Item> iterator() {
        return new RQIterator();
    }

    private class RQIterator implements Iterator<Item> {
        private Item[] mockS;
        private int mockSize;
        private int mockn;

        private RQIterator() {
            mockS = (Item[]) new Object[s.length];
            for (int i = 0; i < n; i++) {
                mockS[i] = s[i];
            }
            mockSize = size;
            mockn = n;
        }

        @Override
        public boolean hasNext() {
            return mockSize > 0;
        }

        @Override
        public Item next() {
            if (mockSize == 0) throw new NoSuchElementException();

            int j = StdRandom.uniform(0, mockn);
            while (mockS[j] == null) {
                j = StdRandom.uniform(0, mockn);
            }
            Item item = mockS[j];
            mockS[j] = null;
            mockSize--;
            if (mockSize > 0 && mockSize == mockS.length / 2) {
                Item[] copy = (Item[]) new Object[mockS.length / 2];
                int k = 0;
                for (int i = 0; i < mockn; i++) {
                    if (mockS[i] != null) copy[k++] = mockS[i];
                }
                mockn = k;
                mockS = copy;
            }
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void traverse() {
        StdOut.print("Traversed: ");
        for (int i = 0; i < s.length; i++) {
            StdOut.print(s[i] + " ");
        }
        StdOut.println();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == s.length) {
            int upperQuarter = (int) Math.ceil((double) n / 4);
            if (size == upperQuarter) resize(size * 2);
            else resize(s.length * 2);
        }
        s[n++] = item;
        size++;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] != null) {
                copy[j++] = s[i];
            }
        }
        n = j;
        s = copy;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int j = StdRandom.uniform(0, n);
        while (s[j] == null) {
            j = StdRandom.uniform(0, n);
        }
        Item item = s[j];
        s[j] = null;
        size--;
        if (size > 0 && size == s.length / 4) resize(s.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int j = StdRandom.uniform(0, n);
        while (s[j] == null) {
            j = StdRandom.uniform(0, n);
        }
        return s[j];
    }


    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        while (StdIn.hasNextChar()) {
            String word = StdIn.readLine();
            if (word.equals("-")) {
                StdOut.println("dequeued - " + q.dequeue());
            } else if (word.equals("traverse")) q.traverse();
            else if (word.equals("iterate")) {
                for (String s : q) StdOut.println(s);
            } else q.enqueue(word);
        }
    }
}
