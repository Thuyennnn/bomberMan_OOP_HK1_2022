package gameobject;

import java.awt.*;

public abstract class Weapon extends GameObject {

    private double width;
    private double height;

    private boolean isSeted;

    private long timeDelay;
    private long timeStart;

    public Weapon (double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        fixPos();
        this.isSeted = true;
        this.timeStart = System.nanoTime();
    }

    public abstract boolean update();

    public void fixPos() {
        int tilteSize = getGameWorld().getPhysicalMap().getTileSize();

        int x = (int) getPosX() / tilteSize;
        setPosX(x * tilteSize + tilteSize / 2);

        int y = (int) getPosY() / tilteSize;
        setPosY(y * tilteSize + tilteSize / 2);
    }

    public abstract void draw(Graphics2D g2);

//    public Rectangle getBound() {
//        Rectangle currentRect = new Rectangle();
//        currentRect.x =
//    }



    public void  setTimeDelay(long timeDelay) {
        this.timeDelay = timeDelay;
    }

    public long getTimeDelay() {
        return timeDelay;
    }

    public boolean IsSeted() {
        return isSeted;
    }

    public void unSeted() {
        this.isSeted = false;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

}
