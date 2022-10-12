package gameobject;

import java.awt.*;
import java.util.ArrayList;

public class BombsList {

    public static int MAX_BOMB = 1;// Declare number of bombs allowed to appear is 1 when just begining.
    public static int BOMB_SETED = 0;// number of bombs setted

    private ArrayList<Bomb> bombsList;

    private GameWorld gameWorld;

    /**
     * Constructor.
     */
    BombsList(GameWorld gameWorld) {
        bombsList = new ArrayList<Bomb>();
        this.gameWorld = gameWorld;
    }

    /**
     * Add a bomb if BOMB_SETED < MAx_BOMB.
     */
    public void addbomb() {
        if (BOMB_SETED < MAX_BOMB) {
            double posX = gameWorld.getBomberman().getPosX();
            double posY = gameWorld.getBomberman().getPosY();
            Bomb bomb = new Bomb(posX, posY, gameWorld);
            bombsList.add(bomb);
            BOMB_SETED++;
        }
    }

    /**
     * Remove bomb.
     */
    public void removeBomb(Bomb bomb) {
        bombsList.remove(bomb);
        BOMB_SETED--;
    }

    /**
     * Update method for BombsList.
     */
    public void Update() {
        for (int i = 0; i < bombsList.size(); i++)
            if (bombsList.get(i).update()) i--;
    }

    /**
     * Draw method for BombsList.
     * When bombsList is not empty.
     */
    public void Draw(Graphics2D g2) {
        if (!bombsList.isEmpty()) {
            for (Bomb b : bombsList)
                b.draw(g2);
        }
    }
}