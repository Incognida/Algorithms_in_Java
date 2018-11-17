import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Queue<Item> {
    // Queue based on resizing array
    private Item[] s;
    private int head = 0;
    private int tail = 0;

    public Queue() {
        s = (Item[]) new Object[1];
    }

    public void traverse() {
        for (int i = 0; i < s.length; i++) {
            StdOut.print(s[i] + "  ");
        }
        StdOut.println();
        StdOut.println(String.format(
                "Head is %s, tail is %s, s.length is %s", head, tail, s.length
        ));
    }

    public boolean isEmpty() {
        return tail <= head;
    }

    public void enqueue(Item item) {
        if (tail == s.length) resizeForEnqueue(s.length * 2);
        s[tail++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        Item item = s[head];
        s[head] = null;
        head++;
        if (head > 0 && head == 3 * s.length / 4) {
            StdOut.println("Checking for associativity " + 3 * s.length / 4);
            resizeForDequeue(s.length + s.length / 4);
        }
        return item;
    }

    public void resizeForDequeue(int capacity) {
        StdOut.println(
                String.format("Array will be decreased from %s to %s", s.length, capacity - head));
        Item[] copy = (Item[]) new Object[capacity - head];
        int j = 0;
        for (int i = head; i < tail; i++) {
            copy[j++] = s[i];
        }
        s = copy;
        tail = tail - head;
        head = 0;
        StdOut.println(
                String.format(
                        "After decreasing: tail = %s, array_length = %s, head = %s",
                        tail, s.length, head
                )
        );
    }

    public void resizeForEnqueue(int capacity) {
        StdOut.println(String.format("Array will be increased from %s to %s", tail, capacity));
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < tail; i++) {
            copy[i] = s[i];
        }
        s = copy;
        StdOut.println(
                String.format(
                        "After increasing: tail = %s, array_length = %s, head = %s",
                        tail, s.length, head
                )
        );
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        while (StdIn.hasNextChar()) {
            String word = StdIn.readString();
            if (word.equals("-")) {
                StdOut.println(
                        String.format("Dequeued item - %s", queue.dequeue())
                );
            } else if (word.equals("stop")) {
                break;
            } else queue.enqueue(word);
        }
    }
}
