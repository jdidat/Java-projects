 /**
 * Created by JDidat on 2/8/2016.
 */
import java.util.Scanner;
public class MathToolsClient {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("Select a number from the menu choices:");
            System.out.println("1 - Absolute Value");
            System.out.println("2 - Power");
            System.out.println("3 - Nth Root");
            System.out.println("4 - Scientific Notation");
            String response = s.nextLine();
            if (response.equals("1")) {
                System.out.println("***Absolute Value***");
                System.out.println("Enter your number");
                double absNum = s.nextDouble();
                double abs = src.MathTools.absoluteValue(absNum);
                System.out.println("answer: " + abs);
                System.out.println("Return to the menu?");
                if (s.nextLine().equals("yes")) {
                    continue;
                }
                else if (s.nextLine().equals("no")) {
                    break;
                }
            }
            else if (response.equals("2")) {
                System.out.println("***Power***");
                System.out.println("Enter your base");
                double base = s.nextDouble();
                System.out.println("Enter your exponent");
                int exponent = s.nextInt();
                double power = src.MathTools.power(base, exponent);
                System.out.println("answer: " + power);
                System.out.println("Return to the menu?");
                if (s.nextLine().equals("yes")) {
                    continue;
                }
                else if (s.nextLine().equals("no")) {
                    break;
                }
            }
            else if (response.equals("3")) {
                System.out.println("***Nth Root***");
                System.out.println("Enter your value");
                double value = s.nextDouble();
                System.out.println("Enter your root");
                int root = s.nextInt();
                double nthRoot = src.MathTools.nthRoot(value, root);
                System.out.println("answer: " + nthRoot);
                System.out.println("Return to the menu?");
                if (s.nextLine().equals("yes")) {
                    continue;
                }
                else if (s.nextLine().equals("no")) {
                    break;
                }
            }
            else if (response.equals("4")) {
                System.out.println("***Scientific Notation***");
                System.out.println("Enter your number");
                double n = s.nextDouble();
                String sciNod = src.MathTools.scientificNotation(n);
                System.out.println("answer: " + sciNod);
                System.out.println("Return to the menu?");
                if (s.nextLine().equals("yes")) {
                    continue;
                }
                else if (s.nextLine().equals("no")) {
                    break;
                }
            }
            else {
                System.out.println("Invalid Option");
                System.out.println("Return to the menu?");
                String response1 = s.nextLine();
                if (response1.equals("yes")) {
                    continue;
                }
                else if (response1.equals("no")) {
                    break;
                }
            }
        }
    }
}
