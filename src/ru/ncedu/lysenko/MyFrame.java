package ru.ncedu.lysenko;

import javax.swing.*;

class MyFrame extends JFrame{

    static final int FRAME_HEIGHT=650, FRAME_WIDTH=800;
    static final int FWIDTH = 30, FHIGHT = 30, FSCALE = 20, INDENT = 10;
    static final int SPEED = 100;
    static final int ADD_SCORE = 10;

    MyFrame() {
        setTitle("Game Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        setResizable(false);

        JPanel panel = new MyPanel();
        getContentPane().add(panel);
    }


}
