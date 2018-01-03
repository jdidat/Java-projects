import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by JDidat on 2/10/2017.
 */
public class Fast {
    private int n;
    private Point pA[];
    private HashSet<Point> h = new HashSet<>();
    private ArrayList<Point> a = new ArrayList<>();
    public PrintWriter w;
    public Fast(int n) {
        this.n = n;
        this.pA = new Point[n];
        try {
            this.w = new PrintWriter("visualPoints.txt");
        } catch (IOException e) {
            System.out.println("file error");
        }
    }
    public void fastCollinear(int n, Point pA[]) {
        Arrays.sort(pA);
        Point sA[] = pA.clone();
        double slope;
        for (int i = 0; i < n; i++) {
            Point p = pA[i];
            a.add(p);
            Arrays.sort(sA, p.BY_SLOPE_ORDER);
            double tempSlope = Double.NEGATIVE_INFINITY;
            for (int j = 0; j < n; j++) {
                if (sA[j] == p) {
                    continue;
                }
                if (sA[j].getX() - p.getX() == 0) {
                    slope = Double.POSITIVE_INFINITY;
                } else {
                    slope = (double)(sA[j].getY() - p.getY()) / (double)(sA[j].getX() - p.getX());
                }
                slope += 0.0;
                if (slope != tempSlope) {
                    if (a.size() >= 4) {
                        StdOut.print(a.size() + ":");
                        w.write(a.size() + ":");
                        int k;
                        for (k = 0; k < a.size() - 1; k++) {
                            StdOut.print("(" + a.get(k).getX() + ", " + a.get(k).getY() + ") -> ");
                            w.write("(" + a.get(k).getX() + ", " + a.get(k).getY() + ") -> ");
                            w.flush();
                        }
                        StdOut.println("(" + a.get(k).getX() + ", " + a.get(k).getY() + ")");
                        w.write("(" + a.get(k).getX() + ", " + a.get(k).getY() + ")\n");
                        w.flush();
                    }
                    a.clear();
                    a.add(p);
                    tempSlope = slope;
                }
                if (h.contains(sA[j])) {
                    a.clear();
                    a.add(p);
                    double slopeComp1;
                    do {
                        j++;
                        if (j >= n) {
                            break;
                        }
                        if (sA[j].getX() - p.getX() == 0) {
                            slopeComp1 = Double.POSITIVE_INFINITY;
                        } else {
                            slopeComp1 = (double) (sA[j].getY() - p.getY()) / (double) (sA[j].getX() - p.getX());
                        }
                        slopeComp1 += 0.0;
                    } while (slope == slopeComp1);
                    if (j >= n) {
                        break;
                    }
                    a.clear();
                    a.add(p);
                }
                if (sA[j] != p) {
                    a.add(sA[j]);
                }
                tempSlope = slope;
            }
            if (a.size() >= 4) {
                StdOut.print(a.size() + ":");
                w.write(a.size() + ":");
                int l;
                for (l = 0; l < a.size() - 1; l++) {
                    StdOut.print("(" + a.get(l).getX() + ", " + a.get(l).getY() + ") -> ");
                    w.write("(" + a.get(l).getX() + ", " + a.get(l).getY() + ") -> ");
                    w.flush();
                }
                StdOut.println("(" + a.get(l).getX() + ", " + a.get(l).getY() + ")");
                w.write("(" + a.get(l).getX() + ", " + a.get(l).getY() + ")\n");
                w.flush();
            }
            a.clear();
            h.add(p);
        }
    }
    public static void main(String[] args) {
        int x,y;
        int n = StdIn.readInt();
        Point p;
        Fast f = new Fast(n);
        for(int i = 0; i < n; i++) {
            x = StdIn.readInt();
            y = StdIn.readInt();
            p = new Point(x, y);
            f.pA[i] = p;
        }
        f.fastCollinear(f.n, f.pA);
        f.w.close();
    }
}
