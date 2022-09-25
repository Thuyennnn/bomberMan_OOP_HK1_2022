package userInterface;

import effect.Animation;
import effect.FrameImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class GamePanel extends JPanel implements Runnable, KeyListener {

    Thread gameThread;
    private boolean isRunning = true;
    InputManager inputManager;

    public GamePanel() throws IOException {

        inputManager = new InputManager();

    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long previousTime = System.nanoTime();
        long currentTime;
        long sleepTime;

        long period = 1000000000/80;

        while (isRunning) {

            repaint();
            currentTime = System.nanoTime();
            sleepTime = period - (currentTime - previousTime);
            try{
                if(sleepTime > 0)
                    Thread.sleep(sleepTime/1000000);
                else Thread.sleep(period/2000000);

            }catch(Exception e){}

            previousTime = System.nanoTime();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0 , GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);

        Graphics2D g2 = (Graphics2D) g;

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.processKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.processKeyRelease(e.getKeyCode());
    }
}
