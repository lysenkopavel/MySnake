package ru.ncedu.lysenko;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.ncedu.lysenko.MyFrame.SPEED;

public class MyActionListener implements ActionListener {

    private MyPanel myPanel;
    private Game snakeGame;
    private MyKey key;

    MyActionListener(MyPanel myPanel) {
        this.myPanel = myPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Timer timerDraw = new Timer(SPEED, new ActionListener() {
            private StringBuilder currentScore = new StringBuilder();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!snakeGame.endGame) {
                    snakeGame.setDirectionX(key.getNewDirectionX());
                    snakeGame.setDirectionY(key.getNewDirectionY());
                    snakeGame.move();
                    if (myPanel.drawScore != snakeGame.score) {
                        myPanel.drawScore = snakeGame.score;
                        currentScore.setLength(0);
                        currentScore.append("Score: ");
                        currentScore.append(snakeGame.score);
                        myPanel.textScore.setText(currentScore.toString());
                    }
                    myPanel.repaint();
                } else {
                    myPanel.drawGameOver = true;
                    myPanel.repaint();
                }
            }
        });

        snakeGame = new Game(myPanel);
        myPanel.drawGameOver = false;
        myPanel.drawTwoDimAr = true;
        key = new MyKey(snakeGame);
        myPanel.buttonNewGame.addKeyListener(key);
        timerDraw.start();
    }
}
