package snake.snakeAI.ga;

public abstract class Individual<P extends Problem, I extends Individual> implements Comparable<I>{

    protected double fitness;
    protected int movements = 0;
    protected int foods = 0;
    protected P problem;

    public Individual(P problem) {
        this.problem = problem;
    }

    public Individual(Individual<P, I> original) {
        this.problem = original.problem;
        this.fitness = original.fitness;
    }

    public abstract double computeFitness();
    
    public abstract int getNumGenes();
    
    public abstract void swapGenes(I other, int g);    

    public double getFitness() {
        return fitness;
    }

    @Override
    public abstract I clone();
}
