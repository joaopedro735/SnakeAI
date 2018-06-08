package snake.snakeAI.ga.geneticOperators;

import snake.snakeAI.ga.GeneticAlgorithm;
import snake.snakeAI.ga.RealVectorIndividual;

public class MutationInsert<I extends RealVectorIndividual> extends Mutation<I> {

    public MutationInsert(double probability) {
        super(probability);
    }

    @Override
    public void run(I ind) {
        int pos1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int pos2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        if (GeneticAlgorithm.random.nextDouble() < probability) {
            if(pos1 < pos2) {
                double a2 = ind.getGene(pos2);
                for (int i = pos2 - 1; i > pos1; i--) {
                    ind.setGene(i + 1, ind.getGene(i));
                }
                ind.setGene(pos2+1,a2);
            }else{
                double a1 = ind.getGene(pos1);
                for (int i = pos1 - 1; i > pos2; i--) {
                    ind.setGene(i + 1, ind.getGene(i));
                }
                ind.setGene(pos2+1,a1);
            }

        }
    }
}
