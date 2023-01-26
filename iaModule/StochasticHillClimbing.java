package iaModule;

import flappyBird.FlappyBird;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StochasticHillClimbing {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static final String GREEN = "\033[0;32m";
    public static final String RESET = "\033[0m";

    public static final int FRAME_SKIPPED = 9;

    public StochasticHillClimbing() {}

    public static void main(String[] args) throws AWTException, IOException {
        FlappyBird.flappyBird = new FlappyBird();
        stochasticClimbHill();
    }

    public static void stochasticClimbHill() throws AWTException {
        Utils utils = new Utils();
        Robot robot = new Robot();
        Random random = new Random();
        ArrayList <Double> results = new ArrayList<>();
        int i = 1;

        while(true){
            Rectangle futureBirdPosition = FlappyBird.flappyBird.bird,
                    actualBirdPosition = (Rectangle) FlappyBird.flappyBird.bird.clone();
            ArrayList<Rectangle> columns = FlappyBird.flappyBird.columns;

            if(FlappyBird.flappyBird.gameOver) {
                double metri = FlappyBird.flappyBird.metri;

                System.out.println(HillClimbing.GREEN + i + ". [Hai percorso " + df.format(metri) + "m]" +
                        HillClimbing.RESET);
                i++;
                results.add(metri);
                System.out.println("Il miglior risultato è stato " + df.format(Collections.max(results)) + "m 🏆");
                System.out.println("");
                System.out.println("");
            }

            int score = FlappyBird.flappyBird.score;

            // caso in cui il vicino è peggio dello stato attuale (nel caso in cui non abbia saltato)
            actualBirdPosition.x -= 10;
            actualBirdPosition.y -= 2;
            if(utils.distanceObjectiveFunction(columns.get((score * 2) % 8), actualBirdPosition)
                    <= utils.distanceObjectiveFunction(columns.get((score * 2) % 8), futureBirdPosition)) {
                utils.rewindBirdNoJump();
            }

            // caso in cui il vicino è peggio dello stato attuale (nel caso in cui abbia saltato)
            actualBirdPosition.y += 12;
            if(utils.distanceObjectiveFunction(columns.get((score * 2) % 8), actualBirdPosition)
                    <= utils.distanceObjectiveFunction(columns.get((score * 2) % 8), futureBirdPosition)) {
                utils.rewindBirdJump();
            }

            // il bird salta
            if(random.nextInt(2) == 0){
                robot.keyRelease(KeyEvent.VK_SPACE);

                robot.delay(20 * FRAME_SKIPPED);
            }

            // il bird non salta
            else {
                robot.delay(20 * FRAME_SKIPPED);
            }
        }
    }

}
