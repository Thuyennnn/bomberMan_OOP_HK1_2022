package gameobject;

import java.awt.*;
import java.util.ArrayList;

public class ItemsList {
    private ArrayList<Item> itemsList;
    private GameWorld gameWorld;

    public ItemsList(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        itemsList = new ArrayList<Item>();
    }

    public void Update() {
        for(int i = 0; i < itemsList.size(); i++)
            itemsList.get(i).Update();
    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < itemsList.size(); i++)
            itemsList.get(i).draw(g2);
    }

    public void add(Item item) {
        itemsList.add(item);
    }

    public void remove(Item item) {
        itemsList.remove(item);
    }
}
