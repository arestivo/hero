package com.aor.hero.arena;

public class Coin extends Element {
    private int value;

    public Coin(int x, int y, int value) {
        super(x, y);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
