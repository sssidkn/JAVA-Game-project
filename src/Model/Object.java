package Model;

import Contorller.State;
import Observer.WindowObserver;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Object implements WindowObserver {
    protected int maxHealth;
    protected boolean isLive = true;
    protected Cord cord;
    protected long lastAttackTime = 0;
    protected int attackFrequency;
    protected int sizeCoefficient;
    protected State state;
    protected int speedX;
    protected int speedY;
    protected int height;
    protected int width;
    protected boolean isLeft;
    protected int defaultY;
    protected int health;
    protected int hitForce;
    protected int hitRange;

    public abstract ArrayList<Sprite> getSprites();

    public abstract void setState(State state) throws IOException;


    public Cord getCord() {
        return cord;
    }

    public void setCord(Cord cord) {
        this.cord = cord;
    }

    public State getState() {
        return state;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public int getDefaultY() {
        return defaultY;
    }

    public void setDefaultY(int defaultY) {
        this.defaultY = defaultY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return cord.x;
    }

    public int getY() {
        return cord.y;
    }

    public int getSizeCoefficient() {
        return sizeCoefficient;
    }

    public int getHitForce() {
        return hitForce;
    }

    public void setHitForce(int hitForce) {
        this.hitForce = hitForce;
    }

    public void setSizeCoefficient(int sizeCoefficient) {
        this.sizeCoefficient = sizeCoefficient;
    }

    public void getDamage(int damage) throws IOException {
        health -= damage;
        if (health <= 0) {
            setState(State.DEATH);
        }
    }
    public abstract void move();

    public int getHitRange() {
        return hitRange;
    }

    public void setHitRange(int hitRange) {
        this.hitRange = hitRange;
    }

    public long getLastAttackTime() {
        return lastAttackTime;
    }

    public void setLastAttackTime(long lastAttackTime) {
        this.lastAttackTime = lastAttackTime;
    }

    public int getAttackFrequency() {
        return attackFrequency;
    }

    public void setAttackFrequency(int attackFrequency) {
        this.attackFrequency = attackFrequency;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
