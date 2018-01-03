import java.io.IOException;
/**
 * Created by JDidat on 1/23/2017.
 */
public class Percolation {
    private int n;
    public int graph[][];
    private WeightedQuickUnionUF uf;
    public Percolation(int n) {
        this.n = n;
        this.graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = 0;
            }
        }
        this.uf = new WeightedQuickUnionUF(n*n);
    }
    public void open(int x, int y) {
        int transX = n - x - 1;
        graph[transX][y] = 1;
        int oneDX = (n * transX) + y;
        if (transX != 0 && isOpen(transX - 1, y)) {
            uf.union(oneDX, oneDX - n);
        }
        if (transX != n - 1 && isOpen(transX + 1, y)) {
            uf.union(oneDX, oneDX + n);
        }
        if (y != 0 && isOpen(transX, y - 1)) {
            uf.union(oneDX, oneDX - 1);
        }
        if (y != n - 1 && isOpen(transX, y + 1)) {
            uf.union(oneDX, oneDX + 1);
        }
    }
    public boolean isOpen(int x, int y) {
        if (graph[x][y] == 1) {
            return true;
        }
        return false;
    }
    public boolean isFull(int x, int y) {
        int transX = (n - x) - 1;
        int oneDX = (n * transX) + y;
        for (int i = 0; i < n; i++) {
            if (uf.connected(i, oneDX)) {
                return true;
            }
        }
        return false;
    }
    public boolean percolates() {
        //int l = n * (n - 1);
        for(int i = 0; i < n; i++) {
            /*if (graph[0][i] == 1 || graph[0][i] == 2) {
                for (int k = l; k < n * n; k++) {
                    if (uf.connected(i, k)) {
                        return true;
                    }
                }
            }*/
            if (uf.connected((n * (n - 1) + i), (n * (n - (n - 1) - 1) + i))) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args)  {
        int x,y;
        int n = StdIn.readInt();
        Percolation p = new Percolation(n);
        while (!StdIn.isEmpty()) {
            x = StdIn.readInt();
            y = StdIn.readInt();
            p.open(x, y);
        }
        boolean ans = p.percolates();
        if (ans) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}
