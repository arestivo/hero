package com.aor.hero.arena;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private Hero hero;
    private List<Enemy> enemies;
    private List<Wall> walls;
    private List<Coin> coins;

    public Arena(Hero hero) {
        this.hero = hero;
        this.enemies = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.coins = new ArrayList<>();
    }

    public void addElement(Element element) {
        if (element instanceof Hero) hero = (Hero) element;
        if (element instanceof Enemy) enemies.add((Enemy) element);
        if (element instanceof Wall) walls.add((Wall) element);
        if (element instanceof Coin) coins.add((Coin) element);
    }

    public void moveHeroLeft() {
        Position position = hero.getPosition().left();
        if (canMove(position)) hero.setPosition(position);
        checkCollisions(hero.getPosition());
    }

    public void moveHeroRight() {
        Position position = hero.getPosition().right();
        if (canMove(position)) hero.setPosition(position);
        checkCollisions(hero.getPosition());
    }

    public void moveHeroUp() {
        Position position = hero.getPosition().up();
        if (canMove(position)) hero.setPosition(position);
        checkCollisions(hero.getPosition());
    }

    public void moveHeroDown() {
        Position position = hero.getPosition().down();
        if (canMove(position)) hero.setPosition(position);
        checkCollisions(hero.getPosition());
    }

    public void step() {
        for (Enemy enemy : enemies) {
            Position position = enemy.nextMove();
            if (canMove(position)) enemy.setPosition(position);
        }
    }

    private boolean canMove(Position position) {
        Wall wall = (Wall) getCollidingElement(position, walls);
        return wall == null;
    }

    private void checkCollisions(Position position) {
        Enemy enemy = (Enemy) getCollidingElement(position, enemies);
        if (enemy != null) hero.decreaseHealth(enemy.getPower());

        Coin coin = (Coin) getCollidingElement(position, coins);
        if (coin != null) {
            hero.increaseScore(coin.getValue());
            coins.remove(coin);
        }
    }

    private Element getCollidingElement(Position position, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return element;

        return null;
    }

    public boolean isFinished() {
        return hero.isDead() || coins.size() == 0;
    }

    public Hero getHero() {
        return hero;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Coin> getCoins() {
        return coins;
    }

}
