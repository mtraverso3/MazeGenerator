public class Cell {

    private Wall wall;
    private int step;
    private int depth;

    public Cell(Wall wall, int step) {
        this.wall = wall;
        this.step = step;
    }

    public Wall getWall() {
        return wall;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getStep() {
        return step;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }
}
