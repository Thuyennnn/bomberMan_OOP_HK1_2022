package gameobject;

import java.awt.*;

public class State {
    private GameWorld gameWorld;
    private Menu menu;

    private boolean isPlayGame;

    public State() {
        gameWorld = new GameWorld();
        menu = new Menu();
        this.isPlayGame = false;
    }

    public void update() {

        if(gameWorld.getBomberman().getState() == Human.DIE || gameWorld.getBossTeam().getBossTeam().isEmpty() == true)
        {
            this.isPlayGame = false;
        }
        if(isPlayGame == true)
            gameWorld.Update();
        else menu.Update();

//        System.out.println(isPlayGame);
    }

    public void render(Graphics2D g2) {
        if(isPlayGame == true)
            gameWorld.Render(g2);
        else {

            menu.Render(g2);
        }
    }

    public boolean IsPlayGame() {
        return isPlayGame;
    }

    public void setPlayGame() {
        this.isPlayGame = true;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}