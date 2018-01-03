/**
 * Created by JDidat on 1/25/2017.
 */
public class PercolationStats {
    private static int flag = 0;
    public PercolationStats() {}
    public void stats(int gridSize, int rep) {
        int x, y;
        double[] pA = new double[rep];
        double[] tA = new double[rep];
        int pCount = 0;
        if (flag == 0) {
            for (int i = 0; i < rep; i++) {
                pCount = 0;
                Stopwatch timer = new Stopwatch();
                PercolationQuick p = new PercolationQuick(gridSize);
                while (pCount < ((gridSize * gridSize) * (0.58)) || !p.percolates()) {
                    x = (int) (Math.random() * gridSize);
                    y = (int) (Math.random() * gridSize);
                    if (p.graph[gridSize - x - 1][y] == 1) {
                        continue;
                    }
                    p.open(x, y);
                    pCount++;
                }
                pA[i] = (double) pCount/(gridSize * gridSize);
                tA[i] = timer.elapsedTime();
            }
        }
        else {
            for (int i = 0; i < rep; i++) {
                pCount = 0;
                Stopwatch timer = new Stopwatch();
                Percolation p = new Percolation(gridSize);
                while (pCount < ((gridSize * gridSize) * (0.58)) || !p.percolates()) {
                    x = (int) (Math.random() * gridSize);
                    y = (int) (Math.random() * gridSize);
                    if (p.graph[gridSize - x - 1][y] == 1) {
                        continue;
                    }
                    p.open(x, y);
                    pCount++;
                    pA[i] = (double) pCount/(gridSize * gridSize);
                    tA[i] = timer.elapsedTime();
                }
            }
        }
        double meanTime = StdStats.mean(tA);
        double totalTime = StdStats.sum(tA);
        double stdTime = StdStats.stddev(tA);
        double meanP = StdStats.mean(pA);
        double stdThres = StdStats.stddev(pA);
        StdOut.println("mean threshold=" + meanP);
        StdOut.println("std dev=" + stdThres);
        StdOut.println("time=" + totalTime);
        StdOut.println("mean time=" + meanTime);
        StdOut.println("stddev time=" + stdTime);
    }
    public static void main(String[] args) {
        PercolationStats p = new PercolationStats();
        if (args[2].equals("fast")) {
            flag = 1;
        }
        p.stats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

    }

}
