package gameobject;

public abstract class GameObject {
    double PosX;
    double PosY;
    GameWorld gameWorld;

    public GameObject(double posX, double posY, GameWorld gameWorld) {
        PosX = posX;
        PosY = posY;
        this.gameWorld = gameWorld;
    }

    public double getPosX() {
        return PosX;
    }

    public void setPosX(double posX) {
        PosX = posX;
    }

    public double getPosY() {
        return PosY;
    }

    public void setPosY(double posY) {
        PosY = posY;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public abstract void Update() ;
}
