package com.aor.hero;

import com.aor.hero.arena.*;
import com.aor.hero.gui.Gui;

import java.io.IOException;
import java.util.Random;

public class Game {
    public static void main(String[] args) throws IOException {
        System.out.println("Start");

        Hero hero = new Hero(new Position(10, 10), 100);
        Arena arena = new Arena(hero);

        createEnemies(arena);
        createWalls(arena);
        createCoins(arena);

        Gui gui = new Gui(arena);

        while (!arena.isFinished()) {
            gui.draw();

            Gui.MOVE movement = gui.getNextMovement();

            if (movement == Gui.MOVE.DOWN) arena.moveHeroDown();
            if (movement == Gui.MOVE.UP) arena.moveHeroUp();
            if (movement == Gui.MOVE.LEFT) arena.moveHeroLeft();
            if (movement == Gui.MOVE.RIGHT) arena.moveHeroRight();

            if (movement == Gui.MOVE.QUIT) gui.close();
            if (movement == Gui.MOVE.EOF) break;

            arena.step();
        }

        gui.draw();
        System.out.println("Score: " + arena.getHero().getScore());
    }

    private static void createCoins(Arena arena) {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            int value = 5 + random.nextInt(6);

            arena.addElement(new Coin(new Position(x, y), value));
        }
    }

    private static void createWalls(Arena arena) {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            int value = 5 + random.nextInt(6);

            arena.addElement(new Wall(new Position(x, y)));
        }
    }

    private static void createEnemies(Arena arena) {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            int value = 5 + random.nextInt(6);

            arena.addElement(new Zombie(new Position(x, y), value));
        }
    }
}
