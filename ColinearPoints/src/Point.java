/*************************************************************************
 * Compilation:  javac Point.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;
public class Point implements Comparable<Point> {
    public final Comparator<Point> BY_SLOPE_ORDER = new slopeCompare();
    public class slopeCompare implements Comparator<Point> {
        public int compare(Point a, Point b) {
            double slope1;
            if (a.x - x == 0) {
                slope1 = Double.POSITIVE_INFINITY;
            } else {
                slope1 = (double)(a.y - y) / (double)(a.x - x);
            }
            double slope2;
            if (b.x - x == 0) {
                slope2 = Double.POSITIVE_INFINITY;
            } else {
                slope2 = (double)(b.y - y) / (double)(b.x - x);
            }
            if (slope1 > slope2) {
                return 1;
            }
            else if (slope2 > slope1) {
                return -1;
            }
            return a.compareTo(b);
        }
    }
    private final int x;
    private final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public static boolean areCollinear(Point p, Point q, Point r) {
        boolean result = (q.y - p.y)*(r.x - q.x) == (r.y - q.y)*(q.x - p.x);
        return result;
    }
    public static boolean areCollinear(Point p, Point q, Point r, Point s) {
        boolean result = areCollinear(p, q, r);
        result = result && areCollinear(p, q, s);
        return result;
    }
    public int compareTo(Point that) {
        if (this.x > that.x || (this.x == that.x && this.y > that.y)) {
            return 1;
        }
        if (this.x < that.x || (this.x == that.x && this.y < that.y)) {
            return -1;
        }
        return 0;
    }

}
