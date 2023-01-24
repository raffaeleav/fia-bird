package iaModule;

import flappyBird.FlappyBird;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class HillClimbing {

    public HillClimbing() throws AWTException {}

    public static void main(String[] args) throws AWTException, IOException {
        FlappyBird.flappyBird = new FlappyBird();
        climbHill();
    }

    public static void climbHill() throws AWTException {
        Utils utils = new Utils();
        Robot robot = new Robot();
        robot.setAutoDelay(20 * 9);

        while(true){
            Rectangle futureBirdPosition = FlappyBird.flappyBird.bird,
                    actualBirdPosition = (Rectangle) FlappyBird.flappyBird.bird.clone();
            ArrayList<Rectangle> colums = FlappyBird.flappyBird.columns;

            if(FlappyBird.flappyBird.gameOver)
                System.exit(0);

            System.out.println(colums.size());
            int score = FlappyBird.flappyBird.score;

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

            if(utils.jumpObjectiveFunction(colums.get((score * 2) % 8), futureBirdPosition)){
                //System.out.println("Salto!");
                robot.keyRelease(KeyEvent.VK_SPACE);
            }

            else {
                //System.out.println("Non Salto!");
                robot.delay(20 * 9);
            }
        }
    }

}

