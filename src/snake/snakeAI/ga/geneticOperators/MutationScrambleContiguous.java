package snake.snakeAI.ga.geneticOperators;

import snake.snakeAI.ga.GeneticAlgorithm;
import snake.snakeAI.ga.Individual;
import snake.snakeAI.ga.RealVectorIndividual;

import java.util.ArrayList;
import java.util.List;

public class MutationScrambleContiguous<I extends RealVectorIndividual> extends Mutation<I> {

    public MutationScrambleContiguous(double probability) {
        super(probability);
    }

    @Override
    public String toString() {
        return "Scramble contiguous mutation (" + probability + ")";
    }

    @Override
    public void run(I ind) {
        /*
        *   Escolhe duas posições e entre as mesmas(contiguous) mistura os genes.
        */
        int pos1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes()); //gera fronteira inicial
        int pos2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes()); // gera fronteira final
        if (pos2 < pos1) {
            int postemp = pos1;
            pos1 = pos2;
            pos2 = postemp;
        }
        for (int i = pos1; i < pos2; i++) {
            if (GeneticAlgorithm.random.nextDouble() < probability) {
                    int randomPosition = GeneticAlgorithm.random.nextInt(pos2-pos1);
                    double temp = ind.getGene(i);
                    ind.setGene(i, ind.getGene(randomPosition));
                    ind.setGene(randomPosition, temp);
            }
        }

    }


}
