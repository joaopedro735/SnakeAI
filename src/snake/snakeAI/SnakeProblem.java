package snake.snakeAI;

import snake.Environment;
import snake.snakeAI.ga.Problem;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SnakeProblem implements Problem<SnakeIndividual> {
    private static final int NUM_NN_INPUTS = 9; // TODO THIS IS A FAKE NUMBER; PLEASE ADAPT TO YOUR CASE
    private static final int NUM_NN_OUTPUTS = 4; // TODO THIS IS A FAKE NUMBER; PLEASE ADAPT TO YOUR CASE
    private  int GENOME_SIZE; // TODO THIS IS A FAKE NUMBER; PLEASE ADAPT TO YOUR CASE

    final private int environmentSize;
    private int maxIterations;
    private int numInputs;
    private int numHiddenUnits;
    public int numOutputs;
    private int numEnvironmentRuns;
    private int tipoProblema;
    final private Environment environment;

    public SnakeProblem(
            int environmentSize,
            int maxIterations,
            int numHiddenUnits,
            int numEnvironmentRuns,
            int tipoProblema) {
        System.out.println("Construtor AI");
        this.environmentSize = environmentSize;
        this.maxIterations = maxIterations;
        this.numInputs = NUM_NN_INPUTS;
        this.numHiddenUnits = numHiddenUnits;
        this.numOutputs = NUM_NN_OUTPUTS;
        this.numEnvironmentRuns = numEnvironmentRuns;
        this.tipoProblema = tipoProblema;
        GENOME_SIZE=numInputs*numHiddenUnits+(numHiddenUnits+1)*numOutputs;

        environment = new Environment(environmentSize,
                maxIterations,tipoProblema,numInputs,numHiddenUnits,numOutputs);
    }

    public SnakeProblem(int tipoProblema) {
        this.maxIterations = 500;
        this.numEnvironmentRuns = 10;
        this.environmentSize = 10;
        System.out.println("Construtor adhoc/random");
        environment = new Environment(
                environmentSize,
                maxIterations,
                tipoProblema);
    }

    @Override
    public SnakeIndividual getNewIndividual() {
        return new SnakeIndividual(this, GENOME_SIZE /*TODO?*/);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public int getNumEvironmentSimulations() {
        return numEnvironmentRuns;
    }

    // MODIFY IF YOU DEFINE OTHER PARAMETERS
    public static SnakeProblem buildProblemFromFile(File file, int tipoProblema) throws IOException {
        java.util.Scanner f = new java.util.Scanner(file);

        List<String> lines = new LinkedList<>();

        while (f.hasNextLine()) {
            String s = f.nextLine();
            if (!s.equals("") && !s.startsWith("//")) {
                lines.add(s);
            }
        }

        List<String> parametersValues = new LinkedList<>();
        for (String line : lines) {
            String[] tokens = line.split(":");
            parametersValues.add(tokens[1].trim());
        }
        System.out.println("Problem:" + tipoProblema);
        int environmentSize = Integer.parseInt(parametersValues.get(0));
        int maxIterations = Integer.parseInt(parametersValues.get(1));
        int numHiddenUnits = Integer.parseInt(parametersValues.get(2));
        int numEnvironmentRuns = Integer.parseInt(parametersValues.get(3));
        return new SnakeProblem(
                environmentSize,
                maxIterations,
                numHiddenUnits,
                numEnvironmentRuns,
                tipoProblema);
    }

    // MODIFY IF YOU DEFINE OTHER PARAMETERS
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Environment size: ");
        sb.append(environmentSize);
        sb.append("\n");
        sb.append("Maximum number of iterations: ");
        sb.append(maxIterations);
        sb.append("\n");
        sb.append("Number of inputs: ");
        sb.append(numInputs);
        sb.append("\n");
        sb.append("Number of hidden units: ");
        sb.append(numHiddenUnits);
        sb.append("\n");
        sb.append("Number of outputs: ");
        sb.append(numOutputs);
        sb.append("\n");
        sb.append("Number of environment simulations: ");
        sb.append(numEnvironmentRuns);
        return sb.toString();
    }


    public Environment setEnvironment() {
        return this.environment;
    }
}
