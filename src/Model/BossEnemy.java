package Model;

import Contorller.State;
import View.Canvas;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class BossEnemy extends Object {
    private ArrayList<FireBall> fireBalls;
    private final ArrayList<Sprite> idleSprites;
    private final ArrayList<Sprite> deathSprites;
    private final ArrayList<Sprite> doFireBallSprites;
    private int ballSpeed = -30;

    public ArrayList<FireBall> getFireBalls() {
        return fireBalls;
    }

    @Override
    public void getDamage(int damage) throws IOException {
        health -= damage;
        if (health <= 0) {
            setState(State.DEATH);
        }
    }

    @Override
    public void setLive(boolean live) {
        isLive = live;
    }

    public BossEnemy() throws IOException {
        attackFrequency = 5000;
        fireBalls = new ArrayList<>();
        idleSprites = new ArrayList<>();
        deathSprites = new ArrayList<>();
        doFireBallSprites = new ArrayList<>();

        health = 3000;
        maxHealth = 3000;

        for (int i = 1; i < 5; i++) {
            doFireBallSprites.add(new Sprite("src/res/enemySprites/boss/attack/bossAttack" + i + ".png"));
        }
        for (int i = 1; i < 7; i++) {
            deathSprites.add(new Sprite("src/res/enemySprites/boss/death/bossDeath" + i + ".png"));
            idleSprites.add(new Sprite("src/res/enemySprites/boss/idle/idle" + i + ".png"));
        }
        height = idleSprites.get(0).height;
        width = idleSprites.get(0).width;
    }

    public void makeFireBall() throws IOException {
        if (lastAttackTime == 0 || System.currentTimeMillis() - lastAttackTime >= attackFrequency) {
            lastAttackTime = System.currentTimeMillis();
            setState(State.ATTACK);
            if (!isLeft && ballSpeed < 0) {
                ballSpeed = -ballSpeed;
            }
            if (isLeft && ballSpeed > 0) {
                ballSpeed = -ballSpeed;
            }
            fireBalls.add(new FireBall(new Cord(getX() - width * sizeCoefficient / 2, getY() - height * sizeCoefficient / 2), 200, ballSpeed));
        }
    }

    @Override
    public ArrayList<Sprite> getSprites() {
        if (state == State.ATTACK) {
            return doFireBallSprites;
        }
        if (state == State.RUN) {
            return idleSprites;
        }
        if (state == State.DEATH) {
            return deathSprites;
        }
        return idleSprites;
    }

    @Override
    public void setState(State state) throws IOException {
        if (this.state != State.DEATH) {
            this.state = state;
            Canvas.getInstance().setIndexEnemy(0);
        }
    }

    @Override
    public void update(int width, int height) throws IOException {
        setSizeCoefficient((height + width) / 300);
        for (FireBall ball : fireBalls) {
            ball.speed *= sizeCoefficient;
            if (ball.cord.x < 0 || ball.cord.x > Canvas.getInstance().getWidth()) {
                fireBalls.remove(ball);
            }
        }
        speedX = width / 100;
        int x = width * 90 / 100;
        int y = height * 90 / 100 - 40;
        setCord(new Cord(x, y));
        setDefaultY(y);
    }

    @Override
    public void move() {

    }

    public class FireBall {
        private int width;
        private int height;
        private int damage;
        private int speed;
        private Cord cord;
        private ArrayList<Sprite> moveSprites;
        private int spriteIndex = 0;

        public FireBall(Cord cord, int damage, int speed) throws IOException {
            moveSprites = new ArrayList<>();
            for (int i = 1; i < 4; i++) {
                moveSprites.add(new Sprite("src/res/enemySprites/boss/fireBall/fire-ball" + i + ".png"));
            }
            this.damage = damage;
            this.cord = cord;
            this.speed = speed;
        }

        public void draw(Graphics g) {
            Sprite sprite = moveSprites.get(spriteIndex % 3);
            width = sprite.width;
            height = sprite.height;
            sprite.draw(g, cord, width * sizeCoefficient, height * sizeCoefficient);
            spriteIndex += 1;
        }

        public void update() {
            cord.x += speed;
        }

        public int getDamage() {
            return damage;
        }

        public void setDamage(int damage) {
            this.damage = damage;
        }

        public Cord getCord() {
            return cord;
        }

        public void setCord(Cord cord) {
            this.cord = cord;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
