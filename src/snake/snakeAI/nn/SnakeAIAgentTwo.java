package snake.snakeAI.nn;

import snake.*;

import java.awt.*;

public class SnakeAIAgentTwo extends SnakeAIAgent {

    public SnakeAIAgentTwo(
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


        //Food norte
        if (n != null && n.hasFood())
            inputs[4] = 1;
        else
            inputs[4] = 0;

        //Food este
        if (e != null && e.hasFood())
            inputs[5] = 1;
        else
            inputs[5] = 0;

        //Food sul
        if (s != null && s.hasFood())
            inputs[6] = 1;
        else
            inputs[6] = 0;

        //Food oeste
        if (w != null && w.hasFood())
            inputs[7] = 1;
        else
            inputs[7] = 0;
        //TODO
        /*if ((environment.getFood().getCell().getColumn() == getCell().getColumn()))
            inputs[8] = 1;
        else
            inputs[8] = 0;

        if ((environment.getFood().getCell().getLine() == getCell().getLine()))
            inputs[9] = 1;
        else
            inputs[9] = 0;*/

    }
}
