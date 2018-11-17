import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class StackedQueue<Item> {
    private int prev_capacity = 0;
    private int popPointer = 0;
    private int pushPointer = 0;
    private Item[] popArray;
    private Item[] pushArray;

    public StackedQueue() {
        popArray = (Item[]) new Object[1];
        pushArray = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return popPointer == 0 && pushPointer == 0;
    }

    private void traverse() {
        StdOut.println("PopStack");
        for (int i = 0; i < popArray.length; i++) StdOut.print(popArray[i] + " ");
        StdOut.println("\nPushStack");
        for (int i = 0; i < pushArray.length; i++) StdOut.print(pushArray[i] + " ");
        StdOut.println();
    }

    public void enqueue(Item item) {
        if (pushPointer == pushArray.length) resizeArray(2 * pushArray.length);
        pushArray[pushPointer++] = item;
        StdOut.println("Push pointer became " + pushPointer);
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (popPointer == 0) {
            try {
                resize();
            } catch (NoSuchElementException e) {
                return null;
            }
        }

        Item item = popArray[--popPointer];
        popArray[popPointer] = null;
        StdOut.println("Pop pointer became " + popPointer);

        return item;
    }

    private void resizeArray(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < pushPointer; i++) {
            copy[i] = pushArray[i];
        }
        pushArray = copy;
    }

    private void resize() {
        Item[] newPopArray = (Item[]) new Object[1];
        Item[] newPushArray = (Item[]) new Object[1];
        if (pushArray.length != prev_capacity) {
            newPushArray = (Item[]) new Object[pushArray.length - prev_capacity];
        }
        if (pushPointer != prev_capacity) {
            newPopArray = (Item[]) new Object[pushPointer - prev_capacity];
        } else {
            StdOut.println("empty pop stack");
            throw new NoSuchElementException();
        }

        int j = 0;
        for (int i = prev_capacity; i < pushPointer; i++) {
            newPushArray[j++] = pushArray[i];
        }

        j = 0;
        for (int i = pushPointer - 1; i >= prev_capacity; i--) {
            newPopArray[j++] = pushArray[i];
        }
        prev_capacity = j;
        popArray = newPopArray;
        pushArray = newPushArray;

        popPointer = pushPointer = j;
    }


    public static void main(String[] args) {
        StackedQueue<String> queue = new StackedQueue<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.equals("traverse")) queue.traverse();
            else if (word.equals("-")) {
                StdOut.println("dequeued - " + queue.dequeue());
            } else queue.enqueue(word);
        }
    }
}
