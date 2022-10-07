package gameobject;

import userInterface.GameFrame;

public class Camera {

    private double posX;
    private double posY;

    private double width;
    private double height;

    private GameWorld gameWorld;

    private boolean isLocked;

    public Camera(double posX, double posY, GameWorld gameWorld) {
        this.posX = posX;
        this.posY = posY;
        width = GameFrame.SCREEN_WIDTH;
        height = GameFrame.SCREEN_HEIGHT;
        this.gameWorld = gameWorld;
    }

    public void Update() {
        Bomberman bomberman = gameWorld.getBomberman();
        char [][] physicalMap = gameWorld.getPhysicalMap().physicalMap;
        int tileSize = gameWorld.getPhysicalMap().getTileSize();

        if(bomberman.getPosX() <= GameFrame.SCREEN_WIDTH / 2) posX = 0;
        else if(bomberman.getPosX() >= (physicalMap[0].length ) * tileSize - GameFrame.SCREEN_WIDTH / 2)
            posX = (physicalMap[0].length ) * tileSize - GameFrame.SCREEN_WIDTH;
        else posX = bomberman.getPosX() - GameFrame.SCREEN_WIDTH / 2;

        if(bomberman.getPosY() <= GameFrame.SCREEN_HEIGHT / 2) posY = 0;
        else if(bomberman.getPosY() >= (physicalMap.length ) * tileSize - GameFrame.SCREEN_HEIGHT / 2)
            posY = (physicalMap.length ) * tileSize - GameFrame.SCREEN_HEIGHT;
        else posY = bomberman.getPosY() - GameFrame.SCREEN_HEIGHT / 2;

    }
    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
    }
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
