package gameobject;


import effect.CacheDataLoader;
import effect.FrameImage;
import userInterface.GameFrame;


import java.awt.*;
import java.awt.event.KeyEvent;



public class Menu{

    public final int NUMBER_OF_BUTTON = 2;


    private Button[] buttons;
    private int buttonSelected = 0;
    private boolean canContinueGame = false;

    FrameImage bgImage;
    FrameImage bgImage2;

    public static int RENDER = 0;


    public Menu() {

        buttons = new Button[NUMBER_OF_BUTTON];
        buttons[0] = new Button("NEW GAME", 450, 250, 100, 40, 15, 25, Color.ORANGE);
        buttons[0].setHoverBgColor(Color.BLUE);
        buttons[0].setPressedBgColor(Color.GREEN);


        buttons[1] = new Button("EXIT", 450, 310, 100, 40, 15, 25, Color.ORANGE);
        buttons[1].setHoverBgColor(Color.BLUE);
        buttons[1].setPressedBgColor(Color.GREEN);

        bgImage = CacheDataLoader.getInstance().getFrameImage("bg");
        bgImage2 = CacheDataLoader.getInstance().getFrameImage("bg2");
    }


    public void Update() {
        for(int i = 0;i<NUMBER_OF_BUTTON;i++) {
            if(i == buttonSelected) {
                buttons[i].setState(Button.HOVER);
            } else {
                buttons[i].setState(Button.NONE);
            }
        }
    }


    public void Render(Graphics2D g2) {

        g2.setColor(Color.GRAY);

        g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);

        bgImage2.draw(g2, 600, 200);
        bgImage.draw(g2, 150, 200);
        for (Button bt : buttons) {
            bt.draw(g2);
        }
    }

    public int getButtonSelected() {

        return buttonSelected;
    }

    public void setButtonSelected(int buttonSelected) {
        this.buttonSelected = buttonSelected;
        System.out.println(buttonSelected);
    }
}