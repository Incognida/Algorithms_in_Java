public class WQU {
    // Weighted Quick Union-Find data structure
    private int[] id;
    private int[] weights;

    public WQU(int n) {
        id = new int[n];
        weights = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            weights[i] = 0;
        }
    }

    public int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (weights[i] < weights[j]) {
            id[i] = j;
            weights[j] += weights[i];
        } else {
            id[j] = i;
            weights[i] += weights[j];
        }
    }
}
