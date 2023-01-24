package iaModule;

import flappyBird.FlappyBird;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class StochasticHillClimbing {

    public StochasticHillClimbing() {}

    public static void main(String[] args) throws AWTException {
        FlappyBird.flappyBird = new FlappyBird();
        stochasticClimbHill();
    }

    public static void stochasticClimbHill() throws AWTException {
        Utils utils = new Utils();
        Robot robot = new Robot();
        robot.setAutoDelay(20 * 9);

        while(true){
            Rectangle futureBirdPosition = FlappyBird.flappyBird.bird,
                    actualBirdPosition = (Rectangle) FlappyBird.flappyBird.bird.clone();
            ArrayList<Rectangle> colums = FlappyBird.flappyBird.columns;

            // se si perde la partita finisce
            if(FlappyBird.flappyBird.gameOver)
                System.exit(0);

            // punteggio per calcolare di quale pipe calcolare il punto ideale di salto
            int score = FlappyBird.flappyBird.score;

            // caso in cui il vicino e' peggio dello stato attuale (nel caso in cui non abbia saltato)
            actualBirdPosition.x -= 10;
            actualBirdPosition.y -= 2;
            if(utils.distanceObjectiveFunction(colums.get((score * 2) % 8), actualBirdPosition)
                    <= utils.distanceObjectiveFunction(colums.get((score * 2) % 8), futureBirdPosition)) {
                utils.rewindBirdNoJump();
                System.out.println("Renderizzo non salto");
            }

            // caso in cui il vicino e' peggio dello stato attuale (nel caso in cui abbia saltato)
            actualBirdPosition.y += 12;
            if(utils.distanceObjectiveFunction(colums.get((score * 2) % 8), actualBirdPosition)
                    <= utils.distanceObjectiveFunction(colums.get((score * 2) % 8), futureBirdPosition)) {
                utils.rewindBirdJump();
                System.out.println("Renderizzo salto");
            }

            // l' algoritmo decide di saltare
            if(utils.jumpObjectiveFunction(colums.get((score * 2) % 8), futureBirdPosition)){
                robot.keyRelease(KeyEvent.VK_SPACE);
            }

            // l' algoritmo decide di non saltare
            else {
                robot.delay(20 * 9);
            }
        }
    }

}
