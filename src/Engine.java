/*
Game Engine 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

public class Engine extends JPanel {
    interface Game {
        Dimension getClientDimension();
        double getTargetUpdates();
        void setInput(int input);
        boolean isRunning();
        void update();
        void paint(Graphics2D g2);
    }

    private Game game;

    private boolean exit;
    private double TARGET_UPDATE_TIME; //In nanoseconds
    private double TARGET_RENDER_TIME; //In nanoseconds

    //Debug info
    private boolean displayDebug = false;
    private long frame = 0;
    private int fps = 0;

    private Engine(Game game) {
        this.game = game;
        exit = false;
        TARGET_UPDATE_TIME = 1000000000/this.game.getTargetUpdates();
        TARGET_RENDER_TIME = 1000000000/60; //Denominator represents maximum FPS

        try {
            SwingUtilities.invokeAndWait(() -> {
                JFrame frame = new JFrame("Snake");
                setPreferredSize(this.game.getClientDimension());
                setIgnoreRepaint(true);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.add(this);
                frame.setResizable(false);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                frame.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_F1) {
                            displayDebug = !displayDebug;
                        } else {
                            game.setInput(e.getKeyCode());
                        }
                    }
                });
            });
        } catch (InterruptedException | InvocationTargetException e) {e.printStackTrace();}

        gameLoop();
    }

    private void gameLoop() {
        double timeSinceLastUpdate = 0;
        double timeSinceLastFrame = 0;
        double frameTimer = 0;
        int fps = 0;
        double lastLoopStartTime = System.nanoTime();
        double thisLoopStartTime, timeSinceLastLoop;

        while (!exit) {
            thisLoopStartTime = System.nanoTime();
            timeSinceLastLoop = thisLoopStartTime - lastLoopStartTime;
            timeSinceLastFrame += timeSinceLastLoop;
            frameTimer += timeSinceLastLoop;
            if (game.isRunning()) {
                timeSinceLastUpdate += timeSinceLastLoop;

                while (timeSinceLastUpdate >= TARGET_UPDATE_TIME) {
                    game.update();
                    timeSinceLastUpdate -= TARGET_UPDATE_TIME;
                }
            }

            if (timeSinceLastFrame >= TARGET_RENDER_TIME) {
                repaint();
                fps++;
                timeSinceLastFrame = 0;
            }

            if (frameTimer >= 1000000000) {
                this.fps = fps;
                fps = 0;
                frameTimer = 0;
            }

            lastLoopStartTime = thisLoopStartTime;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        frame++;
        Graphics2D g2 = (Graphics2D) g;
        game.paint(g2);
        if (displayDebug) paintDebugInfo(g2);
    }

    private void paintDebugInfo(Graphics2D g2) {
        g2.setFont(new Font("monospace", Font.BOLD, 12));
        g2.setColor(Color.white);
        g2.drawString(Long.toString(frame), 5, 15);
        g2.drawString(Integer.toString(fps), 5, 28);
    }

    public static void main(String[] args) {
        new Engine(new SnakeGame());
    }
}
