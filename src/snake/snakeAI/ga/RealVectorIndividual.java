package snake.snakeAI.ga;

public abstract class RealVectorIndividual <P extends Problem, I extends RealVectorIndividual> extends Individual<P, I>{
    // TODO
    // a dos ga das aulas
    //

    protected double[] genome;

    public RealVectorIndividual(P problem, int size) {
        super(problem);
        // TODO
        genome = new double[size];
    }

    public RealVectorIndividual(RealVectorIndividual<P, I> original) {
        super(original);
        // TODO
        this.genome = new double[original.genome.length];
        System.arraycopy(original, 0, genome, 0, genome.length);

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
        other.genome[index] = aux;
    }
}
