package com.aor.hero.gui;

import com.aor.hero.arena.*;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Gui {
    private final Arena arena;
    private final TerminalScreen screen;

    public enum MOVE {UP, DOWN, LEFT, RIGHT, QUIT, EOF, NONE}

    public Gui(Arena arena) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        this.arena = arena;
    }

    public void draw() throws IOException {
        screen.clear();

        drawElement(arena.getHero());

        for (Enemy enemy : arena.getEnemies()) drawElement(enemy);
        for (Wall wall : arena.getWalls()) drawElement(wall);
        for (Coin coin : arena.getCoins()) drawElement(coin);

        screen.refresh();
    }

    private void drawElement(Element enemy) {
        char character = '?';

        if (enemy instanceof Hero) character = 'H';
        if (enemy instanceof Zombie) character = 'Z';
        if (enemy instanceof Wall) character = '#';
        if (enemy instanceof Coin) character = 'O';

        screen.setCharacter(enemy.getPosition().getX(), enemy.getPosition().getY(), new TextCharacter(character));
    }

    public MOVE getNextMovement() throws IOException {
        KeyStroke input = screen.readInput();

        System.out.println(input);

        if (input.getKeyType() == KeyType.EOF) return MOVE.EOF;
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q') return MOVE.QUIT;
        if (input.getKeyType() == KeyType.ArrowDown) return MOVE.DOWN;
        if (input.getKeyType() == KeyType.ArrowUp) return MOVE.UP;
        if (input.getKeyType() == KeyType.ArrowLeft) return MOVE.LEFT;
        if (input.getKeyType() == KeyType.ArrowRight) return MOVE.RIGHT;

        return MOVE.NONE;
    }

    public void close() throws IOException {
        screen.close();
    }
}
