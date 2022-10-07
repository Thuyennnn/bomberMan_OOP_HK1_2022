package gameobject;

import effect.Animation;
import effect.CacheDataLoader;

import java.awt.*;

public class Bomb extends Weapon {

    Animation animationBomb;
    public Bomb(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setTimeDelay(1500 * 1000000);
        animationBomb = new Animation(CacheDataLoader.getInstance().getAnimation("bomb"));
    }

    @Override
    public boolean update() {
        long currentTime = System.nanoTime();
        if(currentTime - getTimeStart() > getTimeDelay()) {
            unSeted();
            gameWorld.getFlamesList().add(this);
            gameWorld.getBombsList().removeBomb(this);
            return true;
        }

        return  false;
    }
    @Override
    public void draw(Graphics2D g2) {
        if(IsSeted() == true) {
            Camera camera = gameWorld.getCamera();
            animationBomb.Update(System.nanoTime());
            animationBomb.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
        }
    }



}
