public class Cell {

    private Wall wall;
    private int step;
    private int depth;
    private boolean visited;

    public Cell(Wall wall, boolean visited) {
        this.wall = wall;
        this.visited = visited;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
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

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
