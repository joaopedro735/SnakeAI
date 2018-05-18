package snake.snakeAI.nn;

import snake.*;
import snake.snakeAI.ga.Individual;
import snake.snakeAI.ga.RealVectorIndividual;
import snake.snakeAI.ga.geneticOperators.MutationFourChoices;

import java.awt.Color;

public class SnakeAIAgent extends SnakeAgent {
   
    final private int inputLayerSize;
    final private int hiddenLayerSize;
    final private int outputLayerSize;

    /**
     * Network inputs array.
     */
    final private int[] inputs;
    /**
     * Hiddden layer weights.
     */
    final private double[][] w1;
    /**
     * Output layer weights.
     */
    final private double[][] w2;
    /**
     * Hidden layer activation values.
     */
    final private double[] hiddenLayerOutput;
    /**
     * Output layer activation values.
     */
    final private int[] output;

    public SnakeAIAgent(
            Cell cell,
            int inputLayerSize,
            int hiddenLayerSize,
            int outputLayerSize) {
        super(cell, Color.BLUE,null);
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
        }
}
    
    /**
     * Computes the output of the network for the inputs saved in the class
     * vector "inputs".
     *
     */
    private void forwardPropagation() {
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

        //
        int directions[] = possibleMoves(perception);
        int foodDir[] = findFood(perception);
        int k = 0;
        for (int i = k; i < 4; i++, k++) {
            inputs[i] = directions[k];
        }
        for (int i = k; i < 8; i++, k++) {
            inputs[i] = foodDir[k];
        }


        forwardPropagation();
        if(output[0]==1)
            return Action.WEST;
        if(output[1]==1)
            return Action.SOUTH;
        if(output[2]==1)
            return Action.EAST;
        if(output[3]==1)
            return Action.SOUTH;
        return null;
    }

    private int[] findFood(Perception perception) {
        int[] result = new int[4];
        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();

        if (n.hasFood()) {
            result[0] = 1;
        }

        if (s.hasFood()) {
            result[1] = 1;
        }

        if (w.hasFood()) {
            result[2] = 1;
        }

        if (e.hasFood()) {
            result[3] = 1;
        }
        return result;
    }

    private int[] possibleMoves(Perception perception) {
        int[] result = new int[4];
        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();

        if (n != null && !n.hasTail() && !n.hasAgent()) {
            result[0] = 1;
        }

        if (s != null && !s.hasTail() && !s.hasAgent()) {
            result[1] = 1;
        }

        if (w != null && !w.hasTail() && !w.hasAgent()) {
            result[2] = 1;
        }

        if (e != null && !e.hasTail() && !e.hasAgent()) {
            result[3] = 1;
        }

        return result;
    }
}
