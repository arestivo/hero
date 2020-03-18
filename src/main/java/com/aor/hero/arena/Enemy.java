package com.aor.hero.arena;

import com.aor.hero.arena.strategies.MoveStrategy;

public class Enemy extends Element {
    private int power;
    private MoveStrategy moveStrategy;

    public Enemy(int x, int y, int power, MoveStrategy moveStrategy) {
        super(x, y);

        this.power = power;
        this.moveStrategy = moveStrategy;
    }

    public int getPower() {
        return power;
    }

    public Position nextMove() {
        return moveStrategy.getNextMove(getPosition());
    }
}
