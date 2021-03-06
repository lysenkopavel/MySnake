package ru.ncedu.lysenko;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.floor;
import static ru.ncedu.lysenko.MyFrame.*;

public class MyPanel extends JPanel {

    private Image backgroundImage = null;
    private Image headImage = null;
    private Image appleImage = null;
    private Image bodyImage = null;
    private Image gameOver = null;

    private JButton buttonExit = new JButton("Exit");
    private JButton buttonNewGame = new JButton("New game");
    private JLabel textScore = new JLabel("Score: 0");

    boolean drawGameOver = false;
    boolean drawTwoDimAr = false;

    private Game gameS;

    void setGameS(Game gameS) {
        this.gameS = gameS;
    }

    JLabel getTextScore() {
        return textScore;
    }

    JButton getButtonNewGame() {
        return buttonNewGame;
    }

    MyPanel() {

        setLayout(null);
        add(buttonNewGame);
        add(buttonExit);
        add(textScore);

        this.readImage();

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ActionListener al = new MyActionListener(this);
        buttonNewGame.addActionListener(al);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, null);

        buttonNewGame.setBounds(650, 100, 100, 30);
        buttonNewGame.setBackground(new Color(90, 227, 62));

        buttonExit.setBounds(650, 150, 100, 30);
        buttonExit.setBackground(new Color(227, 55, 62));

        textScore.setBounds(650, 200, 100, 30);
        textScore.setFont(new Font("Serif", Font.PLAIN, 16));

        this.paintGrid(g);
        if (drawTwoDimAr) this.paintTwoDemArray(g);
        if (drawGameOver) g.drawImage(gameOver, 50, (int) floor(FRAME_HEIGHT / 3), null);

    }

    private void paintGrid(Graphics g) {
        g.setColor(new Color(38, 41, 169));
        for (int xx = 0; xx <= FSCALE * FWIDTH; xx += FSCALE) {
            g.drawLine(xx + INDENT, INDENT, xx + INDENT, FSCALE * FHIGHT + INDENT);
        }
        for (int yy = 0; yy <= FSCALE * FHIGHT; yy += FSCALE) {
            g.drawLine(INDENT, yy + INDENT, FSCALE * FWIDTH + INDENT, yy + INDENT);
        }
    }

    private void paintTwoDemArray(Graphics g) {
        for (int[] a : gameS.snakeListDe) {
            g.drawImage(bodyImage, INDENT + 1 + a[0] * FSCALE, INDENT + 1 + a[1] * FSCALE, null);
        }
        g.drawImage(headImage, INDENT + gameS.getHeadX() * FSCALE, INDENT + gameS.getHeadY() * FSCALE, null);
        g.drawImage(appleImage, INDENT + 1 + gameS.getAppleX() * FSCALE, INDENT + 1 + gameS.getAppleY() * FSCALE, null);
    }

    private void readImage() {
        String backgroungPath = "Images/background.jpg";
        String headImagePath = "Images/head_20pix.png";
        String appleImagePath = "Images/apple_19pix.jpg";
        String bodyImagePath = "Images/bodyNEW_19pix.png";
        String gameOverImagePath = "Images/game_over.png";
        try {
            backgroundImage = ImageIO.read(new File(backgroungPath));
            appleImage = ImageIO.read(new File(appleImagePath));
            headImage = ImageIO.read(new File(headImagePath));
            bodyImage = ImageIO.read(new File(bodyImagePath));
            gameOver = ImageIO.read(new File(gameOverImagePath));
        } catch (IOException IOe) {
            IOe.printStackTrace();
        }
    }


}
