package gameobject;

import effect.CacheDataLoader;

import java.awt.*;

public class PhysicalMap extends GameObject{
    public char[][] physicalMap;
    private final int tileSize = 60;


    public double PosX, PosY;

    public PhysicalMap(double PosX, double PosY, GameWorld gameWorld) {
        super(PosX, PosY, gameWorld);
        physicalMap = CacheDataLoader.getInstance().getPhysicalMap();
    }

    public int getTileSize() {
        return tileSize;
    }

    public void draw(Graphics2D g2) {

        for (int i = 0; i < physicalMap.length; i++)
            for (int j = 0; j < physicalMap[0].length; j++)
                if (physicalMap[i][j] != ' ') {
                    if (physicalMap[i][j] == '#') g2.setColor(Color.gray);
                    else if (physicalMap[i][j] == '*') g2.setColor(Color.yellow);
                        g2.fillRect((int) PosX + j * getTileSize(), (int) PosY + i * tileSize, tileSize, tileSize);
                }

    }

    public Rectangle haveCollisionWithRightRect(Rectangle rect) {
        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;

        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 + 3;
        if(posX2 >= physicalMap[0].length) posX2 = physicalMap[0].length - 1;

        if(posY1 < 0) posY1 = 0;
        if(posY2 >= physicalMap.length) posY2 = physicalMap.length - 1;


        for(int x = posX1; x <= posX2; x++){
            for(int y = posY1; y <= posY2;y++){
                if(physicalMap[y][x] == '#'){
                    Rectangle r = new Rectangle((int) (getPosX() + x * tileSize), (int) (getPosY() + y * tileSize), tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithLand(Rectangle rect){

        int posX1 = rect.x / tileSize - 2;
        int posX2 = (rect.x + rect.width) + 2;

        if(posX1 < 0) posX1 = 0;
        if(posX2 >= physicalMap[0].length) posX2 = physicalMap[0].length - 1;

        int posY1 = (rect.y + rect.height) / tileSize;

        for(int y = posY1; y < physicalMap.length; y++)
            for(int x = posX1; x <= posX2; x++) {
                if(physicalMap[y][x] == '#') {
                    Rectangle rectTile = new Rectangle((int) (getPosX() + x * tileSize), (int) (getPosY() + y * tileSize), tileSize, tileSize);
                    if(rect.intersects(rectTile)) return rectTile;
                }
            }
        return null;
    }


    @Override
    public void Update() {}
}
