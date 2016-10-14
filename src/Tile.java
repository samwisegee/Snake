import java.awt.*;

class Tile {
    private String type;
    private boolean traversable;
    private String orientation;
    private Color color;
    private Color[][] sprite;

    Tile(String type){
        this.type = type;
        switch (type) {
            case "empty":
                traversable = true;
                color = new Color(0, 0, 0);
                sprite = Sprite.EMPTY;
                break;
            case "barrierL":
                traversable = false;
                color = new Color(0, 0, 160);
                sprite = Sprite.BARRIER_L;
                break;
            case "barrierR":
                traversable = false;
                color = new Color(0, 0, 160);
                sprite = Sprite.BARRIER_R;
                break;
            case "barrierT":
                traversable = false;
                color = new Color(0, 0, 160);
                sprite = Sprite.BARRIER_T;
                break;
            case "barrierB":
                traversable = false;
                color = new Color(0, 0, 160);
                sprite = Sprite.BARRIER_B;
                break;
            case "apple":
                traversable = true;
                color = new Color(255, 0, 0);
                sprite = Sprite.APPLE;
                break;
            case "head":
                traversable = false;
                color = new Color(0, 150, 0);
                sprite = Sprite.HEAD;
                break;
            case "tail":
                traversable = false;
                color = new Color(0, 255, 0);
                sprite = Sprite.TAIL;
                break;
            case "deadhead":
                traversable = false;
                color = new Color(0, 85, 0);
                sprite = Sprite.HEAD;
                break;
        }
    }

    String getType() {
        return type;
    }

    boolean isTraversable() {
        return traversable;
    }

    Color getColor() {
        return color;
    }

    Color[][] getSprite() {
        return sprite;
    }
}
