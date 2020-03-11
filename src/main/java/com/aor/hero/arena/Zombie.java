package com.aor.hero.arena;

import java.util.Random;

public class Zombie extends Enemy {
    public Zombie(Position position, int power) {
        super(position, power);
    }

    @Override
    public Position nextMove() {
        int x = getPosition().getX();
        int y = getPosition().getY();

        Random random = new Random();
        int direction = random.nextInt(4);

        switch (direction) {
            case 0: return new Position(x - 1, y);
            case 1: return new Position(x + 1, y);
            case 2: return new Position(x, y - 1);
            case 3: return new Position(x, y + 1);
        }

        return getPosition();
    }
}
