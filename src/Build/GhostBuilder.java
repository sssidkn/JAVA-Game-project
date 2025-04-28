package Build;

import Contorller.State;
import Model.Enemy;
import Model.Sprite;

import java.io.IOException;
import java.util.ArrayList;

public class GhostBuilder implements EnemyBuilder {
    private Enemy enemy;

    @Override
    public EnemyBuilder setSettings() throws IOException {
        enemy.setHealth(1500);
        enemy.setMaxHealth(1500);
        enemy.setHitForce(150);
        enemy.setHitRange(100);
        enemy.setAttackFrequency(10000);
        enemy.setState(State.STAY);
        return this;
    }

    @Override
    public EnemyBuilder setSprites() throws IOException {
        enemy = new Enemy();
        ArrayList<Sprite> spritesBuffer = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            spritesBuffer.add(new Sprite("src/res/enemySprites/ghost/idle/ghost-idle" + i + ".png"));
        }

        enemy.setHeight(spritesBuffer.get(0).height);
        enemy.setWidth(spritesBuffer.get(0).width);

        enemy.setMoveSprites((ArrayList<Sprite>) spritesBuffer.clone());
        spritesBuffer.clear();

        for (int i = 1; i < 5; i++) {
            spritesBuffer.add(new Sprite("src/res/enemySprites/ghost/attack/ghost-shriek" + i + ".png"));
        }
        enemy.setAttackSprites((ArrayList<Sprite>) spritesBuffer.clone());
        spritesBuffer.clear();

        for (int i = 1; i < 8; i++) {
            spritesBuffer.add(new Sprite("src/res/enemySprites/ghost/death/ghost-death" + i + ".png"));
        }
        enemy.setDeathSprites((ArrayList<Sprite>) spritesBuffer.clone());
        spritesBuffer.clear();

        return this;
    }

    @Override
    public Enemy build() {
        return enemy;
    }
}
