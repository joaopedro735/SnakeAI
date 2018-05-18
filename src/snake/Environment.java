package snake;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import gui.PanelSimulation;
import snake.snakeAI.nn.SnakeAIAgent;
import snake.snakeAdhoc.SnakeAdhocAgent;
import snake.snakeRandom.SnakeRandomAgent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {

    public Random random;
    private final Cell[][] grid;
    private final List<SnakeAgent> agents;
    private Food food;
    private int foods;
    private int movements;
    private final int maxIterations;
    private int tipoProblema;
    private int numInputs;
    private int numHiddenUnits;
    public int numOutputs;

    public Environment(
            int size,
            int maxIterations,
            int tipoProblema) {

        this.grid = new Cell[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }

        this.maxIterations = maxIterations;
        this.tipoProblema = tipoProblema;
        this.agents = new ArrayList<>();
        this.random = new Random();
    }


    public Environment(
            int size,
            int maxIterations,
            int tipoProblema,
            int numInputs,
            int numHiddenUnits,
            int numOutputs) {

        this(size,maxIterations,tipoProblema);
        this.numInputs = numInputs;
        this.numHiddenUnits = numHiddenUnits;
        this.numOutputs = numOutputs;
    }

    public void initialize(int seed) {
        random.setSeed(seed);
        placeAgents();
        placeFood();
    }

    // TODO MODIFY TO PLACE ADHOC OR AI SNAKE AGENTS
    private void placeAgents() {
        //Limpar agentes e caudas
        if(agents.size() > 0) {
                for (SnakeAgent agent: agents) {
                agent.getCell().setAgent(null);
                for (Tail tail: agent.getTailList()) {
                    tail.getCell().setTail(null);
                }
                agent.getTailList().clear();
            }
        }

        agents.clear();
        //Criar agentes
        switch(this.tipoProblema){
            case 0:
                agents.add(new SnakeAdhocAgent(grid[random.nextInt(grid.length)][random.nextInt(grid.length)],
                        Color.BLACK,
                        this));
                break;
            case 1:
                agents.add(new SnakeRandomAgent(grid[random.nextInt(grid.length)][random.nextInt(grid.length)],
                        Color.GREEN, this));
                break;
            case 2:
                agents.add(new SnakeAIAgent(grid[random.nextInt(grid.length)][random.nextInt(grid.length)],
                        numInputs, numHiddenUnits,numOutputs));
                break;
        }
    }

    protected void placeFood() {
        // TODO
        System.out.println("food");
        //Limpar comida
        if(food != null){
            food.getCell().setFood(null);
        }
        food = null;
        //Criar comida
        Cell cell;
        do{
            cell = getCell(random.nextInt(getNumLines()),random.nextInt(getNumColumns()));
        }while(cell.hasAgent() || cell.hasTail());
        food = new Food(cell);
    }

    public void simulate() {
        // TODO
        int moves = 0;
        System.out.println("simulate");
        for (int i = 0; i < maxIterations && !agents.get(0).isDead(); i++) {
            for (SnakeAgent agent : agents) {
                agent.act();
                fireUpdatedEnvironment();
                moves++;
            }
        }
        setMovements(moves);
        fireUpdatedEnvironment();
    }

    public int getSize() {
        return grid.length;
    }

        public Cell getNorthCell(Cell cell) {
        if (cell.getLine() > 0) {
            return grid[cell.getLine() - 1][cell.getColumn()];
        }
        return null;
    }

    public Cell getSouthCell(Cell cell) {
        if (cell.getLine() < grid.length - 1) {
            return grid[cell.getLine() + 1][cell.getColumn()];
        }
        return null;
    }

    public Cell getEastCell(Cell cell) {
        if (cell.getColumn() < grid[0].length - 1) {
            return grid[cell.getLine()][cell.getColumn() + 1];
        }
        return null;
    }

    public Cell getWestCell(Cell cell) {
        if (cell.getColumn() > 0) {
            return grid[cell.getLine()][cell.getColumn() - 1];
        }
        return null;
    }

    public int getNumLines() {
        return grid.length;
    }

    public int getNumColumns() {
        return grid[0].length;
    }

    public final Cell getCell(int linha, int coluna) {
        return grid[linha][coluna];
    }

    public Color getCellColor(int linha, int coluna) {
        return grid[linha][coluna].getColor();
    }

    //listeners
    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getTipoProblema() {
        return tipoProblema;
    }

    public void setWeights(double[] genome) {
        //TODO para cada agente setWeights
        for (SnakeAIAgent agentAI: getSnakes()) {
            agentAI.setWeights(genome);
        }
    }

    public List<SnakeAIAgent> getSnakes() {
        //TODO devolver snakeAIagents
        ArrayList<SnakeAIAgent> agentsAI = new ArrayList<>();
        agentsAI.add((SnakeAIAgent) agents.get(0));
        return agentsAI;
    }

    public int getFoods() {
        return foods;
    }

    public void setFoods(int value) {
        foods = value;
    }

    public int getMovements() {
        return movements;
    }

    public void setMovements(int value) {
        movements = value;
    }
}
