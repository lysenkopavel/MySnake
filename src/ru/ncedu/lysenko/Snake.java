package ru.ncedu.lysenko;

import javax.swing.*;

public class Snake {

    private Snake() {
        JFrame frame = new MyFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Snake();
            }
        });

    }
}
