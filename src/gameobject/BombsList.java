package gameobject;

import java.awt.*;
import java.util.ArrayList;

public class BombsList {

    public static int MAX_BOMB = 1;
    public static int BOMB_SETED = 0;

    private ArrayList<Bomb> bombsList;

    private GameWorld gameWorld;
    BombsList(GameWorld gameWorld) {
        bombsList = new ArrayList<Bomb>();
        this.gameWorld = gameWorld;
    }

    public void addbomb() {
        if(BOMB_SETED < MAX_BOMB) {
            double posX = gameWorld.getBomberman().getPosX();
            double posY = gameWorld.getBomberman().getPosY();
            Bomb bomb = new Bomb(posX, posY, gameWorld);
            bombsList.add(bomb);
            System.out.println("added");
            BOMB_SETED ++;
        }
    }

    public void removeBomb(Bomb bomb) {
        bombsList.remove(bomb);
        BOMB_SETED--;
    }
    public void Update() {
        for(int i = 0; i < bombsList.size(); i++)
            if(bombsList.get(i).update() == true) i--;
    }

    public void Draw(Graphics2D g2) {
        if(bombsList.isEmpty() == false)
        for(Bomb b : bombsList)
            b.draw(g2);
    }

}
