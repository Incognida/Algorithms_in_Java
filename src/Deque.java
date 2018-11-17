import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public int size() {
        return size;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node mockFirst = first;

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return mockFirst != null;
        }

        @Override
        public Item next() {
            if (mockFirst == null) throw new NoSuchElementException();
            Item item = mockFirst.item;
            mockFirst = mockFirst.next;
            return item;
        }
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldfirst = first;

        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;

        if (oldfirst != null) oldfirst.prev = first;
        if (oldfirst == null) last = first;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Node oldfirst = first;

        if (last.equals(oldfirst)) last = null;

        Item item = first.item;
        first = first.next;

        if (first != null) {
            first.prev = null;
        }
        size--;
        return item;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldlast = last;

        last = new Node();
        last.item = item;
        last.prev = oldlast;
        last.next = null;

        if (oldlast != null) oldlast.next = last;
        if (oldlast == null) first = last;
        size++;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Node oldlast = last;

        if (first.equals(oldlast)) first = null;

        Item item = last.item;
        last = last.prev;

        if (last != null) {
            last.next = null;
        }
        size--;
        return item;
    }

    public static void main(String[] args) {

    }
}
