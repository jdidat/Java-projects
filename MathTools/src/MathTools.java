package src;

/**
 * Created by JDidat on 2/8/2016.
 */
public class MathTools {
    public static double absoluteValue(double n) {
        if (n < 0) {
            return n/-1;
        }
        else {
            return n;
        }
    }
    public static double power(double base, int exponent) {
        double power = 1;
        if (exponent > 0) {
            for (int i = 0; i < exponent; i++) {
                power *= base;
            }
            return power;
        }
        else if (exponent < 0) {
            double absExponent = absoluteValue(exponent);
            for (int i = 0; i < absExponent; i++) {
                power *= base;
            }
            double negativePower = ((1)/(power));
            return negativePower;
        }
        else {
            return 1;
        }
    }
    public static double nthRoot(double value, int root) {
        double xk = 1;
        double deltax = 1;
        ;double temp = 1;
        if (value <= 0) {
            return 0;
        }
        else if (root == 0) {
            return 0;
        }
        else {
            if (root > 0) {
                xk = value;
            }
            else if (root < 0) {
                xk = 1 / value;
            }
        }
        while (deltax > 0.000000001) {
            temp = (1.0 / root) * ((root - 1) * xk + (value / (power(xk, (root - 1)))));
            deltax = absoluteValue(temp - xk);
            xk = temp;
        }
        return temp;
    }
    public static String scientificNotation(double n) {
        int power = 0;
        if (n >= 10) {
            while (n >= 10) {
                n = n/10;
                power += 1;
            }
        }
        else if (n < 1) {
            while (n < 1) {
                n = n*10;
                power += -1;
            }
        }
        n = ((int)(n*1000000)) / 1000000.0;
        String sciNot = n + " x 10 ^ " + power;
        return sciNot;
    }
}
