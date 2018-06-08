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
        //Substituição das variaveis do novo pq nao é feito no clone
        fitness = original.fitness;
        foods = original.foods;
        movements = original.movements;
        //TODO
    }

    @Override
    public double computeFitness() {
        //TODO:coloco a mover 500x * 10simulacoes (para 10 seeds)
        Environment environment = problem.setEnvironment();
        movements=0;
        foods=0;
        fitness=0;
        int penalty = 0;
        for (int i = 0; i < problem.getNumEvironmentSimulations(); i++) {
            //TODO: seed needs initialization ?????
            environment.initialize(i);
            environment.setWeights(genome);
            environment.simulate();
            foods += environment.getFoods();
            movements += environment.getMovements();
            if(environment.getTipoProblema() == 5) {
                 penalty += Math.abs(environment.getFoods1() - environment.getFoods2());
            }
        }
        //penalty
        fitness = foods * 1000 + movements - (penalty * 1000);
        return fitness;
    }

    public double[] getGenome(){
        //TODO
        return genome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nfitness: ");
        sb.append(fitness);
        sb.append("\nmovimentos: ");
        sb.append((double) movements/problem.getNumEvironmentSimulations());
        sb.append("\ncomidas: ");
        sb.append((double) foods/problem.getNumEvironmentSimulations());
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
        if(fitness > i.fitness)
            return 1;
        if(fitness < i.fitness)
            return -1;
        return 0;
    }

    @Override
    public SnakeIndividual clone() {
        return new SnakeIndividual(this);
    }
}
