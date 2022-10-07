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

                    gameWorld.getBomberman().setSpeedX(-3 - gameWorld.getBomberman().getSpeedBonus());
                    gameWorld.getBomberman().setDirection(2);

                break;
            case KeyEvent.VK_S:

                    gameWorld.getBomberman().setSpeedY(3 + gameWorld.getBomberman().getSpeedBonus());
                    gameWorld.getBomberman().setDirection(1);

                break;
            case KeyEvent.VK_D:

                    gameWorld.getBomberman().setSpeedX(3 + gameWorld.getBomberman().getSpeedBonus());
                    gameWorld.getBomberman().setDirection(4);


                break;
            case KeyEvent.VK_W:

                    gameWorld.getBomberman().setSpeedY(-3 - gameWorld.getBomberman().getSpeedBonus());
                    gameWorld.getBomberman().setDirection(3);

                break;
            case KeyEvent.VK_SPACE:
                gameWorld.getBombsList().addbomb();
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

                break;
        }
    }
}
