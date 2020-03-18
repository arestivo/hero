package com.aor.hero.arena;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;

    private Hero hero;
    private List<Enemy> enemies;
    private List<Wall> walls;
    private List<Coin> coins;

    private boolean isFinished;

    private List<ArenaObserver> observers;

    public Arena(Hero hero, int width, int height) {
        this.width = width;
        this.height = height;

        this.hero = hero;

        this.enemies = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.coins = new ArrayList<>();

        this.isFinished = false;

        this.observers = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addElement(Element element) {
        if (element instanceof Hero) hero = (Hero) element;
        if (element instanceof Enemy) enemies.add((Enemy) element);
        if (element instanceof Wall) walls.add((Wall) element);
        if (element instanceof Coin) coins.add((Coin) element);

        this.notifyObservers();
    }

    public void moveHeroTo(Position position) {
        if (canMove(position)) hero.setPosition(position);
        checkCollisions(hero.getPosition());

        this.notifyObservers();
    }

    public void step() {
        for (Enemy enemy : enemies) {
            Position position = enemy.nextMove();
            if (canMove(position)) enemy.setPosition(position);
        }

        this.notifyObservers();
    }

    private boolean canMove(Position position) {
        if (position.getX() < 0 || position.getX() >= width) return false;
        if (position.getY() < 0 || position.getY() >= height) return false;

        Wall wall = (Wall) getCollidingElement(position, walls);
        return wall == null;
    }

    private void checkCollisions(Position position) {
        Enemy enemy = (Enemy) getCollidingElement(position, enemies);
        if (enemy != null) {
            hero.increaseScore(-enemy.getPower());
            hero.decreaseHealth(enemy.getPower());
        }

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
        return hero.isDead() || coins.size() == 0 || isFinished;
    }

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>();

        elements.add(hero);
        elements.addAll(coins);
        elements.addAll(walls);
        elements.addAll(enemies);

        return elements;
    }

    public int getScore() {
        return hero.getScore();
    }

    public int getHeroHealth() {
        return hero.getHealth();
    }

    public Position getHeroPosition() {
        return hero.getPosition();
    }

    public void finish() {
        this.isFinished = true;
    }

    public void addObserver(ArenaObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (ArenaObserver observer : observers) {
            observer.arenaChanged();
        }
    }
}
