package ru.ncedu.lysenko;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class MyKey extends KeyAdapter {

    private Game snakeGame;
    private int newDirectionY , newDirectionX;

    int getNewDirectionY() {
        return newDirectionY;
    }

    int getNewDirectionX() {
        return newDirectionX;
    }

    MyKey(Game game) {
        this.snakeGame = game;
        newDirectionY = snakeGame.getDirectionY();
        newDirectionX = snakeGame.getDirectionX();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (snakeGame.getDirectionX() != 0) {
            if (e.getKeyCode() == VK_DOWN) {
                newDirectionX = 0;
                newDirectionY = 1;
            }
            if (e.getKeyCode() == VK_UP) {
                newDirectionX = 0;
                newDirectionY = -1;
            }
        }
        if (snakeGame.getDirectionY() != 0) {
            if (e.getKeyCode() == VK_LEFT) {
                newDirectionX = -1;
                newDirectionY = 0;
            }
            if (e.getKeyCode() == VK_RIGHT) {
                newDirectionX = 1;
                newDirectionY = 0;
            }
        }
    }

}
