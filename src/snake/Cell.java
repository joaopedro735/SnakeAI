package snake;

import java.awt.Color;

public class Cell {
    private final int line, column;
    private SnakeAgent agent = null;
    private Food food = null;
    private Tail tail = null;

    public Cell(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public Color getColor() {
        if (agent != null) {
            return agent.getColor();
        }else if (food != null) {
            return food.getColor();
        }else if (tail != null) {
            return tail.getColor();
        }
        return Color.WHITE;
    }

    public SnakeAgent getAgent() {
        return agent;
    }

    public void setAgent(SnakeAgent agent) {
        this.agent = agent;
    }

    public boolean hasAgent() {
        return agent != null;
    }

    public Food getFood(){
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public boolean hasFood(){
        return food != null;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Tail getTail() {
        return tail;
    }

    public void setTail(Tail tail) {
        this.tail = tail;
    }

    public boolean hasTail() {
        return tail != null;
    }
}