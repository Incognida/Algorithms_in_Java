import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BitonicBinarySearch {
    /* Bitonic array is an array that firstly increase, and then immediately decrease
    The goal of this task is to search a value in array for about ~lgN. I've got 3lgN.
    * */
    public static int bitonicValue(int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = -1;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (mid == hi || mid == lo) {
                return mid;
            }
            if (a[mid - 1] < a[mid] && a[mid] > a[mid + 1]) {
                return mid;
            } else if (a[mid - 1] < a[mid] && a[mid] < a[mid + 1]) {
                lo = mid + 1;
            } else if (a[mid - 1] > a[mid] && a[mid] > a[mid + 1]) {
                hi = mid - 1;
            } else StdOut.println("Bad condition");
        }
        return mid;
    }

    public static int binarySearch(int[] a, int lo, int hi, int key, boolean incr) {
        int mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key == a[mid]) {
                return mid;
            } else if (key < a[mid]) {
                if (!incr) lo = mid + 1;
                else hi = mid - 1;
            } else {
                if (!incr) hi = mid - 1;
                else lo = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        StdOut.println("Write dimension of array");
        int n = Integer.parseInt(args[0]);
        int[] bitonicArray = new int[n];
        StdOut.println("Write values of array");
        for (int i = 0; i < n; i++) {
            bitonicArray[i] = StdIn.readInt();
        }
        StdOut.println("Write key to find");
        int key = StdIn.readInt();

        int value = bitonicValue(bitonicArray);
        if (value == -1) {
            StdOut.println("Array is not bitonic");
            return;
        }

        int increasing = binarySearch(bitonicArray, 0, value, key, true);
        if (increasing != -1) {
            StdOut.println("Value is = " + increasing);
            return;
        }

        int decreasing = binarySearch(bitonicArray, value, n - 1, key, false);
        if (decreasing != -1) {
            StdOut.print("Value is = " + decreasing);
        } else StdOut.print("Not found");
    }
}
