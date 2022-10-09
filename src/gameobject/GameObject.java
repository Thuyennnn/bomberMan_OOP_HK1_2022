package gameobject;

import java.awt.*;

public abstract class GameObject {
    private double PosX;
    private double PosY;
    private double width;
    private double height;

    private GameWorld gameWorld;

    public GameObject(double posX, double posY, GameWorld gameWorld) {
        PosX = posX;
        PosY = posY;
        this.gameWorld = gameWorld;
    }

    public abstract void draw(Graphics2D g2);

    public Rectangle getBound() {
        Rectangle rectangle = new Rectangle();

        rectangle.x = (int) (PosX - width / 2);
        rectangle.y = (int) (PosY - height / 2);
        rectangle.width = (int) width;
        rectangle.height = (int) height;

        return rectangle;
    }
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

    public GameWorld getGameWorld() {
        return gameWorld;
    }
}
