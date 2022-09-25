package userInterface;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;

    GamePanel gamePanel;
    GameFrame() throws IOException {
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocation((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel();

        this.add(gamePanel);
        this.addKeyListener(gamePanel);
    }

    public static void main(String[] args) throws IOException {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
    }

}
