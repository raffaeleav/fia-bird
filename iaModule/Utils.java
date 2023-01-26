package iaModule;

import flappyBird.FlappyBird;

import java.awt.*;

public class Utils {

    // metodo per calcolare il centro dello spazio libero tra 2 pipe
    public Point getPipeHole(Rectangle upperPipe){

        double x = upperPipe.getX() + 50;
        double y = upperPipe.getY() - 90;

        return new Point( (int) x, (int) y);
    }

    // calcolare la distanza euclidea tra il Bird e il punto centrale 'libero'
    public double getDistance(double xBird, double yBird, Point point){
        return point.distance(xBird, yBird);
    }

    // true jump, false no-jump
    public boolean jumpObjectiveFunction(Rectangle upperPipe, Rectangle Bird){
        // Punto centrale della pipe
        Point pipeHole = this.getPipeHole(upperPipe);

        // Se il bird non salta viene incrementato il valore di y di 2 poiché l'asse y è invertito
        // 0 Punto massimo
        // 676 Punto minimo
        double nojumpdistance = getDistance(Bird.getX(), Bird.getY() + 2, pipeHole);
        double jumpDistance = getDistance(Bird.getX(), Bird.getY() - 10, pipeHole);

        return nojumpdistance <= jumpDistance ? false : true;
    }

    public double distanceObjectiveFunction(Rectangle upperPipe, Rectangle Bird){
        Point pipeHole = this.getPipeHole(upperPipe);

        // Se il bird non salta viene incrementato il valore di y di 2 poiché l'asse y è invertito
        // 0 Punto massimo
        // 676 Punto minimo
        double nojumpdistance = getDistance(Bird.getX(), Bird.getY() + 2, pipeHole);
        double jumpDistance = getDistance(Bird.getX(), Bird.getY() - 10, pipeHole);

        return Math.min(nojumpdistance, jumpDistance);
    }

    // il metodo permette di riportare il JFrame all' azione precedente (in caso di salto)
    public void rewindBirdJump(){
        for (int i = 0; i < FlappyBird.flappyBird.columns.size(); i++) {
            Rectangle column = FlappyBird.flappyBird.columns.get(i);

            // permette di portare i tubi allo stato precedente, 10 rappresenta la velocita' a cui si muovono
            column.x += 10 * 9;
        }

        FlappyBird.flappyBird.metri -= 0.2;

        // rimette il bird nella posizione precedente
        FlappyBird.flappyBird.bird.y += 10 * 9;

        FlappyBird.flappyBird.renderer.repaint();
    }

    // il metodo permette di riportare il JFrame all' azione precedente (in caso di non salto)
    public void rewindBirdNoJump(){
        for (int i = 0; i < FlappyBird.flappyBird.columns.size(); i++) {
            Rectangle column = FlappyBird.flappyBird.columns.get(i);

            // permette di portare i tubi allo stato precedente, 10 rappresenta la velocita' a cui si muovono
            column.x += 10 * 9;
        }

        FlappyBird.flappyBird.metri -= 0.2;

        // rimette il bird nella posizione precedente
        FlappyBird.flappyBird.bird.y -= 2 * 9;

        FlappyBird.flappyBird.renderer.repaint();
    }
}
