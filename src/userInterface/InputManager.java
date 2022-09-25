package userInterface;

import java.awt.event.KeyEvent;
public class InputManager {
    public void  processKeyPressed (int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                System.out.println("Press UP");
                break;

            case KeyEvent.VK_DOWN:
                System.out.println("Press DOWN");
                break;
        }
    }

    public void  processKeyRelease (int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                System.out.println("Release UP");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Release DOWN");
                break;
        }
    }
}
