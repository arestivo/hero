package com.aor.hero.arena;

public class Coin extends Element {
    private int value;

    public Coin(Position position, int value) {
        super(position);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
