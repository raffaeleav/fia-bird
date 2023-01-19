package iaModule;

import java.util.Arrays;
import java.util.Random;

public class HillClimbing {
    private int[] currentSolution;
    private int[] bestSolution;
    private int currentFitness;
    private int bestFitness;
    private Random rand;

    public HillClimbing(int size) {
        currentSolution = new int[size];
        bestSolution = new int[size];
        rand = new Random();
        initialize();
    }

    private void initialize() {
        // generiamo una soluzione casuale iniziale
        for (int i = 0; i < currentSolution.length; i++) {
            currentSolution[i] = rand.nextInt(20);
        }
        currentFitness = calculateFitness(currentSolution);
        bestFitness = currentFitness;
        System.arraycopy(currentSolution, 0, bestSolution, 0, currentSolution.length);
    }

    private int calculateFitness(int[] solution) {
        // calcoliamo la fitness della soluzione passata come parametro
        int fitness = 0;
        for (int i = 0; i < solution.length; i++) {
            fitness += solution[i] * solution[i];
        }
        return fitness;
    }

    public void run() {
        while (true) {
            int[] newSolution = new int[currentSolution.length];
            System.arraycopy(currentSolution, 0, newSolution, 0, currentSolution.length);
            // generiamo una nuova soluzione modificando un singolo elemento
            int index = rand.nextInt(newSolution.length);
            newSolution[index] += rand.nextInt(10) - 5;
            int newFitness = calculateFitness(newSolution);
            if (newFitness < currentFitness) {
                // la nuova soluzione è migliore, aggiorniamo la soluzione corrente
                currentFitness = newFitness;
                System.arraycopy(newSolution, 0, currentSolution, 0, currentSolution.length);
                if (currentFitness < bestFitness) {
                    // la nuova soluzione è anche migliore della soluzione migliore trovata finora
                    bestFitness = currentFitness;
                    System.arraycopy(currentSolution, 0, bestSolution, 0, currentSolution.length);
                }
            } else {
                // la nuova soluzione non è migliore, terminiamo l'algoritmo
                break;
            }
        }
    }

    public int[] getBestSolution() {
        return bestSolution;
    }

    public int getBestFitness() {
        return bestFitness;
    }

    public static void main(String[] args) {
        HillClimbing hc = new HillClimbing(100);
        hc.run();
        System.out.println("Best solution: " + Arrays.toString(hc.getBestSolution()));
        System.out.println("Best fitness: " + hc.getBestFitness());
    }
}
