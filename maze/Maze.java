package maze;

public class Maze {

    protected int dimension;                 // dimension of maze
    protected boolean[][] north;     // is there a wall to north of cell i, j
    protected boolean[][] east;
    protected boolean[][] south;
    protected boolean[][] west;
    protected int delay = 10;  // controls the speed of the animation

    public Maze(boolean[][] north, boolean[][] east, boolean[][] south, boolean[][] west) {
        dimension = north.length-2;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        StdDraw.setXscale(0, dimension + 2);
        StdDraw.setYscale(0, dimension + 2);
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(dimension / 2 + 0.5, dimension / 2 + 0.5, 0.375);
        StdDraw.filledCircle(1.5, 1.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= dimension; x++) {
            for (int y = 1; y <= dimension; y++) {
                if (south[x][y]) {
                    StdDraw.line(x, y, x + 1, y);
                }
                if (north[x][y]) {
                    StdDraw.line(x, y + 1, x + 1, y + 1);
                }
                if (west[x][y]) {
                    StdDraw.line(x, y, x, y + 1);
                }
                if (east[x][y]) {
                    StdDraw.line(x + 1, y, x + 1, y + 1);
                }
            }
        }
        StdDraw.show(1000);
    }
    

    public int getDimension() {
        return dimension;
    }

    public boolean hasNorthWall(int i, int j) {
        return north[i][j];
    }

    public boolean hasSouthWall(int i, int j) {
        return south[i][j];
    }

    public boolean hasWestWall(int i, int j) {
        return west[i][j];
    }

    public boolean hasEastWall(int i, int j) {
        return east[i][j];
    }

    public void drawBlue(int i, int j) {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(i + 0.5, j + 0.5, 0.25);
        StdDraw.show(delay);
    }

    public void drawGray(int i, int j) {
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledCircle(i + 0.5, j + 0.5, 0.25);
        StdDraw.show(delay);
    }

    public void drawGray2(int i, int j){
	StdDraw.setPenColor(StdDraw.DARK_GRAY);
	StdDraw.filledCircle(i + 0.5, j + 0.5, 0.25);
	StdDraw.show(delay);
    }

    public void clearDots() {
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                StdDraw.filledCircle(i + 0.5, j + 0.5, 0.25);
            }
        }
        StdDraw.show(0);
    }

    public void highlight(int i, int j) {
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.filledCircle(i + 0.5, j + 0.5, 0.25);
        StdDraw.show(0);
    }

    public int getGoalX() {
        return dimension / 2;
    }

    public int getGoalY() {
        return dimension / 2;
    }

    public int getStartX() {
        return 1;
    }

    public int getStartY() {
        return 1;
    }
}
