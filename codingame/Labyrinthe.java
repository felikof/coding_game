import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * Une fois téléporté dans la structure, votre mission sera de :
 * atteindre la salle de commande depuis laquelle vous désactiverez le champ de force
 * revenir à votre position de départ une fois le champ de force désactivé
 **/
class Labyrinthe {

    static int[] ROW_MOVES = {-1, 1, 0, 0}; // UP, DOWN, LEFT, RIGHT
    static int[] COL_MOVES = {0, 0, -1, 1}; // UP, DOWN, LEFT, RIGHT
    static char WALL = '#';
    static char EMPTY = '.';
    static char START = 'T';
    static char END = 'C';
    static char UNKNOWN = '?';

    static class Position {
        int row;
        int col;
        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Node {
        Position p;
        int cost;
        Node parent;
        char dir;
        Node(Position p, int cost, Node parent, char dir) {
            this.p = p;
            this.cost = cost;
            this.parent = parent;
            this.dir = dir;
        }
    }

    static class Maze {
        char[][] grid;
        int R;
        int C;
        Position start;
        Position end;
        Maze(int R, int C) {
            this.R = R;
            this.C = C;
            this.grid = new char[R][C];
        }
        public boolean isInBound(Position p) {
            return p.row >= 0 && p.row < R && p.col >= 0 && p.col < C;
        }
        public boolean isWalkable(Position p) {
            return isInBound(p) && (grid[p.row][p.col] == EMPTY || grid[p.row][p.col] == END);
        }
        public boolean isNotScanned(Position p) {
            return isInBound(p) && (grid[p.row][p.col] == UNKNOWN);
        }
        public List<Position> getNeighbors(Position p) {
            List<Position> neighbors = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int newRow = p.row + ROW_MOVES[i];
                int newCol = p.col + COL_MOVES[i];
                Position neighbor = new Position(newRow, newCol);
                if (isInBound(neighbor) && grid[neighbor.row][neighbor.col] != WALL) {
                    neighbors.add(neighbor);
                }
            }
            return neighbors;
        }
    }

    public static Node node(Maze maze, Position start, char goal) {
        List<Node> l = new ArrayList<>();
        boolean[][] visited = new boolean[maze.R][maze.C];
        Node[][] parent = new Node[maze.R][maze.C];
        l.add(new Node(start, 0, null, ' '));
        visited[start.row][start.col] = true;

        while (!l.isEmpty()) {
            Node current = l.remove(0);
            if (maze.grid[current.p.row][current.p.col] == goal) {
                return current;
            }
            List<Position> neighbors = maze.getNeighbors(current.p);
            for (Position neighbor : neighbors) {
                if (!visited[neighbor.row][neighbor.col]) {
                    visited[neighbor.row][neighbor.col] = true;
                    char dir = getDirection(current.p, neighbor);
                    Node nextNode = new Node(neighbor, current.cost + 1, current, dir);
                    l.add(nextNode);
                    parent[neighbor.row][neighbor.col] = nextNode;
                }
            }
        }
        return null;
    }

    public static String findPath(Maze maze, Position start, Position goal) {
        Node result = node(maze, start, maze.grid[goal.row][goal.col]);
        if (result == null) {
            return " ";
        }
        StringBuilder path = new StringBuilder();
        while (result.parent != null) {
            path.append(result.dir);
            result = result.parent;
        }
        return path.reverse().toString();
    }

    public static char getDirection(Position from, Position to) {
        if (to.row < from.row) return 'U';
        if (to.row > from.row) return 'D';
        if (to.col < from.col) return 'L';
        return 'R';
    }

    public static Position findUnknown(Maze maze, Position start) {
        Node result = node(maze, start, UNKNOWN);
        return result != null ? result.p : null;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // number of rows.
        int C = in.nextInt(); // number of columns.
        int A = in.nextInt(); // number of rounds between the time the alarm countdown is activated and the time the alarm goes off.

        Maze maze = new Maze(R, C);
        boolean controlVisited = false;
        // game loop
        while (true) {
            int KR = in.nextInt(); // row where Rick is located.
            int KC = in.nextInt(); // column where Rick is located.

            for (int i = 0; i < R; i++) {
                //String ROW = in.next(); 
                String line = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
                for (int j = 0; j < C; j++) {
                    maze.grid[i][j] = line.charAt(j);
                    if (maze.grid[i][j] == START) {
                        maze.start = new Position(i, j);
                    } else if (maze.grid[i][j] == END) {
                        maze.end = new Position(i, j);
                    }

                }
            }
            if (maze.grid[KR][KC] == END) {
                controlVisited = true;
            }
            Position currentP = new Position(KR, KC);
            Position target = controlVisited ? maze.start : (findUnknown(maze, currentP) != null ? findUnknown(maze, currentP) : maze.end);
            String path = findPath(maze, currentP, target);

            if (path.isEmpty()) {
                System.out.println ("ERROR");
            } else {
                System.out.println (path.charAt(0) == 'U' ? "UP":
                                    path.charAt(0) == 'D' ? "DOWN":
                                    path.charAt(0) == 'L' ? "LEFT" : "RIGHT");
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            //System.out.println("RIGHT"); // Rick's next move (UP DOWN LEFT or RIGHT).
        }
    }
}