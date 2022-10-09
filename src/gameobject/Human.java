package gameobject;

import effect.Animation;
import effect.CacheDataLoader;

import java.awt.*;

public abstract class Human extends GameObject{

    private double speedX;
    private double speedY;

    private int direction;

    public static final int DIRECTION_DOWN = 1;
    public static final int DIRRECTION_LEFT = 2;
    public static final int DIRECTION_UP = 3;
    public static final int DIRECTION_RIGHT = 4;

    private int state;

    public static final int LIVE = 1;
    public static final int DIE = 2;
    private boolean HumanRun;
    GameWorld gameWorld;

    public Human(double posX, double posY, double width, double height, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setWidth(width);
        setHeight(height);

        this.state = 1;
        this.HumanRun = false;
        this.direction = DIRECTION_DOWN;

    }

    public abstract void update();

    public abstract void draw(Graphics2D g2);


    public boolean haveCollisionWithFlame() {

        Rectangle currentRect = getBound();
        FlamesList flamesList = getGameWorld().getFlamesList();
        for(Flame f : flamesList.getFlamesList()) {
            Rectangle Rectf = f.getBound();

            if(currentRect.intersects(Rectf)) {
                return true;
            }
        }
        return false;
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


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public boolean isHumanRun() {
        return HumanRun;
    }

    public void setHumanRun(boolean humanRun) {
        HumanRun = humanRun;
    }
}
