import java.awt.*;
import java.awt.event.KeyEvent;

class SnakeGame implements Engine.Game {
    private boolean running;
    private int input;
    private int scale = 5; //Pixels per sprite square side
    private int resolution = 4; //Squares per tile dimension
    private int width = 24; //In tiles
    private int height = 24; //In tiles
    private Board board;
    private Snake snake;

    public Dimension getClientDimension() {
        return new Dimension(width * scale * resolution, height * scale * resolution);
    }

    public double getTargetUpdates() {
        return 10; //Engine updates per second (for Snake this represents tile moves per second)
    }

    public boolean isRunning() {
        return running;
    }

    SnakeGame() {
        newGame();
    }

    private void newGame() {
        running = true;
        snake = new Snake(7, 13); //Snake spawn coordinates
        board = new Board(snake, width, height);
    }

    public void setInput(int input) {
        if (input == KeyEvent.VK_SPACE) {
            running = !running;
        } else {
            this.input = input;
        }
    }

    public void update() {
        processInput();
        if (!snake.isAlive()) {
            return;
        }
        snake.move(board);
    }

    private void processInput() {
        switch (input) {
            case KeyEvent.VK_R:
                if (!snake.isAlive()) newGame();
                break;
            case KeyEvent.VK_W:
                if (snake.direction != 's') snake.setDirection('n');
                break;
            case KeyEvent.VK_S:
                if (snake.direction != 'n') snake.setDirection('s');
                break;
            case KeyEvent.VK_A:
                if (snake.direction != 'e') snake.setDirection('w');
                break;
            case KeyEvent.VK_D:
                if (snake.direction != 'w') snake.setDirection('e');
                break;
        }
        input = 0;
    }

    public void paint(Graphics2D g2) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                paintSprite(g2, x, y, board.tiles[x][y].getSprite());

//                g2.setColor(board.tiles[x][y].getColor());
//                g2.fillRect(x * scale * resolution, y * scale * resolution, scale * resolution, scale * resolution);

                if (!running) {
                    g2.setColor(new Color(255, 255, 255));
                    g2.drawString("Paused", 50, 50);
                }
            }
        }
    }

    private void paintSprite(Graphics2D g2, int x, int y, Color[][] sprite) {
        for (int i = 0; i < sprite.length; i++) {
            for (int j = 0; j < sprite[0].length; j++) {
                g2.setColor(sprite[i][j]);
                g2.fillRect((x * scale * resolution) + (i * scale), (y * scale * resolution) + (j * scale), scale, scale);
            }
        }
    }
}
