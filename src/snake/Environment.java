package snake;

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
    private int numOutputs;
    private int numSnakesAI = 1;
    public boolean stop;

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

        this(size, maxIterations, tipoProblema);
        this.numInputs = numInputs;
        this.numHiddenUnits = numHiddenUnits;
        this.numOutputs = numOutputs;
        if (maxIterations == 300) {
            numSnakesAI = 2;
        }
    }

    public void initialize(int seed) {
        random.setSeed(seed);
        placeAgents();
        placeFood();
    }

    // TODO MODIFY TO PLACE ADHOC OR AI SNAKE AGENTS
    private void placeAgents() {
        //Limpar agentes e caudas
        if (agents.size() > 0) {
            for (SnakeAgent agent : agents) {
                agent.getCell().setAgent(null);
                for (Tail tail : agent.getTailList()) {
                    tail.getCell().setTail(null);
                }
                agent.getTailList().clear();
            }
        }

        agents.clear();
        //Criar agentes
        switch (this.tipoProblema) {
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
                        numInputs, numHiddenUnits, numOutputs, this, Color.blue));
                if (numSnakesAI == 2)
                    agents.add(new SnakeAIAgent(grid[random.nextInt(grid.length)][random.nextInt(grid.length)],
                            numInputs, numHiddenUnits, numOutputs, this, Color.magenta));
                break;
        }
    }

    protected void placeFood() {
        // TODO
        //Limpar comida
        if (food != null) {
            food.getCell().setFood(null);
        }
        food = null;
        //Criar comida
        Cell cell;
        do {
            cell = getCell(random.nextInt(getNumLines()), random.nextInt(getNumColumns()));
        } while (cell.hasAgent() || cell.hasTail());
        food = new Food(cell);
    }

    public void simulate() {

        foods = 0;
        int i;
        stop = false;
        for (i = 0; i < maxIterations && !stop; i++) {
            for (SnakeAgent agent : agents) {
                agent.act();
                fireUpdatedEnvironment();
            }
        }
        setMovements(i);
        setFoods();
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
        //((SnakeAIAgent)agents.get(0)).setWeights(genome);
        for (SnakeAgent agent :
                agents) {
            ((SnakeAIAgent) agent).setWeights(genome);
        }
    }

    public List<SnakeAgent> getSnakes() {
        //TODO devolver snakeAgents
        return agents;
    }

    public int getFoods() {
        return foods;
    }

    public void setFoods() {
        int sum = 0;
        for (SnakeAgent agent :
                agents) {
            sum += agent.getFoods();
        }
        foods = sum;
    }

    public int getMovements() {
        return movements;
    }

    public void setMovements(int value) {
        movements = value;
    }

    public void setNumSnakesAI(int numSnakesAI) {
        this.numSnakesAI = numSnakesAI;
    }
}
