package snake;

public class Perception {
    // TODO
    private Cell n, s, e, w;

    public Perception(Cell n, Cell s, Cell e, Cell o) {
        this.n = n;
        this.s = s;
        this.e = e;
        this.w = o;
    }

    public Cell getE() {
        return e;
    }

    public Cell getN() {
        return n;
    }

    public Cell getW() {
        return w;
    }

    public Cell getS() {
        return s;
    }
}
