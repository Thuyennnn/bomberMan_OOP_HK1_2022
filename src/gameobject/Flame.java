package gameobject;

import effect.Animation;
import effect.CacheDataLoader;

import java.awt.*;

public class Flame extends Weapon {

    Animation animationFlame;

    /**
     * Constructor.
     */
    public Flame(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setTimeDelay(200 * 1000000);
        setWidth(60);
        setHeight(60);
        animationFlame = new Animation(CacheDataLoader.getInstance().getAnimation("fire"));
    }

    /**
     * Override update method for Flame.
     */
    @Override
    public boolean update() {
        if (System.nanoTime() - getTimeStart() > getTimeDelay()) {
            unSeted();
            getGameWorld().getFlamesList().removeFlame(this);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Override draw method for Flame.
     */
    @Override
    public void draw(Graphics2D g2) {
        if (IsSeted()) {
            Camera camera = getGameWorld().getCamera();
            int tileSize = getGameWorld().getPhysicalMap().getTileSize();
            animationFlame.Update(System.nanoTime());
            animationFlame.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);

        }
    }

    /**
     * Draw bound of flame.
     */
    public void drawBound(Graphics2D g2) {
        Camera camera = getGameWorld().getCamera();
        Rectangle r = getBound();
        g2.setColor(Color.RED);
        g2.fillRect((int) (r.x - camera.getPosX()), (int) (r.y - camera.getPosY()), r.width, r.height);
    }
}