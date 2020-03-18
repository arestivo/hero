package com.aor.hero.arena.strategies;

import com.aor.hero.arena.Position;

import java.util.Random;

public class ZombieMoveStrategy implements MoveStrategy {

    @Override
    public Position getNextMove(Position position) {
        int x = position.getX();
        int y = position.getY();

        Random random = new Random();
        int direction = random.nextInt(4);

        switch (direction) {
            case 0: return position.left();
            case 1: return position.right();
            case 2: return position.up();
            case 3: return position.down();
        }

        return position;
    }
}
