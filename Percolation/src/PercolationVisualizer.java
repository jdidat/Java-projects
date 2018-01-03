import java.io.*;
import java.util.Random;

/**
 * Created by JDidat on 1/25/2017.
 */
public class PercolationVisualizer {
    private int n;
    private int graph[][];
    private WeightedQuickUnionUF uf;
    public PrintWriter w;
    public PercolationVisualizer(int n) {
        this.n = n;
        this.graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = 0;
            }
        }
        this.uf = new WeightedQuickUnionUF(n*n);
        try {
            this.w = new PrintWriter("visualMatrix.txt");
        } catch (IOException e) {
            System.out.println("file error");
        }
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
        int oneDX = (n * transX)+ y;
        for (int i = 0; i < n; i++) {
            if (uf.connected(i, oneDX)) {
                return true;
            }
        }
        return false;
    }
    public boolean percolates() {
        int l = n * (n - 1);
        for(int i = 0; i < n; i++) {
            for (int j = l; j < n * n; j++) {
                if (uf.connected(i , j)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void visualizer(int x, int y) {
        if (percolates()) {
            int skipRow = (n * n) - (n * (n - 1));
            for (int i = 0; i < n; i++) {
                if (graph[0][n - i - 1] == 1) {
                    graph[0][n - i - 1] = 2;
                }
                for (int j = skipRow; j < n*n; j++) {
                    if (uf.connected(i,j)) {
                        graph[j / n][j % n] = 2;
                    }
                }
            }
            int transX = n - x - 1;
            int oneDX = (n * transX) + y;
            for (int k = 0; k < n*n; k++) {
                if (uf.connected(oneDX, k)) {
                    graph[k / n][k % n] = 2;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0;j < n; j++) {
                StdOut.print(graph[i][j] + " ");
                w.print(graph[i][j]);
                w.flush();
                w.print(" ");
                w.flush();
            }
            StdOut.print("\n");
            w.print("\n");
            w.flush();
        }
        StdOut.print("\n");
        w.print("\n");
        w.flush();
    }
    public static void main(String[] args) {
        int x,y;
        int n = StdIn.readInt();
        StdOut.print(n);
        PercolationVisualizer p = new PercolationVisualizer(n);
        p.w.print(n);
        p.w.flush();
        p.w.print("\n\n");
        p.w.flush();
        while (!StdIn.isEmpty()) {
            x = StdIn.readInt();
            y = StdIn.readInt();
            p.open(x, y);
            p.visualizer(x, y);
        }
        p.w.close();
    }
}
