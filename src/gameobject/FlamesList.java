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

    public void add(Bomb bomb) {

        int tilteSize = gameWorld.getPhysicalMap().getTileSize();
        PhysicalMap physicalMap = gameWorld.getPhysicalMap();
        for(int i = 1; i <= MAX_FlAME; i++) {

            int x = (int) bomb.getPosY() / tilteSize;
            int y = (int) (bomb.getPosX() + i * tilteSize) / tilteSize;
            if(physicalMap.physicalMap[x][y] != '#'){
                Flame flame = new Flame(bomb.getPosX() + i * tilteSize, bomb.getPosY(), gameWorld);
                flamesList.add(flame);
                if(physicalMap.physicalMap[x][y] != ' ') {
                    if(physicalMap.physicalMap[x][y] == '*')
                        physicalMap.physicalMap[x][y] = ' ';
                    if(physicalMap.physicalMap[x][y] == 'b' || physicalMap.physicalMap[x][y] == 'f'
                        || physicalMap.physicalMap[x][y] == 's') {
                        Item item = new Item(physicalMap.physicalMap[x][y], bomb.getPosX() + i * tilteSize, bomb.getPosY(), gameWorld);
                        gameWorld.getItemsList().add(item);
                        physicalMap.physicalMap[x][y] = 'n';
                    }

                    break;
                }
            } else break;

        }
        for(int i = 1; i <= MAX_FlAME; i++) {

            int x = (int) bomb.getPosY() / tilteSize;
            int y = (int) (bomb.getPosX() - i * tilteSize) / tilteSize;
            if(physicalMap.physicalMap[x][y] != '#'){
                Flame flame = new Flame(bomb.getPosX() - i * tilteSize, bomb.getPosY(), gameWorld);
                flamesList.add(flame);
                if(physicalMap.physicalMap[x][y] != ' ') {
                    if(physicalMap.physicalMap[x][y] == '*')
                        physicalMap.physicalMap[x][y] = ' ';
                    if(physicalMap.physicalMap[x][y] == 'b' || physicalMap.physicalMap[x][y] == 'f'
                            || physicalMap.physicalMap[x][y] == 's') {
                        Item item = new Item(physicalMap.physicalMap[x][y], bomb.getPosX() + i * tilteSize, bomb.getPosY(), gameWorld);
                        gameWorld.getItemsList().add(item);
                        physicalMap.physicalMap[x][y] = 'n';
                    }

                    break;
                }
            } else break;
        }

        for(int i = 1; i <= MAX_FlAME; i++) {
            int x = (int) (bomb.getPosY() + i * tilteSize) / tilteSize;
            int y = (int) bomb.getPosX() / tilteSize;
            if(physicalMap.physicalMap[x][y] != '#'){
                Flame flame = new Flame(bomb.getPosX(), bomb.getPosY() + i * tilteSize, gameWorld);
                flamesList.add(flame);
                if(physicalMap.physicalMap[x][y] != ' ') {
                    if(physicalMap.physicalMap[x][y] == '*')
                        physicalMap.physicalMap[x][y] = ' ';
                    if(physicalMap.physicalMap[x][y] == 'b' || physicalMap.physicalMap[x][y] == 'f'
                            || physicalMap.physicalMap[x][y] == 's') {
                        Item item = new Item(physicalMap.physicalMap[x][y], bomb.getPosX() + i * tilteSize, bomb.getPosY(), gameWorld);
                        gameWorld.getItemsList().add(item);
                        physicalMap.physicalMap[x][y] = 'n';
                    }

                    break;
                }
            } else break;
        }

        for(int i = 1; i <= MAX_FlAME; i++) {
            int x = (int) (bomb.getPosY() - i * tilteSize) / tilteSize;
            int y = (int) bomb.getPosX() / tilteSize;
            if(physicalMap.physicalMap[x][y] != '#'){
                Flame flame = new Flame(bomb.getPosX(), bomb.getPosY() - i * tilteSize, gameWorld);
                flamesList.add(flame);
                if(physicalMap.physicalMap[x][y] != ' ') {
                    if(physicalMap.physicalMap[x][y] == '*')
                        physicalMap.physicalMap[x][y] = ' ';
                    if(physicalMap.physicalMap[x][y] == 'b' || physicalMap.physicalMap[x][y] == 'f'
                            || physicalMap.physicalMap[x][y] == 's') {
                        Item item = new Item(physicalMap.physicalMap[x][y], bomb.getPosX() + i * tilteSize, bomb.getPosY(), gameWorld);
                        gameWorld.getItemsList().add(item);
                        physicalMap.physicalMap[x][y] = 'n';
                    }

                    break;
                }
            } else break;
        }

        Flame flame = new Flame(bomb.getPosX(), bomb.getPosY(), gameWorld);
        flamesList.add(flame);
    }

    public void removeFlame(Flame flame) {
        flamesList.remove(flame);
    }

    public void Update() {
        for(int i = 0; i < flamesList.size(); i++)
            if(flamesList.get(i).update()) i--;
    }

    public void Draw(Graphics2D g2) {
        for(Flame f : flamesList)
            f.draw(g2);
    }
}
