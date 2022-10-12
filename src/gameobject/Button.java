package gameobject;

import java.awt.*;

public class Button {

    public static final int NONE = 0;
    public static final int PRESSED = 1;
    public static final int HOVER = 2;

    protected String text;
    protected int posX;
    protected int posY;
    protected int width;
    protected int height;
    protected int paddingTextX;
    protected int paddingTextY;
    protected boolean enabled;

    protected int state;
    protected Color bgColor;
    protected Color pressedBgColor;
    protected Color hoverBgColor;

    public Button(String text, int posX, int posY, int width, int height, int paddingTextX, int paddingTextY,
                  Color bgColor) {
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.paddingTextX = paddingTextX;
        this.paddingTextY = paddingTextY;
        this.bgColor = bgColor;
        enabled = true;
    }

    public void setEnable(boolean enabled) {
        this.enabled = enabled;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setBgColor(Color color) {
        bgColor = color;
    }

    public void setHoverBgColor(Color color) {
        hoverBgColor = color;
    }
    public void setPressedBgColor(Color color) {
        pressedBgColor = color;
    }


    public boolean isInButton(int x, int y) {
        return (enabled && x >= posX && x <= posX + width && y >= posY && y <= posY + height);
    }


    public void draw(Graphics2D g2) {
        if(enabled) {
            switch (state) {
                case NONE: g2.setColor(bgColor); break;
                case PRESSED: g2.setColor(pressedBgColor); break;
                case HOVER: g2.setColor(hoverBgColor); break;
            }
        } else {
            g2.setColor(Color.GRAY);
        }
        g2.fillRect(posX, posY, width, height);

        g2.setColor(Color.PINK);
        g2.drawRect(posX, posY, width, height);
        g2.drawRect(posX + 1, posY + 1, width - 2, height - 2);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        g2.drawString(text, posX + paddingTextX, posY + paddingTextY);
    }
}