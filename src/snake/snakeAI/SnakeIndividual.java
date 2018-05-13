package snake.snakeAI;

import snake.Environment;
import snake.snakeAI.ga.GeneticAlgorithm;
import snake.snakeAI.ga.RealVectorIndividual;

import static snake.snakeAI.ga.GeneticAlgorithm.random;

public class SnakeIndividual extends RealVectorIndividual<SnakeProblem, SnakeIndividual> {


    public SnakeIndividual(SnakeProblem problem, int size/*TODO?*/) {
        super(problem, size);
        //TODO?
    }

    public SnakeIndividual(SnakeIndividual original) {
        super(original);
        //TODO
    }

    @Override
    public double computeFitness() {
        //TODO:coloco a mover 500x *10simulacoes (para 10 seeds(pos inicial, valores de pessos))
        Environment environment = problem.setEnvironment();
        for (int i = 0; i < problem.getNumEvironmentSimulations(); i++) {
            //TODO: seed needs initialization ?????
            int seed = 0;
            environment.initialize(seed);
            ///environment.setWeights(genome);
            environment.getSnakes().get(i).setWeights(genome);
            environment.simulate();
            //TODO: fitness ?????? "+= 0 " está errado
            fitness += 0;
        }
        foods = environment.getFoods();
        movements = environment.getMovements();
        return 0;
    }

    public double[] getGenome(){
        //TODO
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nfitness: ");
        sb.append(fitness);
        sb.append("\nmovimentos: ");
        sb.append(foods);
        sb.append("\ncomidas: ");
        sb.append(movements);
        //TODO
        return sb.toString();
    }

    /**
     *
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(SnakeIndividual i) {
        //TODO
        return 0;
    }

    @Override
    public SnakeIndividual clone() {
        return new SnakeIndividual(this);
    }
}
