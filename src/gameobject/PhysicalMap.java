package gameobject;

import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;
import userInterface.GameFrame;

import java.awt.*;

public class PhysicalMap{
    public char[][] physicalMap;
    private final int tileSize = 60;


    private double PosX, PosY;

    private GameWorld gameWorld;
    FrameImage wall, wood, grass;

    public PhysicalMap(double PosX, double PosY, GameWorld gameWorld) {
        this.PosX = PosX;
        this.PosY = PosY;
        physicalMap = new char[13][31];
        getMap();
        wall = new FrameImage(CacheDataLoader.getInstance().getFrameImage("tile_wall"));
        wood = new FrameImage(CacheDataLoader.getInstance().getFrameImage("tile_wood"));
        grass = new FrameImage(CacheDataLoader.getInstance().getFrameImage("tile_grass"));

        this.gameWorld = gameWorld;
    }

    public void getMap() {
        char[][]physMap = CacheDataLoader.getInstance().getPhysicalMap();

        for(int i = 0; i < physMap.length; i++)
            for(int j = 0; j < physMap[0].length; j++) {
                physicalMap[i][j] = physMap[i][j];
            }
    }
    public int getTileSize() {
        return tileSize;
    }

    public void draw(Graphics2D g2) {

        Camera camera = gameWorld.getCamera();

        for (int i = 0; i < physicalMap.length; i++)
            for (int j = 0; j < physicalMap[0].length; j++)
            if(j*tileSize - camera.getPosX() > -60 && j*tileSize - camera.getPosX() < GameFrame.SCREEN_WIDTH
            && i*tileSize - camera.getPosY() > -60 && i*tileSize - camera.getPosY() < GameFrame.SCREEN_HEIGHT)
                {

                    if (physicalMap[i][j] == '#')
                        wall.draw(g2, (int) (j * tileSize + tileSize / 2 - camera.getPosX()),
                                (int) (i * tileSize + tileSize / 2 - camera.getPosY()));
                    else if (physicalMap[i][j] == '*' || physicalMap[i][j] == 'b'
                            || physicalMap[i][j] == 'f' || physicalMap[i][j] == 's')

                        wood.draw(g2, (int) (j * tileSize + tileSize / 2 - camera.getPosX()),
                                (int) (i * tileSize + tileSize / 2 - camera.getPosY()));
                    else
                        grass.draw(g2, (int) (j * tileSize + tileSize / 2 - camera.getPosX()),
                                (int) (i * tileSize + tileSize / 2 - camera.getPosY()));

                }

    }

    public Rectangle haveCollisionWithRightRect(Rectangle rect) {

        int PosX1 = (rect.x + rect.width) / tileSize;
        int PosY1 = rect.y / tileSize;
        int PosY2 = PosY1 + 1;
        if (PosY2 >= physicalMap.length) PosY2 = physicalMap.length - 1;


        for (int y = PosY1; y <= PosY2; y++) {
            if (physicalMap[y][PosX1] == '#' || physicalMap[y][PosX1] == '*' || physicalMap[y][PosX1] == 'b'
                || physicalMap[y][PosX1] == 'f' || physicalMap[y][PosX1] == 's') {
                Rectangle r = new Rectangle(PosX1 * tileSize, y * tileSize, tileSize, tileSize);
                if (r.intersects(rect) && (r.x + 10 > rect.x + rect.width)) {
                    return r;
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithLeftRect(Rectangle rect) {

        int PosX1 = rect.x / tileSize;
        int PosY1 = rect.y / tileSize;

        for (int y = PosY1; y <= PosY1 + 1; y++) {
            if (physicalMap[y][PosX1] == '#' || physicalMap[y][PosX1] == '*' || physicalMap[y][PosX1] == 'b'
                || physicalMap[y][PosX1] == 'f' || physicalMap[y][PosX1] == 's') {
                Rectangle r = new Rectangle(PosX1 * tileSize, y * tileSize, tileSize, tileSize);
                if (r.intersects(rect) && (r.x + r.width - 10 < rect.x)) {
                    return r;
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithAboveRect(Rectangle rect) {

        int PosX1 = rect.x / tileSize;
        int PosY1 = rect.y / tileSize;

        for (int x = PosX1; x <= PosX1 + 1; x++) {
            if (physicalMap[PosY1][x] == '#' || physicalMap[PosY1][x] == '*' || physicalMap[PosY1][x] == 'b'
                || physicalMap[PosY1][x] == 'f' || physicalMap[PosY1][x] == 's') {
                Rectangle r = new Rectangle(x * tileSize, PosY1 * tileSize, tileSize, tileSize);
                if (r.intersects(rect) && (r.y + r.height - 10 < rect.y)) {
                    return r;
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithBelowRect(Rectangle rect) {

        int PosX1 = rect.x / tileSize;
        int PosY1 = (rect.y + rect.height) / tileSize;

        for (int x = PosX1; x <= PosX1 + 1; x++) {
            if (physicalMap[PosY1][x] == '#' || physicalMap[PosY1][x] == '*' || physicalMap[PosY1][x] == 'b'
                || physicalMap[PosY1][x] == 'f' || physicalMap[PosY1][x] == 's') {
                Rectangle r = new Rectangle(x * tileSize, PosY1 * tileSize, tileSize, tileSize);
                if (r.intersects(rect) && (r.y + 10 > rect.y + rect.height)) {
                    return r;
                }
            }
        }
        return null;
    }

}
