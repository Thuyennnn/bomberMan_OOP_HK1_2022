package gameobject;

import effect.Animation;
import effect.CacheDataLoader;

import java.awt.*;

public class Flame extends Weapon {

    Animation animationFlame;
    public Flame(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setTimeDelay(200 * 1000000);
        setWidth(60);
        setHeight(60);
        animationFlame = new Animation(CacheDataLoader.getInstance().getAnimation("fire"));
    }

    @Override
    public boolean update() {
        if(System.nanoTime() - getTimeStart() > getTimeDelay()) {
            unSeted();
            getGameWorld().getFlamesList().removeFlame(this);
            return true;
        }else {
//            Rectangle bbmBound = gameWorld.getBomberman().getBound();
//            Rectangle flameBound = getBound();
//            System.out.println("bbm " + bbmBound.x + " " + bbmBound.y + " " + bbmBound.width + " " + bbmBound.height);
//            System.out.println("flame " + flameBound.x + " " + flameBound.y + " " + flameBound.width + " " + flameBound.height);
//
//            if(gameWorld.getBomberman().haveCollisionWithFlame(getBound())){
//                gameWorld.getBomberman().setState(Bomberman.DIE);
//                System.out.println("bbm die");
//            }
            return false;
        }

    }
    @Override
    public void draw(Graphics2D g2) {
        if(IsSeted() == true)
        {
            Camera camera = getGameWorld().getCamera();
            int tileSize = getGameWorld().getPhysicalMap().getTileSize();
            animationFlame.Update(System.nanoTime());
            animationFlame.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);

//            drawBound(g2);
        }
    }

    public void drawBound(Graphics2D g2) {
        Camera camera = getGameWorld().getCamera();
        Rectangle r = getBound();
        g2.setColor(Color.RED);
//
        g2.fillRect((int) (r.x - camera.getPosX()), (int) (r.y - camera.getPosY()), r.width, r.height);
    }
}
