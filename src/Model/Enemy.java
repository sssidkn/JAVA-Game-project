package Model;

import Contorller.State;
import View.Canvas;

import java.io.IOException;
import java.util.ArrayList;

public class Enemy extends Object {
    private ArrayList<Sprite> attackSprites;
    private ArrayList<Sprite> deathSprites;
    private ArrayList<Sprite> moveSprites;

    public void move() {
        cord.x += speedX;
        if (cord.y != defaultY) {
            cord.y = defaultY;
        }
    }

    public void setMoveSprites(ArrayList<Sprite> moveSprites) {
        this.moveSprites = moveSprites;
    }

    public void setAttackSprites(ArrayList<Sprite> attackSprites) {
        this.attackSprites = attackSprites;
    }

    public void setDeathSprites(ArrayList<Sprite> deathSprites) {
        this.deathSprites = deathSprites;
    }


    public ArrayList<Sprite> getSprites() {
        if (state == State.ATTACK) {
            return attackSprites;
        }
        if (state == State.RUN) {
            return moveSprites;
        }
        if (state == State.DEATH) {
            return deathSprites;
        }
        return moveSprites;
    }

    @Override
    public void setState(State state) throws IOException {
        this.state = state;
        Canvas.getInstance().setIndexEnemy(0);
    }

    public void attack(Object object) throws IOException {
        if (lastAttackTime == 0 || System.currentTimeMillis() - lastAttackTime >= attackFrequency) {
            lastAttackTime = System.currentTimeMillis();
            object.getDamage(hitForce);
            state = State.ATTACK;
        }
    }

    @Override
    public void update(int width, int height) throws IOException {
        setSizeCoefficient((height + width) / 500);
        speedX = width / 100;
        int x = width * 90 / 100;
        int y = height * 90 / 100 - 40;
        if (cord == null) {
            setCord(new Cord(x, y));
        }
        setDefaultY(y);
    }

    public ArrayList<BossEnemy.FireBall> getFireBalls() {
        return null;
    }
}