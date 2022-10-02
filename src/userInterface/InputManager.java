package userInterface;

import gameobject.GameWorld;

import java.awt.event.KeyEvent;

public class InputManager {

    GameWorld gameWorld;

    public InputManager(GameWorld gameWorld) {

        this.gameWorld = gameWorld;

    }

    public void processKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                gameWorld.getBomberman().setSpeedX(-5);
//                gameWorld.getPhysicalMap().x-=3;
                break;
            case KeyEvent.VK_S:
                gameWorld.getBomberman().setSpeedY(5);
//                gameWorld.getPhysicalMap().y+=3;
                break;
            case KeyEvent.VK_D:
                gameWorld.getBomberman().setSpeedX(5);
//                gameWorld.getPhysicalMap().x+=3;
                break;
            case KeyEvent.VK_W:
                gameWorld.getBomberman().setSpeedY(-5);
//                gameWorld.getPhysicalMap().y-=3;
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("press space");
                break;
        }
    }

    public void processKeyRelease(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                gameWorld.getBomberman().setSpeedX(0);
                break;
            case KeyEvent.VK_S:
                gameWorld.getBomberman().setSpeedY(0);
                break;
            case KeyEvent.VK_D:
                gameWorld.getBomberman().setSpeedX(0);
                break;
            case KeyEvent.VK_W:
                gameWorld.getBomberman().setSpeedY(0);
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Released space");
                break;
        }
    }
}
