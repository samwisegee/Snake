import java.awt.*;
import java.util.ArrayList;

class Snake {
    private boolean alive;
    int x;
    int y;
    char direction;
    ArrayList<Point> tail = new ArrayList<>();

    Snake(int x, int y) {
        alive = true;
        this.x = x;
        this.y = y;
        direction = 'n';
        tail.add(new Point(x, y + 1));
    }

    void move(Board board) {
        Tile nextTile = board.getNextTile(x, y, direction);
        if (nextTile.isTraversable()) {
            tail.add(0, new Point(x, y));
            if (nextTile.getType().equals("apple")) {
                board.spawnApple();
            } else {
                tail.remove(tail.size() - 1);
            }
            if (direction == 'n') y--;
            if (direction == 's') y++;
            if (direction == 'w') x--;
            if (direction == 'e') x++;
        } else {
            alive = false;
        }
        board.setSnake(this);
    }

    void setDirection(char direction) {
        this.direction = direction;
    }

    boolean isAlive() {
        return alive;
    }
}
