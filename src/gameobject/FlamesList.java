package gameobject;

import java.awt.*;
import java.util.ArrayList;

public class FlamesList {

    public static int MAX_FlAME = 1;
    private ArrayList<Flame> flamesList;
    GameWorld gameWorld;

    public FlamesList(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        flamesList = new ArrayList<Flame>();
    }

    public boolean checkPhysgetFlame(PhysicalMap physicalMap, int x, int y, Bomb bomb, int xi, int yi) {
        int tilteSize = gameWorld.getPhysicalMap().getTileSize();
        physicalMap = gameWorld.getPhysicalMap();

//        System.out.println("bomb" + bomb.getPosX() + " " + bomb.getPosY());

        if (physicalMap.physicalMap[x][y] != '#') {
            Flame flame = new Flame(bomb.getPosX() + xi * tilteSize, bomb.getPosY() + yi * tilteSize, gameWorld);
            flamesList.add(flame);
//            System.out.println((bomb.getPosX() + xi * tilteSize) + " " + (bomb.getPosY() + yi * tilteSize)) ;
            if (physicalMap.physicalMap[x][y] != ' ') {
                if (physicalMap.physicalMap[x][y] == '*')
                    physicalMap.physicalMap[x][y] = ' ';
                if (physicalMap.physicalMap[x][y] == 'b' || physicalMap.physicalMap[x][y] == 'f'
                        || physicalMap.physicalMap[x][y] == 's') {
                    Item item = new Item(physicalMap.physicalMap[x][y], bomb.getPosX() + xi * tilteSize, bomb.getPosY() + yi * tilteSize, gameWorld);
                    gameWorld.getItemsList().add(item);
                    physicalMap.physicalMap[x][y] = ' ';
                }
                return false;

            } else return true;

        } else return false;
    }
    public void add(Bomb bomb) {

        int tilteSize = gameWorld.getPhysicalMap().getTileSize();
        PhysicalMap physicalMap = gameWorld.getPhysicalMap();
        for (int i = 1; i <= MAX_FlAME; i++) {

            int x = (int) bomb.getPosY() / tilteSize;
            int y = (int) (bomb.getPosX() + i * tilteSize) / tilteSize;
            if(checkPhysgetFlame(physicalMap, x, y, bomb, i, 0) == false) break;

        }
        for (int i = -1; i >= (MAX_FlAME * -1); i--) {

            int x = (int) bomb.getPosY() / tilteSize;
            int y = (int) (bomb.getPosX() + i * tilteSize) / tilteSize;
            if(checkPhysgetFlame(physicalMap, x, y, bomb, i, 0) == false) break;
        }

        for (int i = 1; i <= MAX_FlAME; i++) {
            int x = (int) (bomb.getPosY() + i * tilteSize) / tilteSize;
            int y = (int) bomb.getPosX() / tilteSize;
            if(checkPhysgetFlame(physicalMap, x, y, bomb, 0, i) == false) break;
        }

        for (int i = -1; i >= (MAX_FlAME * -1); i--) {
            int x = (int) (bomb.getPosY() + i * tilteSize) / tilteSize;
            int y = (int) bomb.getPosX() / tilteSize;
            if(checkPhysgetFlame(physicalMap, x, y, bomb, 0, i) == false) break;
        }

        Flame flame = new Flame(bomb.getPosX(), bomb.getPosY(), gameWorld);
        flamesList.add(flame);
//        System.out.println((bomb.getPosX()) + " " + (bomb.getPosY()));
    }

    public void removeFlame(Flame flame) {
        flamesList.remove(flame);
    }

    public void Update() {
        for (int i = 0; i < flamesList.size(); i++)
            if (flamesList.get(i).update()) i--;
    }

    public void Draw(Graphics2D g2) {
        for (Flame f : flamesList) {
            f.draw(g2);
        }

    }

    public ArrayList<Flame> getFlamesList() {
        return flamesList;
    }
}
