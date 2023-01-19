package iaModule;

import flappyBird.*;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Utils {
    private final int distanzaConSalto = 2;
    private final int distanzaSenzaSalto = 10;

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
    public double getDistance(double xBird, double yBird, Point point){
        return point.distance(xBird, yBird);
    }

    // true jump, false no-jump
    public boolean ObjectiveFunctionHillClimbing(Rectangle upperPipe, Rectangle bottomPipe, Rectangle Bird){
        // Punto centrale della pipe
        Point pipeHole = this.getPipeHole(upperPipe,bottomPipe);

        // Se il bird non salta viene incrementato il valore di y di 2 poiché l'asse y è invertito
        // 0 Punto massimo
        // 676 Punto minimo
        double noJumpDistance = getDistance(Bird.getX(), Bird.getY() + distanzaConSalto, pipeHole);
        double JumpDistance = getDistance(Bird.getX(), Bird.getY() - distanzaSenzaSalto, pipeHole);


        return !(noJumpDistance > JumpDistance);
    }
}
