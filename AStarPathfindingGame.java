import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AStarPathfindingGame extends JPanel implements MouseListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TILE_SIZE = 20;

    
    private Point playerPos;   
    private Point targetPos;
    private boolean targetSet = false;
    private List <Point> path;
    private List<Point> obstacles;

    public AStarPathfindingGame() {
        playerPos = new Point(0, 0);
        targetPos = new Point(0, 0);
        path = new ArrayList<>();
        obstacles = new ArrayList<>();
        generateObstacles(50);
        
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseListener(this);
    }

    private void generateObstacles(int count) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int x = rand.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
            int y = rand.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
            if (!playerPos.equals(new Point(x, y)) && !obstacles.contains(new Point(x, y))) {
                obstacles.add(new Point(x, y));
            }
        }
    }

    private List<Point> aStar(Point start, Point goal) {
        Set<Point> openSet = new HashSet<>();
        Set<Point> closedSet = new HashSet<>();
        Map<Point, Point> cameFrom = new HashMap<>();
        Map<Point, Integer> gScore = new HashMap<>();
        Map<Point, Integer> fScore = new HashMap<>();

        gScore.put(start, 0);
        fScore.put(start, heuristic(start, goal));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Point current = getLowestFScore(openSet, fScore);
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (Point neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor) || obstacles.contains(neighbor)) continue;
                
                int tentativeGScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + 1;

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentativeGScore >= gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    continue;
                }

                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentativeGScore);
                fScore.put(neighbor, tentativeGScore + heuristic(neighbor, goal));
            }
        }

        return new ArrayList<>(); // No path found
    }

    private int heuristic(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // Manhattan distance
    }

    private Point getLowestFScore(Set<Point> openSet, Map<Point, Integer> fScore) {
        Point lowest = null;
        for (Point point : openSet) {
            if (lowest == null || fScore.getOrDefault(point, Integer.MAX_VALUE) < fScore.getOrDefault(lowest, Integer.MAX_VALUE)) {
                lowest = point;
            }
        }
        return lowest;
    }

    private List<Point> getNeighbors(Point point) {
        List<Point> neighbors = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (Math.abs(dx) == Math.abs(dy)) continue; // Skip diagonal neighbors
                int newX = point.x + dx * TILE_SIZE;
                int newY = point.y + dy * TILE_SIZE;
                if (isInBounds(newX, newY)) {
                    neighbors.add(new Point(newX, newY));
                }
            }
        }
        return neighbors;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    private List<Point> reconstructPath(Map<Point, Point> cameFrom, Point current) {
        List<Point> totalPath = new ArrayList<>();
        totalPath.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(current);
        }
        Collections.reverse(totalPath);
        return totalPath;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw player
        g.setColor(Color.BLUE);
        g.fillRect(playerPos.x, playerPos.y, TILE_SIZE, TILE_SIZE);

        // Draw target
        if (targetSet) {
            g.setColor(Color.GREEN);
            g.fillRect(targetPos.x, targetPos.y, TILE_SIZE, TILE_SIZE);
        }

        // Draw obstacles
        g.setColor(Color.RED);
        for (Point obstacle : obstacles) {
            g.fillRect(obstacle.x, obstacle.y, TILE_SIZE, TILE_SIZE);
        }

        // Draw path
        g.setColor(Color.BLACK);
        for (Point p : path) {
            g.fillRect(p.x, p.y, TILE_SIZE, TILE_SIZE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        targetPos.setLocation(e.getX() - e.getX() % TILE_SIZE, e.getY() - e.getY() % TILE_SIZE);
        targetSet = true;
        path = aStar(playerPos, targetPos);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("A* Pathfinding Game");
        AStarPathfindingGame gamePanel = new AStarPathfindingGame();
        frame.add(gamePanel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/* make sure to not touch the code if you're going to test how it works. 
add enhancements as you wish once you encounter with the game and it's working. Happy game day!! */
