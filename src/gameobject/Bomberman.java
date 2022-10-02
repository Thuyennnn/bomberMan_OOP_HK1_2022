package gameobject;

import java.awt.*;

public class Bomberman {
    private double posX;
    private double posY;
    private double width;
    private double height;
    private double speedX;
    private double speedY;


    private int direction;
    public static int DIRRECTION_LEFT;
    public static int DIRECTION_RIGHT;

    GameWorld gameWorld;
    public Bomberman (double posX, double posY, double width, double height, GameWorld gameWorld) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.gameWorld = gameWorld;
    }

    public Rectangle getBound() {
        Rectangle rectangle = new Rectangle();
        rectangle.x = (int) (posX - width / 2);
        rectangle.y = (int) (posY - height / 2);
        rectangle.width = (int) width;
        rectangle.height = (int) height;
        return rectangle;
    }
    public void update() {
        setPosY(getPosY() + speedY);

        Rectangle currentRect = getBound();
        currentRect.x += speedX;
        Rectangle rightRect = gameWorld.getPhysicalMap().haveCollisionWithRightRect(currentRect);

        if(rightRect != null) setPosX(rightRect.x - width / 2);
        else  {
            setPosX(getPosX() + speedX);
        }
    }
    public void draw(Graphics2D g2) {

        g2.setColor(Color.BLACK);
        g2.fillRect((int) (posX - width / 2), (int) (posY - height / 2), 48, (int) 48);
        g2.setColor(Color.red);
        g2.fillRect((int) posX, (int) posY, (int) 2, (int) 2);
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

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
