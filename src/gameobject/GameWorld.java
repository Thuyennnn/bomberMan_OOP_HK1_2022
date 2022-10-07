package gameobject;

import userInterface.GameFrame;

import java.awt.*;
import java.util.ArrayList;

public class GameWorld {

    private Bomberman bomberman;


    private PhysicalMap physicalMap;


    private BombsList bombsList;

    private FlamesList flamesList;
    private ItemsList itemsList;
    private Camera camera;

    public GameWorld() {
        physicalMap = new PhysicalMap(0, 0, this);
        bomberman = new Bomberman(84, 84, 48, 48, this);
        bombsList = new BombsList(this);
        flamesList = new FlamesList(this);
        itemsList = new ItemsList(this);
        camera = new Camera(0,0, this);
    }

    public void Update() {

        bomberman.update();
        bombsList.Update();
        flamesList.Update();
        camera.Update();
        itemsList.Update();
    }

    public void Render(Graphics2D g2) {
        physicalMap.draw(g2);
        bomberman.draw(g2);
        bombsList.Draw(g2);
        flamesList.Draw(g2);
        itemsList.draw(g2);
    }

    public Bomberman getBomberman() {
        return bomberman;
    }

    public PhysicalMap getPhysicalMap() {
        return physicalMap;
    }

    public BombsList getBombsList() {return bombsList;}

    public FlamesList getFlamesList() {return flamesList;}

    public ItemsList getItemsList() {return  itemsList;}

    public Camera getCamera() {return camera;}

}
