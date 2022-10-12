package gameobject;

import java.awt.*;
import java.util.ArrayList;

public class FlamesList {

    public static int MAX_FlAME = 1; // Declare length of flame allowed to appear is 1 when just begining.
    private ArrayList<Flame> flamesList;
    GameWorld gameWorld;

    /**
     * Constructor.
     */
    public FlamesList(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        flamesList = new ArrayList<Flame>();
    }

    /**
     * Handle flame.
     * Check if not wall, can add flame else if it is wall return false
     * if it is motivable return true,
     * else if it is wood or item, add flame and then it is motivable, return false.
     */
    public boolean checkPhysgetFlame(PhysicalMap physicalMap, int x, int y, Bomb bomb, int xi, int yi) {
        int tilteSize = gameWorld.getPhysicalMap().getTileSize();
        physicalMap = gameWorld.getPhysicalMap();


        // if not wall
        if (physicalMap.physicalMap[x][y] != '#') {
            Flame flame = new Flame(bomb.getPosX() + xi * tilteSize, bomb.getPosY() + yi * tilteSize, gameWorld);
            flamesList.add(flame);

            // if it isn't free(motivable)
            if (physicalMap.physicalMap[x][y] != ' ') {
                if (physicalMap.physicalMap[x][y] == '*')// wood
                    physicalMap.physicalMap[x][y] = ' '; // wood -> motivable

                // if it is item
                if (physicalMap.physicalMap[x][y] == 'b' || physicalMap.physicalMap[x][y] == 'f'
                        || physicalMap.physicalMap[x][y] == 's') {
                    Item item = new Item(physicalMap.physicalMap[x][y], bomb.getPosX() + xi * tilteSize, bomb.getPosY() + yi * tilteSize, gameWorld);
                    gameWorld.getItemsList().add(item);
                    physicalMap.physicalMap[x][y] = ' ';// after using item-> motivable
                }
                return false;

            } else return true;

        } else return false;
    }

    /**
     * Add flame for bomb.
     */
    public void add(Bomb bomb) {

        int tilteSize = gameWorld.getPhysicalMap().getTileSize();
        PhysicalMap physicalMap = gameWorld.getPhysicalMap();
        for (int i = 1; i <= MAX_FlAME; i++) {

            int x = (int) bomb.getPosY() / tilteSize;
            int y = (int) (bomb.getPosX() + i * tilteSize) / tilteSize;
            if (checkPhysgetFlame(physicalMap, x, y, bomb, i, 0) == false) break;

        }
        for (int i = -1; i >= (MAX_FlAME * -1); i--) {

            int x = (int) bomb.getPosY() / tilteSize;
            int y = (int) (bomb.getPosX() + i * tilteSize) / tilteSize;
            if (checkPhysgetFlame(physicalMap, x, y, bomb, i, 0) == false) break;
        }

        for (int i = 1; i <= MAX_FlAME; i++) {
            int x = (int) (bomb.getPosY() + i * tilteSize) / tilteSize;
            int y = (int) bomb.getPosX() / tilteSize;
            if (checkPhysgetFlame(physicalMap, x, y, bomb, 0, i) == false) break;
        }

        for (int i = -1; i >= (MAX_FlAME * -1); i--) {
            int x = (int) (bomb.getPosY() + i * tilteSize) / tilteSize;
            int y = (int) bomb.getPosX() / tilteSize;
            if (checkPhysgetFlame(physicalMap, x, y, bomb, 0, i) == false) break;
        }

        Flame flame = new Flame(bomb.getPosX(), bomb.getPosY(), gameWorld);
        flamesList.add(flame);

    }

    /**
     * Remove flame.
     */
    public void removeFlame(Flame flame) {
        flamesList.remove(flame);
    }

    /**
     * Update method for Flamelist.
     */
    public void Update() {
        for (int i = 0; i < flamesList.size(); i++)
            if (flamesList.get(i).update()) i--;
    }

    /**
     * Draw method for Flamelist.
     */
    public void Draw(Graphics2D g2) {
        for (Flame f : flamesList) {
            f.draw(g2);
        }

    }

    public ArrayList<Flame> getFlamesList() {
        return flamesList;
    }
}