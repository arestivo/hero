package com.aor.hero.arena;

public abstract class Enemy extends Element {
    private int power;

    public Enemy(Position position, int power) {
        super(position);

        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public abstract Position nextMove();
}
