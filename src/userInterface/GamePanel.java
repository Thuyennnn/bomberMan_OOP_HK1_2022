package userInterface;

import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.Bomberman;
import gameobject.GameWorld;
import gameobject.PhysicalMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private Thread thread;
    private boolean isRunning = true;
    private InputManager inputManager;

    private BufferedImage buffImg;
    private Graphics2D buffG2D;

    GameWorld gameWorld;


    public GamePanel() {
        gameWorld = new GameWorld();
        inputManager = new InputManager(gameWorld);
        buffImg = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }
    public void  paint(Graphics g) {
        g.drawImage(buffImg, 0, 0, this);
    }



    public void updateGame() {
        gameWorld.Update();

    }
    public void renderGame() {
        if(buffImg == null) {
            buffImg = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        }
        else {
            buffG2D = (Graphics2D) buffImg.getGraphics();
        }

        if(buffG2D != null) {
            buffG2D.setColor(Color.white);
//            buffG2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);

            gameWorld.Render(buffG2D);

        }
    }
    public void startGame() {
        if(thread == null) {
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }


    @Override
    public void run() {

        long FPS = 80;
        long period = 1000 * 1000000 / FPS;
        long beginTime;
        long sleepTime;

        beginTime = System.nanoTime();
        while ((isRunning)) {

            updateGame();
            renderGame();
            repaint();
            long dentaTime = System.nanoTime() - beginTime;
            sleepTime = period - dentaTime;
            try {
                if(sleepTime > 0) {
                    Thread.sleep(sleepTime/1000000);
                }
                else Thread.sleep(period/2000000);
            } catch (InterruptedException ex) {}

            beginTime = System.nanoTime();

        }
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
