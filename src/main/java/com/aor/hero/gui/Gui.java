package com.aor.hero.gui;

import com.aor.hero.arena.*;
import com.aor.hero.commands.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Gui {
    private final Arena arena;
    private final TerminalScreen screen;

    public Gui(Arena arena) throws IOException {
        TerminalSize terminalSize = new TerminalSize(arena.getWidth(), arena.getHeight() + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        this.arena = arena;
    }

    public void draw() throws IOException {
        screen.clear();

        drawArena();
        drawScore();

        for (Element element : arena.getAllElements()) drawElement(element);

        screen.refresh();
    }

    private void drawArena() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(arena.getWidth(), arena.getHeight()),
                ' '
        );
    }

    private void drawScore() {
        screen.newTextGraphics().putString(0, arena.getHeight(), "Score: " + arena.getScore() + " Health: " + arena.getHeroHealth());
    }

    private void drawElement(Element element) {
        if (element instanceof Hero) drawCharacter(element.getPosition(), "H", "#FFFFFF");
        if (element instanceof Enemy) drawCharacter(element.getPosition(), "E", "#FF0000");
        if (element instanceof Wall) drawCharacter(element.getPosition(), "#", "#FFFFFF");
        if (element instanceof Coin) drawCharacter(element.getPosition(), "O", "#FFFF00");
    }

    private void drawCharacter(Position position, String character, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));

        graphics.putString(position.getX(), position.getY(), character);
    }

    public Command getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        if (input.getKeyType() == KeyType.EOF) return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q') return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.ArrowDown) return new MoveHeroDownCommand(arena);
        if (input.getKeyType() == KeyType.ArrowUp) return new MoveHeroUpCommand(arena);
        if (input.getKeyType() == KeyType.ArrowLeft) return new MoveHeroLeftCommand(arena);
        if (input.getKeyType() == KeyType.ArrowRight) return new MoveHeroRightCommand(arena);

        return new DoNothingCommand();
    }
}
