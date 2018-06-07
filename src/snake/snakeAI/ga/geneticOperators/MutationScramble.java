package snake.snakeAI.ga.geneticOperators;

import snake.snakeAI.ga.GeneticAlgorithm;
import snake.snakeAI.ga.RealVectorIndividual;

public class MutationScramble<I extends RealVectorIndividual> extends Mutation<I> {

    public MutationScramble(double probability) {
        super(probability);
    }

    @Override
    public void run(I ind) {
        for (int i = 0; i < ind.getNumGenes(); i++) {
            if (GeneticAlgorithm.random.nextDouble() < probability) {
                    int randomPosition = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
                    double temp = ind.getGene(i);
                    ind.setGene(i, ind.getGene(randomPosition));
                    ind.setGene(randomPosition, temp);
            }
        }

    }


}
