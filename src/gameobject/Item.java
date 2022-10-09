package gameobject;

import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;

import java.awt.*;

public class Item {
    private double posX;
    private double posY;

    private double width;
    private double height;

    GameWorld gameWorld;

    private char type;

    public static final char BOMB_ITEM = 'b';
    public static final char FLAME_ITEM = 'f';
    public static final char SPEED_ITEM = 's';

    private boolean render;
    private long startTime;
    private long effectedTime;

    private boolean effected;

    Animation bonusBomb, bonusSpeed, bonusFlame;

    public Item(char type, double posX, double posY, GameWorld gameWorld) {
        this.type = type;

        this.posX = posX;
        this.posY = posY;

        this.gameWorld = gameWorld;

        this.width = gameWorld.getPhysicalMap().getTileSize();
        this.height = gameWorld.getPhysicalMap().getTileSize();

        this.effectedTime = 10000;

        this.effected = false;
        this.render = true;

        bonusBomb = new Animation(CacheDataLoader.getInstance().getAnimation("bonus_bomb"));
        bonusSpeed = new Animation(CacheDataLoader.getInstance().getAnimation("bonus_speed"));
        bonusFlame = new Animation(CacheDataLoader.getInstance().getAnimation("bonus_fire"));
    }

    public void Update() {
        if(effected == true) {

            if(((System.nanoTime() - startTime) / 1000000) < effectedTime) {


                if(type == SPEED_ITEM) {
                    gameWorld.getBomberman().setSpeedBonus(2);
                }
                else if(type == BOMB_ITEM) {
                    gameWorld.getBombsList().MAX_BOMB = 2;
                }
                else if(type == FLAME_ITEM) {
                    gameWorld.getFlamesList().MAX_FlAME = 2;
                }
            }
            else {

                if(type == SPEED_ITEM) {
                    gameWorld.getBomberman().setSpeedBonus(0);
                }
                else if(type == BOMB_ITEM) {
                    gameWorld.getBombsList().MAX_BOMB = 1;
                }
                else if(type == FLAME_ITEM) {
                    gameWorld.getFlamesList().MAX_FlAME = 1;
                }

                ItemsList itemsList = gameWorld.getItemsList();
                itemsList.remove(this);

            }
        }
    }

    public void getEffect() {
        effected = true;
        startTime = System.nanoTime();
        render = false;
    }
    public void draw(Graphics2D g2) {

//        Camera camera = gameWorld.getCamera();
//        if(type == SPEED_ITEM) {
//
//            bonusSpeed.draw((int) (posX - camera.getPosX()), (int) (posY - camera.getPosY()), g2);
//        }
//        else if(type == BOMB_ITEM) {
//
//            bonusBomb.draw((int) (posX - camera.getPosX()), (int) (posY - camera.getPosY()), g2);
//        }
//        else if(type == FLAME_ITEM) {
//
//            bonusFlame.draw((int) (posX - camera.getPosX()), (int) (posY - camera.getPosY()), g2);
//        }
        if(render == true)
            drawBound(g2);
    }

    public Rectangle getBound() {
        Rectangle rectangle = new Rectangle();

        rectangle.x = (int) (posX - width / 2);
        rectangle.y = (int) (posY - height / 2);
        rectangle.width = (int) width;
        rectangle.height = (int) height;

        return rectangle;
    }

    public void drawBound(Graphics2D g2) {
        Camera camera = gameWorld.getCamera();
        g2.setColor(Color.YELLOW);
        g2.fillRect((int) (posX - width / 2 - camera.getPosX()), (int) (posY - height / 2 - camera.getPosY()), (int) width, (int) height);
    }

    public boolean isRender() {
        return render;
    }
}
