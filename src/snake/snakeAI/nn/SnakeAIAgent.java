package snake.snakeAI.nn;

import snake.*;
import snake.snakeAI.ga.Individual;
import snake.snakeAI.ga.RealVectorIndividual;

import java.awt.Color;

public abstract class SnakeAIAgent extends SnakeAgent {
   
    final protected int inputLayerSize;
    final protected int hiddenLayerSize;
    final protected int outputLayerSize;

    /**
     * Network inputs array.
     */
    final protected int[] inputs;
    /**
     * Hiddden layer weights.
     */
    final protected double[][] w1;
    /**
     * Output layer weights.
     */
    final protected double[][] w2;
    /**
     * Hidden layer activation values.
     */
    final protected double[] hiddenLayerOutput;
    /**
     * Output layer activation values.
     */
    final protected int[] output;

    public SnakeAIAgent(
            Cell cell,
            int inputLayerSize,
            int hiddenLayerSize,
            int outputLayerSize,
            Environment environment, Color color) {
        super(cell, color, environment);
        this.inputLayerSize = inputLayerSize;
        this.hiddenLayerSize = hiddenLayerSize;
        this.outputLayerSize = outputLayerSize;
        inputs = new int[inputLayerSize];
        inputs[inputs.length - 1] = -1; //bias entry
        w1 = new double[inputLayerSize][hiddenLayerSize]; // the bias entry for the hidden layer neurons is already counted in inputLayerSize variable
        w2 = new double[hiddenLayerSize + 1][outputLayerSize]; // + 1 due to the bias entry for the output neurons
        hiddenLayerOutput = new double[hiddenLayerSize + 1];
        hiddenLayerOutput[hiddenLayerSize] = -1; // the bias entry for the output neurons
        output = new int[outputLayerSize];
    }


    /**
     * Initializes the network's weights
     * 
     * @param weights vector of weights comming from the individual.
     */

    public void setWeights(double[] weights) {
        // TODO: recebe o genoma e divide em w1 e w2
        int k = 0;
        //w1
        for (int i = 0; i < inputLayerSize; i++) {
            for (int j = 0; j < hiddenLayerSize; j++) {
                w1[i][j] = weights[k];
                k++;
            }
        }
        for (int i = 0; i < hiddenLayerSize + 1; i++) {
            for (int j = 0; j < outputLayerSize; j++) {
                w2[i][j] = weights[k];
                k++;
            }
        } }
    
    /**
     * Computes the output of the network for the inputs saved in the class
     * vector "inputs".
     *
     */
    protected void forwardPropagation() {
        // TODO
        for (int i = 0; i < hiddenLayerSize; i++) { // percorre os neurónios da camda
            double somapesada = 0;
            for (int j = 0; j < inputLayerSize; j++) { // percorre os inputs
                somapesada += inputs[j] * w1[j][i];
            }

            hiddenLayerOutput[i] = sigmoidFunction(somapesada);
        }
        int maior = 0;
        double maiorSum = Double.MIN_VALUE;
        for (int i = 0; i < outputLayerSize; i++) {
            double somapesada = 0;

            for (int j = 0; j < hiddenLayerSize + 1; j++) {
                somapesada += hiddenLayerOutput[j] * w2[j][i];

            }
            output[i] = 0;
            if(somapesada > maiorSum){
                maiorSum = somapesada;
                maior = i;
            }

        }
        output[maior] = 1;
    }

    private double sigmoidFunction(double somapesada) {
        return 1 / (1 + Math.exp(-somapesada));
    }

    @Override
    protected Action decide(Perception perception) {
        // TODO: caixa fechada
        //preencher os inputs;
        preencherInputs(perception);
        forwardPropagation();

        if(output[0]==1)
            return Action.WEST;
        if(output[1]==1)
            return Action.NORTH;
        if(output[2]==1)
            return Action.EAST;
        if(output[3]==1)
            return Action.SOUTH;
        return null;
    }

    protected abstract void preencherInputs(Perception perception);
}
