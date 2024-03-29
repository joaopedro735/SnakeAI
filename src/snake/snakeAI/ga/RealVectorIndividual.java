package snake.snakeAI.ga;

public abstract class RealVectorIndividual <P extends Problem, I extends RealVectorIndividual> extends Individual<P, I>{
    // TODO
    // a dos ga das aulas

    protected double[] genome;

    public RealVectorIndividual(P problem, int size) {
        super(problem);
        genome = new double[size];
        for (int g = 0; g < genome.length; g++) {
            genome[g] = GeneticAlgorithm.random.nextDouble()* 2 - 1 ;
        }
    }

    public RealVectorIndividual(RealVectorIndividual<P, I> original) {
        super(original);
        this.genome = new double[original.genome.length];
        System.arraycopy(original.genome, 0, genome, 0, genome.length);
    }
    
    @Override
    public int getNumGenes() {
        // TODO
        return genome.length;
    }
    
    public double getGene(int index) {
        // TODO
        return genome[index];
    }
    
    public void setGene(int index, double newValue) {
        // TODO
        genome[index] = newValue;
    }

    @Override
    public void swapGenes(RealVectorIndividual other, int index) {
        // TODO
        double aux = genome[index];
        genome[index] = other.genome[index];
        other.genome[index] = aux;}
}
