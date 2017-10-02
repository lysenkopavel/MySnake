package ru.ncedu.lysenko;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.ncedu.lysenko.MyFrame.SPEED;

public class MyActionListener implements ActionListener {

    private MyPanel myPanel;
    private Game snakeGame;
    private MyKey key;
    private Timer timer = this.createTimer();

    MyActionListener(MyPanel myPanel) {
        this.myPanel = myPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snakeGame = new Game();
        myPanel.setGameS(snakeGame);

        myPanel.drawGameOver = false;
        myPanel.drawTwoDimAr = true;

        key = new MyKey(snakeGame);
        myPanel.getButtonNewGame().addKeyListener(key);
        timer.restart();
    }

    private Timer createTimer(){
        Timer timerDraw = new Timer(SPEED, new ActionListener() {

            int drawScore = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!snakeGame.isEndGame()) {

                    snakeGame.setDirectionX(key.getNewDirectionX());
                    snakeGame.setDirectionY(key.getNewDirectionY());
                    snakeGame.move();

                    if (drawScore != snakeGame.getScore()) {
                        StringBuilder currentScore = new StringBuilder();
                        currentScore.setLength(0);
                        currentScore.append("Score: ");
                        currentScore.append(snakeGame.getScore());
                        myPanel.getTextScore().setText(currentScore.toString());
                        drawScore = snakeGame.getScore();
                    }

                    myPanel.repaint();

                } else {
                    myPanel.drawGameOver = true;
                    myPanel.repaint();
                }
            }
        });

        return timerDraw;
    }

}
