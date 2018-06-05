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
    public void run(I ind) {
        /*
        *   Escolhe duas posições e entre as mesmas(contiguous) mistura os genes.
        */
        //TODO: Fronteiras não estão dentro dos limites. É preciso resolver.
        int pos1 = GeneticAlgorithm.random.nextInt( ind.getNumGenes() ); //gera fronteira inicial
        int pos2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes()); // gera fronteira final
        bubbleSort(ind,pos1,pos2);
    }

    private void bubbleSort(I ind,int pos1, int pos2) {
        //Bubble sort por 2 motivos
        //  Pouca complexidade temporal
        //  Adaptável ao problema que temos
        int n = ind.getNumGenes();
        double temp;
        int aux;
        //se a fronteira inicial estiver à frente da fronteira final
        if(pos1 < pos2){
            aux = pos1;
            pos1 = pos2;
            pos2 = aux;
        }
        for(int i=pos1; i < pos2; i++){
            for(int j=pos1+1; j < (n-i); j++){
                if(ind.getGene(j-1) > ind.getGene(j)){
                    //swap elements
                    temp = ind.getGene(j-1);
                    ind.setGene(j-1,ind.getGene(j));
                    ind.setGene(j,temp);
                }
            }
        }
    }
}
