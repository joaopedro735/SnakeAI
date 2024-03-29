package snake.snakeRandom;

import snake.*;

import java.awt.*;
import java.util.Random;

public class SnakeRandomAgent extends SnakeAgent {
    private static final int NORTH = 0;
    private static final int SOUTH = 1;
    private static final int EAST = 2;
    private static final int WEST = 3;
    private Random random = new Random();


    public SnakeRandomAgent(Cell cell, Color color, Environment environment) {
        super(cell, color, environment);
    }

    @Override
    protected Action decide(Perception perception) {
        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();

        // se puder ir para norte E tiver lixo
        //  vai para norte
        // senão se puder ir para sul E tiver lixo
        //  vai para sul
        // senão se puder ir para este E tiver lixo
        //  vai para este
        // senão se puder ir para oeste E tiver lixo
        //  vai para oeste

        //Rule #1
        if (w != null && !w.hasTail() && !w.hasAgent() && w.hasFood()) {
            return Action.WEST;
        }
        if (n != null && !n.hasTail() && !n.hasAgent() && n.hasFood()) {
            return Action.NORTH;
        }
        if (e != null && !e.hasTail() && !e.hasAgent() && e.hasFood()) {
            return Action.EAST;
        }
        if (s != null && !s.hasTail() && !s.hasAgent() && s.hasFood()) {
            return Action.SOUTH;
        }

        //Rule #2
        //NORTH = 0, SOUTH = 1, EAST = 2, WEST = 3;
        //Random number between 0 and 3 and switch case
        //if chosen direction is impossible, try the next set of rules
        int direction = random.nextInt(Action.values().length);
        switch (direction) {
            case (NORTH):
                if (n != null && !n.hasTail() && !n.hasAgent()) {
                    return Action.NORTH;
                }
                break;
            case (SOUTH):
                if (s != null && !s.hasTail() && !s.hasAgent()) {
                    return Action.SOUTH;
                }
                break;
            case (EAST):
                if (e != null && !e.hasTail() && !e.hasAgent()) {
                    return Action.EAST;
                }
                break;
            case (WEST):
                if (w != null && !w.hasTail() && !w.hasAgent()) {
                    return Action.WEST;
                }
                break;

        }

        //Rule #3
        // se puder ir para norte (não está na linha 1 e
        if (w != null && !w.hasTail() && !w.hasAgent()) {
            return Action.WEST;
        }
        if (n != null && !n.hasTail() && !n.hasAgent()) {
            return Action.NORTH;
        }
        if (e != null && !e.hasTail() && !e.hasAgent()) {
            return Action.EAST;
        }
        if (s != null && !s.hasTail() && !s.hasAgent()) {
            return Action.SOUTH;
        }


        return null;
    }
}
