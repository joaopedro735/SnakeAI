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
            System.out.println("WEST");
            return Action.WEST;
        }
        if (n != null && !n.hasTail() && !n.hasAgent() && n.hasFood()) {
            System.out.println("NORTH");
            return Action.NORTH;
        }
        if (e != null && !e.hasTail() && !e.hasAgent() && e.hasFood()) {
            System.out.println("EAST");
            return Action.EAST;
        }
        if (s != null && !s.hasTail() && !s.hasAgent() && s.hasFood()) {
            System.out.println("SOUTH");
            return Action.SOUTH;
        }


        if (w != null && !w.hasTail() && !w.hasAgent() && cell.getColumn() > environment.getFood().getCell().getColumn()) {
            System.out.println("WEST");
            return Action.WEST;
        }
        if (n != null && !n.hasTail() && !n.hasAgent() && cell.getLine() > environment.getFood().getCell().getLine()) {
            System.out.println("NORTH");
            return Action.NORTH;
        }
        if (e != null && !e.hasTail() && !e.hasAgent() && cell.getColumn() < environment.getFood().getCell().getColumn()) {
            System.out.println("EAST");
            return Action.EAST;
        }
        if (s != null && !s.hasTail() && !s.hasAgent() && cell.getLine() < environment.getFood().getCell().getLine()) {
            System.out.println("SOUTH");
            return Action.SOUTH;
        }

        if (w != null && !w.hasTail() && !w.hasAgent()) {
            System.out.println("WEST");
            return Action.WEST;
        }
        if (n != null && !n.hasTail() && !n.hasAgent()) {
            System.out.println("NORTH");
            return Action.NORTH;
        }
        if (e != null && !e.hasTail() && !e.hasAgent()) {
            System.out.println("EAST");
            return Action.EAST;
        }
        if (s != null && !s.hasTail() && !s.hasAgent()) {
            System.out.println("SOUTH");
            return Action.SOUTH;
        }

        return null;
    }

}
