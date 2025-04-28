package Model;

import Contorller.State;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class BossEnemyAdapter extends Enemy {
    private BossEnemy bossEnemy;

    public BossEnemyAdapter(BossEnemy enemy) {
        bossEnemy = enemy;
    }

    @Override
    public void attack(Object object) throws IOException {
        bossEnemy.makeFireBall();
    }

    @Override
    public ArrayList<BossEnemy.FireBall> getFireBalls() {
        return bossEnemy.getFireBalls();
    }

    @Override
    public void update(int width, int height) throws IOException {
        bossEnemy.update(width, height);
    }

    @Override
    public void move() {
        bossEnemy.move();
    }

    @Override
    public ArrayList<Sprite> getSprites() {
        return bossEnemy.getSprites();
    }

    @Override
    public void setState(State state) throws IOException {
        bossEnemy.setState(state);
    }

    @Override
    public int getX() {
        return bossEnemy.getX();
    }

    @Override
    public int getY() {
        return bossEnemy.getY();
    }

    @Override
    public Cord getCord() {
        return bossEnemy.cord;
    }

    @Override
    public void setCord(Cord cord) {
        bossEnemy.setCord(cord);
    }

    @Override
    public State getState() {
        return bossEnemy.getState();
    }

    @Override
    public int getSpeedX() {
        return bossEnemy.getSpeedX();
    }

    @Override
    public void setSpeedX(int speedX) {
        bossEnemy.setSpeedX(speedX);
    }

    @Override
    public int getSpeedY() {
        return super.getSpeedY();
    }

    @Override
    public void setSpeedY(int speedY) {
        super.setSpeedY(speedY);
    }

    @Override
    public int getHeight() {
        return bossEnemy.getHeight();
    }

    @Override
    public void setHeight(int height) {
        bossEnemy.setHeight(height);
    }

    @Override
    public int getWidth() {
        return bossEnemy.getWidth();
    }

    @Override
    public void setWidth(int width) {
        bossEnemy.setWidth(width);
    }

    @Override
    public boolean isLeft() {
        return bossEnemy.isLeft();
    }

    @Override
    public void setLeft(boolean left) {
        bossEnemy.setLeft(left);
    }

    @Override
    public int getDefaultY() {
        return bossEnemy.getDefaultY();
    }

    @Override
    public void setDefaultY(int defaultY) {
        bossEnemy.setDefaultY(defaultY);
    }

    @Override
    public int getHealth() {
        return bossEnemy.getHealth();
    }

    @Override
    public void setHealth(int health) {
        bossEnemy.setHealth(health);
    }

    @Override
    public int getSizeCoefficient() {
        return bossEnemy.getSizeCoefficient();
    }

    @Override
    public int getHitForce() {
        return bossEnemy.getHitForce();
    }

    @Override
    public void setHitForce(int hitForce) {
        bossEnemy.setHitForce(hitForce);
    }

    @Override
    public void setSizeCoefficient(int sizeCoefficient) {
        bossEnemy.setSizeCoefficient(sizeCoefficient);
    }

    @Override
    public void getDamage(int damage) throws IOException {
        bossEnemy.getDamage(damage);
    }

    @Override
    public int getHitRange() {
        return bossEnemy.getHitRange();
    }

    @Override
    public void setHitRange(int hitRange) {
        bossEnemy.setHitRange(hitRange);
    }

    @Override
    public long getLastAttackTime() {
        return bossEnemy.getLastAttackTime();
    }

    @Override
    public void setLastAttackTime(long lastAttackTime) {
        bossEnemy.setLastAttackTime(lastAttackTime);
    }

    @Override
    public int getAttackFrequency() {
        return super.getAttackFrequency();
    }

    @Override
    public void setAttackFrequency(int attackFrequency) {
        bossEnemy.setAttackFrequency(attackFrequency);
    }

    @Override
    public int getMaxHealth() {
        return bossEnemy.getMaxHealth();
    }

    @Override
    public void setMaxHealth(int maxHealth) {
        bossEnemy.setMaxHealth(maxHealth);
    }

    @Override
    public boolean isLive() {
        return bossEnemy.isLive();
    }

    @Override
    public void setLive(boolean live) {
        bossEnemy.setLive(live);
    }
}
