package ru.ncedu.lysenko;

import java.lang.reflect.Array;
import java.util.*;

import static ru.ncedu.lysenko.MyFrame.*;

class Game {

    private int score = 0;
    private boolean endGame = false;
    private int directionX, directionY;
    private int[] headCoord = new int[2];
    private int[] appleCoord = new int[2];

    LinkedList<int[]> snakeListDe = new LinkedList<>();

    boolean isEndGame(){
        return  endGame;
    }

    int getScore(){
        return score;
    }

    int getAppleX() {
        return appleCoord[0];
    }

    int getAppleY() {
        return appleCoord[1];
    }

    int getHeadX() {
        return headCoord[0];
    }

    int getHeadY() {
        return headCoord[1];
    }

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

    Game() {
        directionX = -1;
        directionY = 0;

        headCoord[0] = FWIDTH / 2;
        headCoord[1] = FHIGHT / 2;

        snakeListDe.addFirst(headCoord);
        appleCoord[0] = 10;
        appleCoord[1] = 20;
    }

    void move() {

        int[] newHead = new int[2];
        newHead[0] = this.step(headCoord[0] + directionX, FWIDTH);
        newHead[1] = this.step(headCoord[1] + directionY, FHIGHT);

        if (this.containsElement(newHead, snakeListDe)) {
//        if (snakeListDe.contains(newHead)){
            endGame = true;
        } else {
            snakeListDe.removeLast();
            snakeListDe.addFirst(newHead);

            if ((appleCoord[0] == newHead[0]) && (appleCoord[1] == newHead[1])) {
                score = score + ADD_SCORE;
                this.appleNew();
                snakeListDe.addFirst(newHead);
            }
            headCoord = newHead;
        }
    }

    private void appleNew() {
        int aX, aY;
        Random rand = new Random();
        do {
            aX = rand.nextInt(FWIDTH);
            aY = rand.nextInt(FHIGHT);
        } while (this.containsElement(new int[]{aX, aY}, snakeListDe));
//        } while (snakeListDe.contains(new int[]{aX, aY}));
        appleCoord[0] = aX;
        appleCoord[1] = aY;
    }

    private int step(int newCoord, int FULLSIZE) {
        if (newCoord == FULLSIZE) return 0;
        else if (newCoord == -1) return (FULLSIZE - 1);
        else return newCoord;
    }

    private boolean containsElement(int[] a, Deque<int[]> deque) {
        for (int[] el : deque) {
            if (Arrays.equals(el, a)) return true;
//            if ((el[0] == a[0]) && (el[1] == a[1])) {
//                return true;
//            }
        }
        return false;
    }

}
