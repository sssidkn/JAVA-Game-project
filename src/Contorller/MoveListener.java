package Contorller;

import Model.Hero;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MoveListener implements KeyListener {
    private final Hero hero;

    public MoveListener(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                hero.setLeft(e.getKeyCode() == KeyEvent.VK_LEFT);
                hero.setMoving(true);
                hero.setState(State.RUN);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                hero.setState(State.JUMP);
                hero.setMoving(false);
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                hero.setState(State.ATTACK);
                hero.setMoving(false);
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            try {
                hero.setState(State.STAY);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            hero.setMoving(false);
        }
    }
}
