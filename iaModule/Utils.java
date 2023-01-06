package iaModule;

import flappyBird.*;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Utils {

    // metodo per calcolare il centro dello spazio libero tra 2 pipe
    public Point getPipeHole(Rectangle upperPipe, Rectangle bottomPipe){
        // coordinate del centro della base inferiore del rettangolo superiore
        double  yCentreBottomBaseUpper = upperPipe.getY() + upperPipe.getHeight(),
                xCentreBottomBaseUpper = upperPipe.getX() + 150;

        // coordinate del centro della base superiore del rettangolo inferiore
        double  yCentreBottomBaseBottom = bottomPipe.getY(),
                xCentreBottomBaseBottom = bottomPipe.getX() + 150;

        // coordinate del punto in mezzo nello spazio libero
        double y = (yCentreBottomBaseUpper + yCentreBottomBaseBottom) / 2;
        double x = (xCentreBottomBaseUpper + xCentreBottomBaseBottom) / 2;

        return new Point( (int) x, (int) y);
    }

    // calcolare la distanza euclidea tra il Bird e il punto centrale 'libero'
    public double getDistance(Rectangle bird){
        return 0;
    }

    // true jump, false no-jump
    public boolean ObjectiveFunction(){
        return false;
    }
}
