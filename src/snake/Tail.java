package snake;

import java.awt.*;

public class Tail {
    public static final Color COLOR = Color.GRAY;

    private Cell cell;

    public Tail(Cell cell) {
        this.cell = cell;
        if (cell != null)
            this.cell.setTail(this);
    }

    public Color getColor() {
        return COLOR;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        if (this.cell != null)
            this.cell.setFood(null);
        this.cell = cell;
        if (cell != null)
            this.cell.setTail(this);
    }
}
