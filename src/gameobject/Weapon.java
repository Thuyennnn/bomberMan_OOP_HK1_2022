package gameobject;

import java.awt.*;

public abstract class Weapon {

    private double PosX;
    private double PosY;

    private double width;
    private double height;

    private boolean isSeted;

    GameWorld gameWorld;

    private long timeDelay;
    private long timeStart;

    public Weapon (double posX, double posY, GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.PosX = posX;
        this.PosY = posY;
        fixPos();
        this.isSeted = true;
        this.timeStart = System.nanoTime();
    }

    public abstract boolean update();

    public void fixPos() {
        int tilteSize = gameWorld.getPhysicalMap().getTileSize();

        int x = (int) PosX / tilteSize;
        PosX = x * tilteSize + tilteSize / 2;

        int y = (int) PosY / tilteSize;
        PosY = y * tilteSize + tilteSize / 2;
    }

    public abstract void draw(Graphics2D g2);

//    public Rectangle getBound() {
//        Rectangle currentRect = new Rectangle();
//        currentRect.x =
//    }

    public double getPosX() {
        return PosX;
    }

    public void setPosX(double posX) {
        PosX = posX;
    }

    public double getPosY() {
        return PosY;
    }

    public void setPosY(double posY) {
        PosY = posY;
    }

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

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
