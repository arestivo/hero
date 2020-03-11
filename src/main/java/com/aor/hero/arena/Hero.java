package com.aor.hero.arena;

public class Hero extends Element{
    private int health;
    private int score;

    public Hero(int x, int y, int health) {
        super(x, y);

        this.health = health;
        this.score = 0;
    }

    public void decreaseHealth(int ammount) {
        this.health -= ammount;
    }

    public void increaseScore(int ammount) {
        this.score += ammount;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }
}
