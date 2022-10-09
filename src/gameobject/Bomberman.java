package gameobject;

import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;

import java.awt.*;

public class Bomberman extends Human{

    private double speedBonus;


    Animation bbmLeft, bbmRight, bbmUp, bbmDown, bbmDie;

    public Bomberman(double posX, double posY, double width, double height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);
        this.gameWorld = gameWorld;

        this.speedBonus = 0;

        bbmDown = new Animation(CacheDataLoader.getInstance().getAnimation("betty_down"));
        bbmLeft = new Animation(CacheDataLoader.getInstance().getAnimation("betty_left"));
        bbmUp = new Animation(CacheDataLoader.getInstance().getAnimation("betty_up"));
        bbmRight = new Animation(CacheDataLoader.getInstance().getAnimation("betty_right"));
    }



    public void update() {

            if(haveCollisionWithFlame())  setState(Human.DIE);
            if(haveCollisionWithBoss()) setState(Human.DIE);
            if(haveCollisionWithItem());

            if(getState() == Human.LIVE) {
                Rectangle currentRect = getBound();

                currentRect.x += getSpeedX();
                currentRect.y += getSpeedY();

                if(getDirection() == DIRRECTION_LEFT) {
                    Rectangle leftRect = gameWorld.getPhysicalMap().haveCollisionWithLeftRect(currentRect);
                    if(leftRect != null) setPosX(leftRect.x + leftRect.width + getWidth() / 2);
                    else {
                        setPosX(getPosX() + getSpeedX());
                    }
                }
                else if(getDirection() == DIRECTION_RIGHT) {
                    Rectangle rightRect = gameWorld.getPhysicalMap().haveCollisionWithRightRect(currentRect);
                    if(rightRect != null) setPosX(rightRect.x - getWidth() / 2);
                    else {
                        setPosX(getPosX() + getSpeedX());
                    }
                }
                else if(getDirection() == DIRECTION_UP) {
                    Rectangle aboveRect = gameWorld.getPhysicalMap().haveCollisionWithAboveRect(currentRect);
                    if(aboveRect != null) setPosY(aboveRect.y + aboveRect.height + getHeight() / 2);
                    else {
                        setPosY(getPosY() + getSpeedY());
                    }
                }
                else {
                    Rectangle belowRect = gameWorld.getPhysicalMap().haveCollisionWithBelowRect(currentRect);
                    if(belowRect != null) setPosY(belowRect.y - getHeight() / 2);
                    else setPosY(getPosY() + getSpeedY());
                }

                if(getSpeedX() == 0 && getSpeedY() == 0) setHumanRun(false);
                else setHumanRun(true);
            }

    }

    public void draw(Graphics2D g2) {

        Camera camera = getGameWorld().getCamera();
        if(getState() == LIVE) {
            if(isHumanRun()) {
                if(getDirection() == DIRECTION_DOWN) {

                    bbmDown.Update(System.nanoTime());
                    bbmDown.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
                }
                else if(getDirection() == DIRRECTION_LEFT) {

                    bbmLeft.Update(System.nanoTime());
                    bbmLeft.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
                }
                else if(getDirection() == DIRECTION_UP) {

                    bbmUp.Update(System.nanoTime());
                    bbmUp.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
                }
                else {

                    bbmRight.Update(System.nanoTime());
                    bbmRight.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);

                }
            }
            else {
                FrameImage frameImage;
                if(getDirection() == DIRECTION_DOWN)
                    frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("betty_down1"));
                else if(getDirection() == DIRRECTION_LEFT)
                    frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("betty_left1"));
                else if(getDirection() == DIRECTION_UP)
                    frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("betty_up1"));
                else  frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("betty_right1"));

                frameImage.draw(g2, (int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()));


            }
        }
        else {
            FrameImage frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("betty_die"));
            frameImage.draw(g2, (int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()));

        }
//        if(getState() == LIVE)
//            g2.setColor(Color.BLACK);
//        else g2.setColor(Color.GRAY);
//        g2.fillRect((int) (getPosX() - getWidth() / 2 -camera.getPosX()), (int) (getPosX() - getHeight() / 2 - camera.getPosY()), 48, 48);
    }

    public Rectangle getBound() {
        Rectangle rectangle = new Rectangle();
        rectangle.x = (int) (getPosX() - getWidth() / 2);
        rectangle.y = (int) (getPosY() - getHeight() / 2);
        rectangle.width = (int) getWidth();
        rectangle.height = (int) getHeight();
        return rectangle;
    }

    public boolean haveCollisionWithFlame() {

        Rectangle currentRect = getBound();
        FlamesList flamesList = gameWorld.getFlamesList();
        for(Flame f : flamesList.getFlamesList()) {
            Rectangle Rectf = f.getBound();

            if(currentRect.intersects(Rectf)) {
                return true;
            }
        }
        return false;
    }

    public boolean haveCollisionWithBoss() {
        Rectangle currentRect = getBound();
        BossTeam bossTeam = gameWorld.getBossTeam();
        for(Boss b : bossTeam.getBossTeam()) {
            Rectangle Rectb = b.getBound();
            if(currentRect.intersects(Rectb)) return true;
        }

        return false;
    }
    public boolean haveCollisionWithItem() {
        Rectangle currentRect = getBound();
        ItemsList itemsList = gameWorld.getItemsList();
        for(Item i : itemsList.getItemsList()) {
            Rectangle Recti = i.getBound();
            if(i.isRender() == true && currentRect.intersects(Recti)) {
                i.getEffect();
                return true;
            }
        }

        return false;
    }


    public void setSpeedBonus(double speedBonus) {
        this.speedBonus = speedBonus;
    }

    public double getSpeedBonus() {
        return speedBonus;
    }

}
