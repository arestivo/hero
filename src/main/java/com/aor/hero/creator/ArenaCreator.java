package com.aor.hero.creator;

import com.aor.hero.arena.*;
import com.aor.hero.arena.strategies.ZombieMoveStrategy;

import java.util.Random;

public class ArenaCreator {
    private final Random random;

    public ArenaCreator() {
        this.random = new Random();
    }

    public Arena createArena(int width, int height, int enemies, int coins, int walls) {
        Hero hero = new Hero(width / 2, height / 2, 10);
        Arena arena = new Arena(hero, width, height);

        for (int i = 0; i < coins; i++)
            arena.addElement(new Coin(random.nextInt(width), random.nextInt(height), random.nextInt(10) + 1));

        for (int i = 0; i < enemies; i++)
            arena.addElement(new Enemy(random.nextInt(width), random.nextInt(height), 2, new ZombieMoveStrategy()));

        for (int i = 0; i < walls; i++)
            arena.addElement(new Wall(random.nextInt(width), random.nextInt(height)));

        return arena;
    }
}
