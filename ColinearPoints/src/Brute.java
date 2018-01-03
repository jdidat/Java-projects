import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
public class Brute {
    private int n;
    private Point pA[];
    private PrintWriter w;
    public Brute(int n) {
        this.n = n;
        this.pA = new Point[n];
        try {
            this.w = new PrintWriter("visualPoints.txt");
        } catch (IOException e) {
            System.out.println("file error");
        }
    }
    public void bruteCollinear(int n, Point pA[]) {
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (Point.areCollinear(pA[i], pA[j], pA[k], pA[l])) {
                            StdOut.print("4:(" + pA[i].getX() + ", " + pA[i].getY() + ") -> (" +
                                    pA[j].getX() + ", " + pA[j].getY() + ") -> (" +
                                    pA[k].getX() + ", " + pA[k].getY() + ") -> (" +
                                    pA[l].getX() + ", " + pA[l].getY() + ")\n");
                            w.print("4:(" + pA[i].getX() + ", " + pA[i].getY() + ") -> (" +
                                    pA[j].getX() + ", " + pA[j].getY() + ") -> (" +
                                    pA[k].getX() + ", " + pA[k].getY() + ") -> (" +
                                    pA[l].getX() + ", " + pA[l].getY() + ")\n");
                            w.flush();
                        }
                    }
                }
            }
        }
    }
    public static void main(String args[]) {
        int x,y;
        int n = StdIn.readInt();
        Point p;
        Brute b = new Brute(n);
        for(int i = 0; i < n; i++) {
            x = StdIn.readInt();
            y = StdIn.readInt();
            p = new Point(x, y);
            b.pA[i] = p;
        }
        Arrays.sort(b.pA);
        b.bruteCollinear(n, b.pA);
        b.w.close();
    }
}
