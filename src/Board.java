import java.util.Random;

class Board {
    Tile[][] tiles;
    private Random rand = new Random(System.nanoTime());
    private int width;
    private int height;

    Board(Snake snake, int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];

        //Fill board with empty tiles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile("empty");
            }
        }

        //Add barrier tiles to edges
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == 0) {
                    tiles[x][y] = new Tile("barrierL");
                } else if (x == width - 1) {
                    tiles[x][y] = new Tile("barrierR");
                } else if (y == 0) {
                    tiles[x][y] = new Tile("barrierT");
                } else if (y == height - 1) {
                    tiles[x][y] = new Tile("barrierB");
                }
            }
        }

        //Add corners

        //Add the snake
        tiles[snake.x][snake.y] = new Tile("head");
        tiles[snake.tail.get(0).x][snake.tail.get(0).y] = new Tile("tail");

        //Add an apple
        spawnApple();
    }

    void spawnApple() {
        int x, y;
        do {
            x = rand.nextInt(width);
            y = rand.nextInt(height);
        } while (!tiles[x][y].getType().equals("empty"));
            tiles[x][y] = new Tile("apple");
    }

    Tile getNextTile(int x, int y, char dir) {
        switch (dir) {
            case 'n':
                return tiles[x][y - 1];
            case 's':
                return tiles[x][y + 1];
            case 'w':
                return tiles[x - 1][y];
            case 'e':
                return tiles[x + 1][y];
            default:
                return null;
        }
    }

    void setSnake(Snake snake) {
        //Remove old snake information
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y].getType().equals("head") || tiles[x][y].getType().equals("tail")) {
                    tiles[x][y] = new Tile("empty");
                }
            }
        }
        //Add new snake information
        if (snake.isAlive()) {
            tiles[snake.x][snake.y] = new Tile("head");
            snake.tail.forEach((p) -> tiles[p.x][p.y] = new Tile("tail"));
        } else {
            tiles[snake.x][snake.y] = new Tile("deadhead");
            snake.tail.forEach((p) -> tiles[p.x][p.y] = new Tile("tail"));
        }
    }
}

