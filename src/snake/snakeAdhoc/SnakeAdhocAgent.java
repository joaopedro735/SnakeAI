package snake.snakeAdhoc;

import snake.*;

import java.awt.*;


public class SnakeAdhocAgent extends SnakeAgent {


    public SnakeAdhocAgent(Cell cell, Color color, Environment environment) {
        super(cell, color,environment);
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


        if (w != null && !w.hasTail() && !w.hasAgent() && cell.getColumn() > environment.getFood().getCell().getColumn()) {
            return Action.WEST;
        }
        if (n != null && !n.hasTail() && !n.hasAgent() && cell.getLine() > environment.getFood().getCell().getLine()) {
            return Action.NORTH;
        }
        if (e != null && !e.hasTail() && !e.hasAgent() && cell.getColumn() < environment.getFood().getCell().getColumn()) {
            return Action.EAST;
        }
        if (s != null && !s.hasTail() && !s.hasAgent() && cell.getLine() < environment.getFood().getCell().getLine()) {
            return Action.SOUTH;
        }

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
