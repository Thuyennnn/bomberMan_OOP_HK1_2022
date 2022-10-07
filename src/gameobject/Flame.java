package gameobject;

import effect.Animation;
import effect.CacheDataLoader;

import java.awt.*;

public class Flame extends Weapon {

    Animation animationFlame;
    public Flame(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setTimeDelay(200 * 1000000);
        animationFlame = new Animation(CacheDataLoader.getInstance().getAnimation("fire"));
    }

    @Override
    public boolean update() {
        if(System.nanoTime() - getTimeStart() > getTimeDelay()) {
            unSeted();
            gameWorld.getFlamesList().removeFlame(this);
            return true;
        }
        return false;
    }
    @Override
    public void draw(Graphics2D g2) {
        if(IsSeted() == true) {

            Camera camera = gameWorld.getCamera();
            int tileSize = gameWorld.getPhysicalMap().getTileSize();
            animationFlame.Update(System.nanoTime());
            animationFlame.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
        }
    }
}
