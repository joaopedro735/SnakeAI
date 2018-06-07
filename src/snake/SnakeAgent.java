package snake;

import java.awt.Color;
import java.util.ArrayList;

public abstract class SnakeAgent {

    protected Cell cell;
    protected Color color;
    protected Environment environment;
    private int foods = 0;

    public ArrayList<Tail> getTailList() {
        return tailList;
    }

    private ArrayList<Tail> tailList;
    private boolean isDead;

    public SnakeAgent(Cell cell, Color color,Environment environment) {
        this.cell = cell;
        if(cell != null){this.cell.setAgent(this);}
        this.color = color;
        this.environment = environment;
        this.tailList = new ArrayList<>();
    }

    public void act() {
        Perception perception = buildPerception();
        Action action = decide(perception);
        execute(action);
    }

    protected Perception buildPerception() {
        // TODO
        return new Perception(
                environment.getNorthCell(cell),
                environment.getSouthCell(cell),
                environment.getEastCell(cell),
                environment.getWestCell(cell));
    }

    public int getFoods() {
        return foods;
    }

    protected void execute(Action action)
    {
        // TODO
        Cell nextCell = null;

        if (action == Action.NORTH && cell.getLine() != 0) {
            nextCell = environment.getNorthCell(cell);
        } else if (action == Action.SOUTH && cell.getLine() != environment.getNumLines() - 1) {
            nextCell = environment.getSouthCell(cell);
        } else if (action == Action.WEST && cell.getColumn() != 0) {
            nextCell = environment.getWestCell(cell);
        } else if (action == Action.EAST && cell.getColumn() != environment.getNumColumns() - 1) {
            nextCell = environment.getEastCell(cell);
        }

        if (nextCell != null && !nextCell.hasTail() && !nextCell.hasAgent()) {
            if(nextCell.hasFood())
            {
                //Cresce
                //cell.setTail(new Tail(cell));
                tailList.add(0,new Tail(cell));
                //Se nao houver comida e houver caudas
                //Move
            }else if(!tailList.isEmpty()){
                //adiciona uma tail na posicao
                //cell.setTail(new Tail(cell));
                //poe na lista na primeira posicao
                tailList.add(0,new Tail(cell));
                //remove a ultima
                int lastTail = tailList.size() - 1;
                tailList.get(lastTail).getCell().setTail(null);
                tailList.remove(lastTail);
                //cell.setTail(tailList.get(tailList.size()-1));
            }
            //Avança o agente
            setCell(nextCell);

            //Por a comida apenas depois
            if(cell.hasFood())
            {
                nextCell.setFood(null);
                environment.placeFood();
                foods++;
            }
        }
        else{
            isDead = true;
        }
    }

    protected abstract Action decide(Perception perception);

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell newCell) {
        if(this.cell != null){this.cell.setAgent(null);}
        this.cell = newCell;
        if(newCell != null){newCell.setAgent(this);}
    }    
    
    public Color getColor() {
        return color;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }



}
