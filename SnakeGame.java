package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private Apple apple;
    private Shit shit;
    private int turnDelay;
    private boolean isGameStopped;
    private static final int GOAL = 18;
    private int score;

    private void createNewApple() {
        Apple newApple;
        do {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            newApple = new Apple(x, y);
        } while (snake.checkCollision(newApple));
        apple = newApple;
        drawScene();
    }
    private void createNewShit() {
        Shit newShit;
        do {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            newShit = new Shit(x,y);
        } while (snake.checkCollision(newShit));
        shit = newShit;
        drawScene();
    }

    @Override
    public void initialize() {
        setScreenSize(WIDTH,HEIGHT);
        createGame();
    }
    private void createGame(){
        snake = new Snake(WIDTH/2,HEIGHT/2);
        createNewApple();
        createNewShit();
        isGameStopped = false;
        turnDelay = 300;
        setTurnTimer(turnDelay);
        drawScene();
        score = 0;
        setScore(score);
    }
    private void drawScene(){
        for (int x = 0; x < WIDTH; x++)
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x,y,Color.LIGHTBLUE,"");
            }
        snake.draw(this);
        apple.draw(this);
    }
    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (!apple.isAlive) {
            createNewApple();
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
        }
        if (!snake.isAlive) {
            gameOver();
        }

        if (snake.getLength() > GOAL) {
            win();
        }
        drawScene();
    }
    @Override
    public void onKeyPress(Key key) {
        if ( isGameStopped == true && key == Key.SPACE) {
            createGame();
        }
        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        } else if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        }
    }
    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "Чел ты..." + " набрал всего " + score, Color.RED, 30);
    }
    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "Сюдааа...." + score, Color.GREEN, 30);
    }
}

