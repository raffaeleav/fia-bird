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
        Utils s = new Utils();
        Robot robot = new Robot();

        while(true){
            Rectangle birdPosition = FlappyBird.flappyBird.bird;
            ArrayList<Rectangle> colums = FlappyBird.flappyBird.columns;

            System.out.println(colums.size());
            int score = FlappyBird.flappyBird.score;


            if(s.JumpObjectiveFunctionHillClimbing(colums.get((score + 1) % 8) ,colums.get((score) % 8),birdPosition)){
                System.out.println("Salto!");
                robot.keyRelease(KeyEvent.VK_SPACE);
                robot.delay(20 * 11);
            }
            else {

                System.out.println("Non Salto!");
                robot.delay(20 * 11);
            }
            System.out.println("Metri Percorsi: " + FlappyBird.flappyBird.metri);
        }
    }


}

