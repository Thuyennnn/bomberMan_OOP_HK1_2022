package userInterface;


import effect.CacheDataLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;

    GamePanel gamePanel;
    public GameFrame () {
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH, SCREEN_HEIGHT);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

       try {
           CacheDataLoader.getInstance().loadData();
       }
       catch (IOException ex) {
           ex.printStackTrace();
       }

        gamePanel = new GamePanel();
        this.add(gamePanel);

        this.addKeyListener(gamePanel);
    }

    public void startGame() {
        gamePanel.startGame();
    }
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
        gameFrame.startGame();
    }
}