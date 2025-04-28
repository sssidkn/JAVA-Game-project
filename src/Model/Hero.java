package Model;

import Contorller.State;
import View.Canvas;

import java.io.IOException;
import java.util.ArrayList;

import static Tools.Func.distance;

public class Hero extends Object {
    private int jumpHeight;
    private boolean isMoving;
    private final ArrayList<Sprite> runSprites;
    private final ArrayList<Sprite> staySprites;
    private final ArrayList<Sprite> jumpSprites;
    private final ArrayList<Sprite> fallSprites;
    private final ArrayList<Sprite> attackSprites;
    private final ArrayList<Sprite> deathSprites;
    private final ArrayList<Sprite> getDamageSprites;


    public Hero() throws IOException {

        isMoving = false;
        state = State.STAY;
        attackFrequency = 3000;
        hitRange = 150;
        isLive = true;
        hitForce = 400;
        health = maxHealth = 2500;

        getDamageSprites = new ArrayList<>();
        deathSprites = new ArrayList<>();
        staySprites = new ArrayList<>();
        runSprites = new ArrayList<>();
        jumpSprites = new ArrayList<>();
        fallSprites = new ArrayList<>();
        attackSprites = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            attackSprites.add(new Sprite("src/res/heroSprites/attack/Attack" + i + ".png"));
            deathSprites.add(new Sprite("src/res/heroSprites/death/Death" + i + ".png"));
        }
        for (int i = 1; i < 9; i++) {
            runSprites.add(new Sprite("src/res/heroSprites/run/run" + i + ".png"));
        }
        for (int i = 1; i < 11; i++) {
            staySprites.add(new Sprite("src/res/heroSprites/idle/Idle" + i + ".png"));
        }
        for (int i = 1; i < 4; i++) {
            getDamageSprites.add(new Sprite("src/res/heroSprites/getDamage/GetDamage" + i + ".png"));
            jumpSprites.add(new Sprite("src/res/heroSprites/jump/Jump" + i + ".png"));
            fallSprites.add(new Sprite("src/res/heroSprites/fall/Fall" + i + ".png"));
        }
        height = staySprites.get(0).height;
        width = staySprites.get(0).width;
    }

    @Override
    public ArrayList<Sprite> getSprites() {
        if (state == State.STAY) {
            return staySprites;
        }
        if (state == State.RUN) {
            return runSprites;
        }
        if (state == State.JUMP) {
            return jumpSprites;
        }
        if (state == State.FALL) {
            return fallSprites;
        }
        if (state == State.ATTACK) {
            return attackSprites;
        }
        if (state == State.GET_DAMAGE) {
            return getDamageSprites;
        }
        if (state == State.DEATH) {
            return deathSprites;
        }
        return staySprites;
    }

    @Override
    public void setState(State state) throws IOException {
        if (state == State.DEATH) {
            this.state = State.DEATH;
            Canvas.getInstance().setIndexHero(0);
        }
        if (!(this.state == State.JUMP) || (this.state == State.JUMP && state == State.FALL)) {
            if (!(this.state == State.FALL) && this.state != state) {
                this.state = state;
                Canvas.getInstance().setIndexHero(0);
            }
        }
    }

    public void attack(Object object) throws IOException {
        if ((lastAttackTime == 0 || System.currentTimeMillis() - lastAttackTime >= attackFrequency) && distance(this, object) <= hitRange) {
            lastAttackTime = System.currentTimeMillis();
            object.getDamage(hitForce);
        }
    }

    public void move() {
        if (isMoving) {
            if (isLeft) {
                cord.x -= speedX;
            } else {
                cord.x += speedX;
            }
        }
        if (state == State.JUMP) {
            cord.y -= speedY;
            if (cord.y - height * sizeCoefficient < jumpHeight) {
                state = State.FALL;
            }
        }
        if (state == State.FALL) {
            cord.y += speedY * 2;
            if (cord.y - height > defaultY) {
                if (speedX != 0) {
                    state = State.RUN;
                } else state = State.STAY;
                cord.y = defaultY;
            }
        }
        if (this.state != State.FALL && this.state != State.JUMP && getY() != defaultY) {
            cord.y = defaultY;
        }
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    @Override
    public void update(int width, int height) {
        setJumpHeight(height * 10 / 100);

        setSizeCoefficient((height + width) / 350);
        speedX = width / 45;
        speedY = height / 20;
        int x = width * 10 / 100 + this.width;
        int y = height * 90 / 100 - 40;
        if (cord == null) {
            setCord(new Cord(x, y));
        }
        setDefaultY(y);
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
