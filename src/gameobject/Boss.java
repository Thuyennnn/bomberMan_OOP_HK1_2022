package gameobject;

import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;

import java.awt.*;

public class Boss extends Human{

    Animation bossUp, bossDown, bossLeft, bossRight;

    private boolean collisionLeft = true;
    private boolean collisionRight = false;
    private boolean collisionUp = false;
    private boolean collisionDown = false;

    public Boss(double posX, double posY, double width, double height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);

        bossUp = new Animation(CacheDataLoader.getInstance().getAnimation("bbm_up"));
        bossDown = new Animation(CacheDataLoader.getInstance().getAnimation("bbm_down"));
        bossLeft = new Animation(CacheDataLoader.getInstance().getAnimation("bbm_left"));
        bossRight = new Animation(CacheDataLoader.getInstance().getAnimation("bbm_right"));

    }

    public void update() {
        if(haveCollisionWithFlame()) {
            setState(Human.DIE);
            getGameWorld().getBossTeam().remove(this);
        }

        if(getState() == Human.LIVE) {
            action();
            setPosX(getPosX() + getSpeedX());
            setPosY(getPosY() + getSpeedY());
        }
    }

    public void draw(Graphics2D g2) {
        Camera camera = getGameWorld().getCamera();
        if(getState() == LIVE) {
            if(isHumanRun()) {
                if(getDirection() == DIRECTION_DOWN) {

                    bossDown.Update(System.nanoTime());
                    bossDown.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
                }
                else if(getDirection() == DIRRECTION_LEFT) {

                    bossLeft.Update(System.nanoTime());
                    bossLeft.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
                }
                else if(getDirection() == DIRECTION_UP) {

                    bossUp.Update(System.nanoTime());
                    bossUp.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
                }
                else {

                    bossRight.Update(System.nanoTime());
                    bossRight.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);

                }
            }
            else {
                FrameImage frameImage;
                if(getDirection() == DIRECTION_DOWN)
                    frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("bbm_down1"));
                else if(getDirection() == DIRRECTION_LEFT)
                    frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("bbm_left1"));
                else if(getDirection() == DIRECTION_UP)
                    frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("bbm_up1"));
                else  frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("bbm_right1"));

                frameImage.draw(g2, (int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()));


            }
        }

    }


    public void action() {
        int tileSize = getGameWorld().getPhysicalMap().getTileSize();
        char[][] physicalMap = getGameWorld().getPhysicalMap().physicalMap;
        int posX = (int) (getPosX() / tileSize);
        int posY = (int) (getPosY() / tileSize);

        if(collisionLeft == true)
        if((physicalMap[posY][posX-1] == ' ')
            || (physicalMap[posY][posX] == ' ' && getPosX() > (posX * tileSize + tileSize / 2))){
            if(collisionLeft == true) {
                setSpeedX(-2);
                setDirection(Human.DIRRECTION_LEFT);
            }

        }
        else {
            setSpeedX(0);
            collisionLeft = false;
            collisionDown = true;
        }

        if(collisionDown == true)
            if((physicalMap[posY+1][posX] == ' ')
                    || (physicalMap[posY][posX] == ' ' && getPosY() < (posY * tileSize + tileSize / 2))){

                if(collisionDown == true) {

                    setSpeedY(2);
                    setDirection(Human.DIRECTION_DOWN);
                }
                else setSpeedY(0);

            }
            else {
                setSpeedY(0);
                collisionDown = false;
                collisionRight = true;
            }

        if(collisionRight == true)
        if((physicalMap[posY][posX+1] == ' ')
                || (physicalMap[posY][posX] == ' ' && getPosX() < (posX * tileSize + tileSize / 2))){
            if(collisionRight == true) {

                setSpeedX(2);
                setDirection(Human.DIRECTION_RIGHT);
            }
            else setSpeedX(0);

        }
        else {
            setSpeedX(0);
            collisionUp = true;
            collisionRight = false;
        }

        if(collisionUp == true)
            if((physicalMap[posY-1][posX] == ' ')
                    || (physicalMap[posY][posX] == ' ' && getPosY() > (posY * tileSize + tileSize / 2))){
                if(collisionUp == true) {

                    setSpeedY(-2);
                    setDirection(Human.DIRECTION_UP);
                }
                else setSpeedY(0);

            }
            else {
                setSpeedY(0);
                collisionLeft = true;
                collisionUp = false;
            }



    }
}
