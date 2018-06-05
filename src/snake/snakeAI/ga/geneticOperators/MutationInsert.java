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
            for(int i = pos2-1; i > pos1 ; i--){
                double temp = ind.getGene(i+1);
                ind.setGene(i+1,ind.getGene(i));
                ind.setGene(i,temp);
            }
        }
    }
}
