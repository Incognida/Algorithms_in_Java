import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizedArrayStack {
    /*
    This implementation uses less memory than LinkedList, but implementing
    amortized constant time to push() or pop().
    Memory:
    ResizedArrayStack - one time used custom object, that takes 16 bytes
    int n - one time used object Integer, that takes 4 bytes
    padding - one time used padding, 4 bytes

    String[] s - N-times used, takes 8*n bytes.
    When array is full, it takes right 8*n bytes.
    When array is only quarter full, it takes 32*n bytes, cause
    other 75% is full of nulls
    */
    private String[] s;
    private int n = 0;

    public ResizedArrayStack() {
        s = new String[1];
    }

    public void push(String word) {
        if (n == s.length) {
            resize(2 * s.length);
        }
        s[n++] = word;
    }

    public String pop() {
        String item = s[--n];
        s[n] = null;
        if (n > 0 && n == s.length / 4) resize(s.length / 2);
        return item;
    }

    public void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public static void main(String[] args) {
        ResizedArrayStack s = new ResizedArrayStack();
        int n = Integer.parseInt(args[0]);
        StdOut.println("Print words");
        for (int i = 0; i < n; i++) {
            String word = StdIn.readString();
            s.push(word);
        }
    }
}
