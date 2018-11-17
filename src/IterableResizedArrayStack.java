import java.util.Iterator;

public class IterableResizedArrayStack<Item> implements Iterable<Item> {
    private Item[] s;
    private int n = 0;

    public Iterator<Item> iterator() {
        return new ResizedArrayListIterator();
    }

    private class ResizedArrayListIterator implements Iterator<Item> {
        private int i = n;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
        }

        public Item next() {
            return s[--i];
        }
    }

    public IterableResizedArrayStack() {
        s = (Item[]) new Object[1];
    }

    public void push(Item item) {
        if (n == s.length) resize(2 * s.length);
        s[n++] = item;
    }

    public Item pop() {
        Item item = s[--n];
        s[n] = null;
        if (n > 0 && n == s.length / 4) resize(s.length / 2);
        return item;
    }

    public void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
