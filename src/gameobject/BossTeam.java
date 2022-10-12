package gameobject;

import java.awt.*;
import java.util.ArrayList;

public class BossTeam {
    private ArrayList<Boss> bossTeam;
    GameWorld gameWorld;

    public BossTeam(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        bossTeam = new ArrayList<Boss>();

        prepare();
    }

    public void add(Boss boss) {
        bossTeam.add(boss);
    }

    public void remove(Boss boss) {

        bossTeam.remove(boss);
        System.out.println("xoa boss");
    }

    public void Update() {
        for(int i = 0; i < bossTeam.size(); i++)
            bossTeam.get(i).update();
    }

    public void Draw(Graphics2D g2) {
        for(int i = 0; i < bossTeam.size(); i++)
            bossTeam.get(i).draw(g2);
    }

    public ArrayList<Boss> getBossTeam() {
        return bossTeam;
    }

    public void prepare() {
        char physicalMap[][] = gameWorld.getPhysicalMap().physicalMap;
        for(int i = 0; i < physicalMap[0].length; i++)
            for(int j = 0; j < physicalMap.length; j++) {
                if(physicalMap[j][i] == '1') {

                    int tileSize = gameWorld.getPhysicalMap().getTileSize();
                    Boss boss = new Boss(i * tileSize + tileSize / 2, j * tileSize + tileSize / 2, 40, 50, gameWorld);
                    bossTeam.add(boss);
                    System.out.println("them boss");
                    physicalMap[j][i] = ' ';
                }
            }
    }
}
