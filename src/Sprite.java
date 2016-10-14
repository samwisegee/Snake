import java.awt.*;

/*
2d array of colors
 */
class Sprite {
    private static Color b = new Color(0, 0, 0);
    private static Color u1 = new Color(0, 0, 150);
    private static Color u2 = new Color(0, 0, 255);
    private static Color g = new Color(0, 255, 0);
    private static Color r = new Color(255, 0, 0);
    private static Color w = new Color(255, 255, 255);

    static Color[][] EMPTY = {
            {b,b,b,b},
            {b,b,b,b},
            {b,b,b,b},
            {b,b,b,b}
    };

    static Color[][] BARRIER_L = {
            {u1,u1,u1,u1},
            {u1,u1,u1,u1},
            {u2,u2,u2,u2},
            {u2,u2,u2,u2}
    };

    static Color[][] BARRIER_R = {
            {u2,u2,u2,u2},
            {u2,u2,u2,u2},
            {u1,u1,u1,u1},
            {u1,u1,u1,u1}
    };

    static Color[][] BARRIER_T = {
            {u1,u1,u2,u2},
            {u1,u1,u2,u2},
            {u1,u1,u2,u2},
            {u1,u1,u2,u2}
    };

    static Color[][] BARRIER_B = {
            {u2,u2,u1,u1},
            {u2,u2,u1,u1},
            {u2,u2,u1,u1},
            {u2,u2,u1,u1}
    };

    static Color[][] HEAD = {
            {b,g,g,b},
            {g,b,g,g},
            {g,g,b,g},
            {b,g,g,b}
    };

    static Color[][] TAIL = {
            {b,b,b,b},
            {b,g,g,b},
            {b,g,g,b},
            {b,b,b,b}
    };

    static Color[][] APPLE = {
            {b,r,r,b},
            {r,w,r,r},
            {r,r,r,r},
            {b,r,r,b}
    };
}
