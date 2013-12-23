package maze;

public class MazeGenerator {

    protected static int dimension;                 // dimension of maze
    protected static boolean[][] north;     // is there a wall to north of cell i, j
    protected static boolean[][] east;
    protected static boolean[][] south;
    protected static boolean[][] west;
    protected static boolean[][] visited;
    protected static double size;
    protected static boolean done = false;
    protected int delay = 10;  // controls the speed of the animation

    public static Maze getMaze(int dimension0, int nrDeletedWalls) {
        dimension = dimension0;
        init();
        generate(nrDeletedWalls);
        return new Maze(north, east, south, west);
    }

    private static void init() {
        // initialize border cells as already visited
        visited = new boolean[dimension + 2][dimension + 2];
        for (int x = 0; x < dimension + 2; x++) {
            visited[x][0] = visited[x][dimension + 1] = true;
        }
        for (int y = 0; y < dimension + 2; y++) {
            visited[0][y] = visited[dimension + 1][y] = true;
        }


        // initialze all walls as present
        north = new boolean[dimension + 2][dimension + 2];
        east = new boolean[dimension + 2][dimension + 2];
        south = new boolean[dimension + 2][dimension + 2];
        west = new boolean[dimension + 2][dimension + 2];
        for (int x = 0; x < dimension + 2; x++) {
            for (int y = 0; y < dimension + 2; y++) {
                north[x][y] = east[x][y] = south[x][y] = west[x][y] = true;
            }
        }
    }

    // generate the maze
    private static void generate(int x, int y) {
        visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1] || !visited[x - 1][y]) {

            // pick random neighbor (could use Knuth's trick instead)
            while (true) {
                double r = Math.random();
                if (r < 0.25 && !visited[x][y + 1]) {
                    north[x][y] = south[x][y + 1] = false;
                    generate(x, y + 1);
                    break;
                } else if (r >= 0.25 && r < 0.50 && !visited[x + 1][y]) {
                    east[x][y] = west[x + 1][y] = false;
                    generate(x + 1, y);
                    break;
                } else if (r >= 0.5 && r < 0.75 && !visited[x][y - 1]) {
                    south[x][y] = north[x][y - 1] = false;
                    generate(x, y - 1);
                    break;
                } else if (r >= 0.75 && r < 1.00 && !visited[x - 1][y]) {
                    west[x][y] = east[x - 1][y] = false;
                    generate(x - 1, y);
                    break;
                }
            }
        }
    }

    // generate the maze starting from lower left
    private static void generate(int nrDeletedWalls) {
        generate(1, 1);

        // delete some random walls
        for (int i = 0; i < nrDeletedWalls; i++) {
            int x = (int) (1 + Math.random() * (dimension - 1));
            int y = (int) (1 + Math.random() * (dimension - 1));
            if (Math.random() < 0.5) {
                north[x][y] = south[x][y + 1] = false;
            } else {
                east[x][y] = west[x + 1][y] = false;
            }
        }

    }
}
