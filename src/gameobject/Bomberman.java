package gameobject;

import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;

import java.awt.*;

public class Bomberman {
    private double posX;
    private double posY;
    private double width;
    private double height;
    private double speedX;
    private double speedY;

    private double speedBonus;

    private int direction;

    public static final int DIRECTION_DOWN = 1;
    public static final int DIRRECTION_LEFT = 2;
    public static final int DIRECTION_UP = 3;
    public static final int DIRECTION_RIGHT = 4;

    private int state;

    public static final int LIVE = 1;
    public static final int DIE = 2;
    private boolean bbmRun;
    GameWorld gameWorld;

    Animation bbmLeft, bbmRight, bbmUp, bbmDown, bbmDie;

    public Bomberman(double posX, double posY, double width, double height, GameWorld gameWorld) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.gameWorld = gameWorld;

        this.speedBonus = 0;
        this.state = 1;
        this.bbmRun = false;
        this.direction = DIRECTION_DOWN;

        bbmDown = new Animation(CacheDataLoader.getInstance().getAnimation("betty_down"));
        bbmLeft = new Animation(CacheDataLoader.getInstance().getAnimation("betty_left"));
        bbmUp = new Animation(CacheDataLoader.getInstance().getAnimation("betty_up"));
        bbmRight = new Animation(CacheDataLoader.getInstance().getAnimation("betty_right"));
    }



    public void update() {

            if(haveCollisionWithFlame()) state = DIE;
            if(haveCollisionWithItem());

            if(state == LIVE) {
                Rectangle currentRect = getBound();

                currentRect.x += speedX;
                currentRect.y += speedY;

                if(direction == DIRRECTION_LEFT) {
                    Rectangle leftRect = gameWorld.getPhysicalMap().haveCollisionWithLeftRect(currentRect);
                    if(leftRect != null) setPosX(leftRect.x + leftRect.width + width / 2);
                    else {
                        setPosX(getPosX() + speedX);
                    }
                }
                else if(direction == DIRECTION_RIGHT) {
                    Rectangle rightRect = gameWorld.getPhysicalMap().haveCollisionWithRightRect(currentRect);
                    if(rightRect != null) setPosX(rightRect.x - width / 2);
                    else {
                        setPosX(getPosX() + speedX);
                    }
                }
                else if(direction == DIRECTION_UP) {
                    Rectangle aboveRect = gameWorld.getPhysicalMap().haveCollisionWithAboveRect(currentRect);
                    if(aboveRect != null) setPosY(aboveRect.y + aboveRect.height + height / 2);
                    else {
                        setPosY(getPosY() + speedY);
                    }
                }
                else {
                    Rectangle belowRect = gameWorld.getPhysicalMap().haveCollisionWithBelowRect(currentRect);
                    if(belowRect != null) setPosY(belowRect.y - height / 2);
                    else setPosY(getPosY() + speedY);
                }

                if(getSpeedX() == 0 && getSpeedY() == 0) bbmRun = false;
                else bbmRun = true;
            }

    }

    public void draw(Graphics2D g2) {

        Camera camera = gameWorld.getCamera();
//        if(state == LIVE) {
//            if(bbmRun) {
//                if(direction == DIRECTION_DOWN) {
//
//                    bbmDown.Update(System.nanoTime());
//                    bbmDown.draw((int) (posX - camera.getPosX()), (int) (posY - camera.getPosY()), g2);
//                }
//                else if(direction == DIRRECTION_LEFT) {
//
//                    bbmLeft.Update(System.nanoTime());
//                    bbmLeft.draw((int) (posX - camera.getPosX()), (int) (posY - camera.getPosY()), g2);
//                }
//                else if(direction == DIRECTION_UP) {
//
//                    bbmUp.Update(System.nanoTime());
//                    bbmUp.draw((int) (posX - camera.getPosX()), (int) (posY - camera.getPosY()), g2);
//                }
//                else {
//
//                    bbmRight.Update(System.nanoTime());
//                    bbmRight.draw((int) (posX - camera.getPosX()), (int) (posY - camera.getPosY()), g2);
//
//                }
//            }
//            else {
//                FrameImage frameImage;
//                if(direction == DIRECTION_DOWN)
//                    frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("betty_down1"));
//                else if(direction == DIRRECTION_LEFT)
//                    frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("betty_left1"));
//                else if(direction == DIRECTION_UP)
//                    frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("betty_up1"));
//                else  frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("betty_right1"));
//
//                frameImage.draw(g2, (int) (posX - camera.getPosX()), (int) (posY - camera.getPosY()));
//
//
//            }
//        }
//        else {
//            FrameImage frameImage = new FrameImage(CacheDataLoader.getInstance().getFrameImage("betty_die"));
//            frameImage.draw(g2, (int) (posX - camera.getPosX()), (int) (posY - camera.getPosY()));
//
//        }
        if(state == LIVE)
            g2.setColor(Color.BLACK);
        else g2.setColor(Color.GRAY);
        g2.fillRect((int) (posX - width / 2 -camera.getPosX()), (int) (posY - height / 2 - camera.getPosY()), 48, 48);
    }

    public Rectangle getBound() {
        Rectangle rectangle = new Rectangle();
        rectangle.x = (int) (posX - width / 2);
        rectangle.y = (int) (posY - height / 2);
        rectangle.width = (int) width;
        rectangle.height = (int) height;
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

    public void setSpeedBonus(double speedBonus) {
        this.speedBonus = speedBonus;
    }

    public double getSpeedBonus() {
        return speedBonus;
    }

    public void setState(int state) {
        this.state = state;
    }
}
