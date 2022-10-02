package gameobject;

import java.awt.*;

public class GameWorld {

    private Bomberman bomberman;


    private PhysicalMap physicalMap;

    public GameWorld() {
        bomberman = new Bomberman(200, 200, 48, 48, this);
        physicalMap = new PhysicalMap(0, 0, this);
    }

    public void Update() {
        bomberman.update();
    }

    public void Render(Graphics2D g2) {
        physicalMap.draw(g2);
        bomberman.draw(g2);
    }

    public Bomberman getBomberman() {
        return bomberman;
    }

    public PhysicalMap getPhysicalMap() {
        return physicalMap;
    }

}
