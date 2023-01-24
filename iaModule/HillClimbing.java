package iaModule;

import flappyBird.FlappyBird;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class HillClimbing {

    public HillClimbing() throws AWTException {}

    public static void main(String[] args) throws AWTException {
        FlappyBird.flappyBird = new FlappyBird();
        climbHill();
    }

    public static void climbHill() throws AWTException {
        Utils utils = new Utils();
        Robot robot = new Robot();
        robot.setAutoDelay(20 * 9);

        while(true){
            Rectangle birdPosition = FlappyBird.flappyBird.bird;
            ArrayList<Rectangle> colums = FlappyBird.flappyBird.columns;

            if(FlappyBird.flappyBird.gameOver)
                System.exit(0);

            System.out.println(colums.size());
            int score = FlappyBird.flappyBird.score;

            if(utils.jumpObjectiveFunction(colums.get((score * 2) % 8), birdPosition)){
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

