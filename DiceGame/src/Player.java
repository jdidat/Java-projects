import java.util.Random;
import java.util.Scanner;
/**
 * Created by JDidat on 2/5/2016.
 */
public class Player {
    String name;
    Random dice;
    int score;
    private static final String LOSE_MESSAGE = "\tDice values: %s, Sorry, you scored 0 for the round\n\n";
    private static final String THROW_DICE_MESSAGE = "\tDice values: %s, %d + %d = %d points!\n";
    private static final String ALL_DICE_SCORED_MESSAGE = "\tYou have to relaunch the %d dice\n\n";
    private static final String NOT_MULTIPLE_OF_100_MESSAGE = "\tYou have to continue, "
            + "the score is not a multiple of 100\n\n";
    private static final String CONTINUE_MESSAGE = "\tContinue with %d dice? Current score: %d points (Y/N)\n";
    private static final String ROUND_SCORE_MESSAGE = "\tSuper you scored %d for the round!! Your new score is %d\n\n";
    public Player(String name) {
        this.name = name;
        this.dice = new Random();
        this.score = 0;
    }
    public String throwDice(int nbDice) {
        String diceVal = "";
        for (int i = 0; i < nbDice; i++) {
            int temp = dice.nextInt(6) + 1;
            String tempString = Integer.toString(temp);
            diceVal += tempString + " ";
        }
        return diceVal;
    }
    public int nbDiceScored(String diceValues) {
        Scanner s = new Scanner(diceValues);
        Scanner s1 = new Scanner(diceValues);
        int oneCount = 0;
        int fiveCount = 0;
        while (s.hasNextInt()) {
            if (s.nextInt() == 1) {
                oneCount = oneCount + 1;
            }
        }
        while (s1.hasNextInt()) {
            if (s1.nextInt() == 5) {
                fiveCount = fiveCount + 1;
            }
        }
        int val = oneCount + fiveCount;
        return val;
    }
    public int countPoints(String diceValues) {
        Scanner s = new Scanner(diceValues);
        Scanner s1 = new Scanner(diceValues);
        int onePointCount = 0;
        int fivePointCount = 0;
        while (s.hasNextInt()) {
            if (s.nextInt() == 1) {
                onePointCount = onePointCount + 100;
            }
        }
        while (s1.hasNextInt()) {
            if (s1.nextInt() == 5) {
                fivePointCount = fivePointCount + 50;
            }
        }
        int val = onePointCount + fivePointCount;
        return val;
    }
    public boolean continueRound(int nbDice) {
        Scanner s = new Scanner(System.in);
        System.out.printf(CONTINUE_MESSAGE, nbDice, score);
        String answer = s.nextLine();
        String yes = "Y";
        String no = "N";
        boolean c;
        while (!answer.equals(yes) && !answer.equals(no)) {
            System.out.printf(CONTINUE_MESSAGE, nbDice, score);
            answer = s.nextLine();
        }
        if (answer.equals(yes)) {
            c = true;
        }
        else {
            c = false;
        }
        return c;
    }
    public boolean isWinner() {
        if (score >= 5000) {
            return true;
        }
        else {
            return false;
        }
    }
    public void playRound() {
        int diceNum = 5;
        int roundScore = 0;
        while (true) {
            String diceRoll = throwDice(diceNum);
            int launchScore = countPoints(diceRoll);
            if (launchScore == 0) {
                System.out.printf(LOSE_MESSAGE, diceRoll);
                roundScore = 0;
                score = roundScore + score;
                break;
            }
            int roundScore1 = roundScore + launchScore;
            System.out.printf(THROW_DICE_MESSAGE, diceRoll, roundScore, launchScore, roundScore1);
            roundScore = roundScore + launchScore;
            int scoredDice = nbDiceScored(diceRoll);
            if (scoredDice == diceNum) {
                diceNum = 5;
                System.out.printf(ALL_DICE_SCORED_MESSAGE, 5);
                continue;
            }
            diceNum = diceNum - scoredDice;
            if (roundScore1 % 100 == 0) {
                boolean roundContinue = continueRound(diceNum);
                if (roundContinue) {
                    continue;
                }
                else {
                    score = roundScore + score;
                    System.out.printf(ROUND_SCORE_MESSAGE, roundScore1, score);
                    break;
                }
            }
            else {
                System.out.printf(NOT_MULTIPLE_OF_100_MESSAGE);
            }

        }

    }
}
