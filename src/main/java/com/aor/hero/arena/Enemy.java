package com.aor.hero.arena;

public abstract class Enemy extends Element {
    private int power;

    public Enemy(int x, int y, int power) {
        super(x, y);

        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public abstract Position nextMove();
}
