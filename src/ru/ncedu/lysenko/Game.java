package ru.ncedu.lysenko;

import java.util.*;

import static ru.ncedu.lysenko.MyFrame.*;

class Game {

    MyPanel mp;
    boolean endGame = false;
    int score = 0;

    private int directionX, directionY;
    private List<int[]> snakeList = new ArrayList<>();
    private int gX, gY;

    int getDirectionX() {
        return directionX;
    }

    int getDirectionY() {
        return directionY;
    }

    void setDirectionX(int dX) {
        this.directionX = dX;
    }

    void setDirectionY(int dY) {
        this.directionY = dY;
    }

    Game(MyPanel myPanel) {

        this.mp = myPanel;

        for (int yy = 0; yy < FHIGHT; yy++) {
            for (int xx = 0; xx < FWIDTH; xx++) {
                mp.snake[yy][xx] = 0;
            }
        }
        directionX = -1;
        directionY = 0;
        gX = FWIDTH / 2;
        gY = FHIGHT / 2;
        mp.snake[gY][gX] = ISHEAD;
        snakeList.add(new int[]{gX, gY});

        int appleCoord[] = this.appleNew();
        mp.snake[appleCoord[1]][appleCoord[0]] = ISAPPLE;

    }

    void move() {
        boolean eatApple = false;

        gX = this.step(gX + directionX, FWIDTH);
        gY = this.step(gY + directionY, FHIGHT);

        if (mp.snake[gY][gX] == ISBODY) {
            endGame = true;
        } else {
            mp.snake[snakeList.get(0)[1]][snakeList.get(0)[0]] = 0;
            snakeList.remove(0);
            snakeList.add(new int[]{gX, gY});

            if (mp.snake[gY][gX] == ISAPPLE) {
                score = score + ADD_SCORE;
                snakeList.add(new int[]{gX, gY});
                eatApple = true;
            }

            for (Iterator<int[]> i = snakeList.iterator(); i.hasNext(); ) {
                int[] a = i.next();
                mp.snake[a[1]][a[0]] = ISBODY;
            }
            mp.snake[gY][gX] = ISHEAD;

            if (eatApple) mp.snake[this.appleNew()[1]][this.appleNew()[0]] = ISAPPLE;

        }
    }

    private int[] appleNew() {
        int aX, aY;
        Random rand = new Random();
        do {
            aX = rand.nextInt(FWIDTH);
            aY = rand.nextInt(FHIGHT);
        } while (mp.snake[aY][aX] != 0);
        int appleCoord[] = new int[2];

        appleCoord[0] = aX;
        appleCoord[1] = aY;
        return appleCoord;
    }

    private int step(int newCoord, int FULLSIZE){
        if (newCoord == FULLSIZE) return 0;
        else if (newCoord == -1) return (FULLSIZE - 1);
        else return newCoord;
    }


}
