package snake.snakeAI.nn;

import snake.*;

import java.awt.*;

public class SnakeAIAgentBlah extends SnakeAIAgent {

    public SnakeAIAgentBlah(
            Cell cell,
            int inputLayerSize,
            int hiddenLayerSize,
            int outputLayerSize,
            Environment environment,
            Color color) {
        super(cell, inputLayerSize, hiddenLayerSize, outputLayerSize, environment, color);

    }


    @Override
    protected void preencherInputs(Perception perception) {
        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();

        //Pode ir para NORTE
        if (n != null && !n.hasTail() && !n.hasAgent()) {
            inputs[0] = 1;
        }
        else
            inputs[0] = 0;
        //Pode ir para ESTE
        if (e != null && !e.hasTail() && !e.hasAgent()) {
            inputs[1] = 1;
        }
        else
            inputs[1] = 0;

        //Pode ir para SUL
        if (s != null && !s.hasTail() && !s.hasAgent()) {
            inputs[2] = 1;
        }
        else
            inputs[2] = 0;

        //Pode ir para OESTE
        if (w != null && !w.hasTail() && !w.hasAgent()) {
            inputs[3] = 1;
        }
        else
            inputs[3] = 0;
    }
}
