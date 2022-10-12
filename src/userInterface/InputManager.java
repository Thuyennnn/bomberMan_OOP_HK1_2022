package userInterface;

import gameobject.GameWorld;
import gameobject.Human;
import gameobject.Menu;
import gameobject.State;

import java.awt.event.KeyEvent;

public class InputManager {

    State state;

    public InputManager(State state) {

        this.state = state;

    }

    public void processKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                    if(state.IsPlayGame() == true) {
                        state.getGameWorld().getBomberman().setSpeedX(-3 - state.getGameWorld().getBomberman().getSpeedBonus());
                        state.getGameWorld().getBomberman().setDirection(2);
                    }

                break;
            case KeyEvent.VK_S:
                if(state.IsPlayGame() == true) {
                    state.getGameWorld().getBomberman().setSpeedY(3 + state.getGameWorld().getBomberman().getSpeedBonus());
                    state.getGameWorld().getBomberman().setDirection(1);
                }


                break;
            case KeyEvent.VK_D:
                if(state.IsPlayGame() == true) {
                    state.getGameWorld().getBomberman().setSpeedX(3 + state.getGameWorld().getBomberman().getSpeedBonus());
                    state.getGameWorld().getBomberman().setDirection(4);
                }

                break;
            case KeyEvent.VK_W:
                if(state.IsPlayGame() == true) {
                    state.getGameWorld().getBomberman().setSpeedY(-3 - state.getGameWorld().getBomberman().getSpeedBonus());
                    state.getGameWorld().getBomberman().setDirection(3);
                }


                break;
            case KeyEvent.VK_SPACE:
                if(state.IsPlayGame() == true) {
                    state.getGameWorld().getBombsList().addbomb();
                }

                break;

            case KeyEvent.VK_DOWN:

                state.getMenu().setButtonSelected(state.getMenu().getButtonSelected()+1);
                if(state.getMenu().getButtonSelected() >= state.getMenu().NUMBER_OF_BUTTON) {
                    state.getMenu().setButtonSelected(0);
                }
                break;
            case KeyEvent.VK_UP:
                if(state.IsPlayGame() == false) {
                    state.getMenu().setButtonSelected(state.getMenu().getButtonSelected()-1);
                    if(state.getMenu().getButtonSelected() < 0) {
                        state.getMenu().setButtonSelected(state.getMenu().NUMBER_OF_BUTTON - 1);
                    }
                }

                break;
            case KeyEvent.VK_ENTER:
                if(state.IsPlayGame() == false)
                    if(state.getMenu().getButtonSelected() == 1) System.exit(0);
                    else {

                        state.setGameWorld(new GameWorld());
                        state.setPlayGame();

                        System.out.println("set new game world");
                        System.out.println(state.IsPlayGame());
                    }
                break;
        }
    }

    public void processKeyRelease(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                if(state.IsPlayGame() == true)
                    state.getGameWorld().getBomberman().setSpeedX(0);
                break;
            case KeyEvent.VK_S:
                if(state.IsPlayGame() == true)
                    state.getGameWorld().getBomberman().setSpeedY(0);
                break;
            case KeyEvent.VK_D:
                if(state.IsPlayGame() == true)
                    state.getGameWorld().getBomberman().setSpeedX(0);
                break;
            case KeyEvent.VK_W:
                if(state.IsPlayGame() == true)
                    state.getGameWorld().getBomberman().setSpeedY(0);
                break;
            case KeyEvent.VK_SPACE:

                break;
        }
    }
}
