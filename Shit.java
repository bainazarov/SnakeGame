package com.javarush.games.snake;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

public class Shit extends GameObject {
    public Shit(int x, int y) {
        super(x, y);
    }

    public boolean isAlive = false;
    private static final String SHIT_SIGN = "\uD83D";

    public void draw(Game game) {
        game.setCellValueEx(x, y, Color.NONE, SHIT_SIGN, Color.BLACK, 75);
    }
}
