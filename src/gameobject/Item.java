package gameobject;

import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;

import java.awt.*;

public class Item extends GameObject {


    private char type;

    public static final char BOMB_ITEM = 'b';
    public static final char FLAME_ITEM = 'f';
    public static final char SPEED_ITEM = 's';

    private boolean render;
    private long startTime;
    private long effectedTime;

    private boolean effected;

    Animation bonusBomb, bonusSpeed, bonusFlame;

    /**
     * Constructor.
     */
    public Item(char type, double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.type = type;

        setWidth(gameWorld.getPhysicalMap().getTileSize());
        setHeight(gameWorld.getPhysicalMap().getTileSize());

        this.effectedTime = 10000;

        this.effected = false;
        this.render = true;

        bonusBomb = new Animation(CacheDataLoader.getInstance().getAnimation("bonus_bomb"));
        bonusSpeed = new Animation(CacheDataLoader.getInstance().getAnimation("bonus_speed"));
        bonusFlame = new Animation(CacheDataLoader.getInstance().getAnimation("bonus_fire"));
    }

    /**
     * Update method for item.
     */
    public void Update() {
        if (effected) {

            if (((System.nanoTime() - startTime) / 1000000) < effectedTime) {


                if (type == SPEED_ITEM) {
                    getGameWorld().getBomberman().setSpeedBonus(2);
                } else if (type == BOMB_ITEM) {
                    getGameWorld().getBombsList().MAX_BOMB = 2;
                } else if (type == FLAME_ITEM) {
                    getGameWorld().getFlamesList().MAX_FlAME = 2;
                }
            } else {

                if (type == SPEED_ITEM) {
                    getGameWorld().getBomberman().setSpeedBonus(0);
                } else if (type == BOMB_ITEM) {
                    getGameWorld().getBombsList().MAX_BOMB = 1;
                } else if (type == FLAME_ITEM) {
                    getGameWorld().getFlamesList().MAX_FlAME = 1;
                }

                ItemsList itemsList = getGameWorld().getItemsList();
                itemsList.remove(this);

            }
        }
    }

    public void getEffect() {
        effected = true;
        startTime = System.nanoTime();
        render = false;
    }

    /**
     * draw method for item.
     */
    public void draw(Graphics2D g2) {

        if (render == true) {
            Camera camera = getGameWorld().getCamera();
            if (type == SPEED_ITEM) {

                bonusSpeed.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
            } else if (type == BOMB_ITEM) {

                bonusBomb.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
            } else if (type == FLAME_ITEM) {

                bonusFlame.draw((int) (getPosX() - camera.getPosX()), (int) (getPosY() - camera.getPosY()), g2);
            }
        }

    }

    /**
     * Draw the bound of item.
     */
    public void drawBound(Graphics2D g2) {
        Camera camera = getGameWorld().getCamera();
        g2.setColor(Color.YELLOW);
        g2.fillRect((int) (getPosX() - getWidth() / 2 - camera.getPosX()), (int) (getPosY() - getHeight() / 2 - camera.getPosY()), (int) getWidth(), (int) getHeight());
    }

    public boolean isRender() {
        return render;
    }
}