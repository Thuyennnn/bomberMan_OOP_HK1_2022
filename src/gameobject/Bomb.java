package gameobject;

import effect.Animation;
import effect.CacheDataLoader;

import java.awt.*;

public class Bomb extends Weapon {

    Animation animationBomb;

    /**
     * Constructor.
     */
    public Bomb(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setTimeDelay(1500 * 1000000);
        setWidth(42);
        setHeight(42);
        animationBomb = new Animation(CacheDataLoader.getInstance().getAnimation("bomb"));
    }

    /**
     * Override update method.
     */
    @Override
    public boolean update() {
        long currentTime = System.nanoTime();
        if (currentTime - getTimeStart() > getTimeDelay()) {
            this.unSeted();
            getGameWorld().getFlamesList().add(this);
            getGameWorld().getBombsList().removeBomb(this);
            return true;
        }

        return false;
    }

    /**
     * Override draw method.
     */
    @Override
    public void draw(Graphics2D g2) {
        if (IsSeted()) {
            Camera camera = getGameWorld().getCamera();
            animationBomb.Update(System.nanoTime());
            animationBomb.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
        }
    }


}