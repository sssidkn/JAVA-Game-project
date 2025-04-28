package Build;


import Contorller.State;
import Model.Enemy;
import Model.Sprite;
import View.Canvas;

import java.io.IOException;
import java.util.ArrayList;

public class DemonBuilder implements EnemyBuilder {
    private Enemy enemy;

    @Override
    public EnemyBuilder setSettings() throws IOException {
        enemy.setHealth(2000);
        enemy.setMaxHealth(1500);
        enemy.setHitForce(130);
        enemy.setHitRange(200);
        enemy.setAttackFrequency(6000);
        enemy.setState(State.STAY);
        return this;
    }

    @Override
    public EnemyBuilder setSprites() throws IOException {
        enemy = new Enemy();
        ArrayList<Sprite> spritesBuffer = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            spritesBuffer.add(new Sprite("src/res/enemySprites/demon/idle/demon-idle" + i + ".png"));
        }
        enemy.setHeight(spritesBuffer.get(0).height);
        enemy.setWidth(spritesBuffer.get(0).width);

        enemy.setMoveSprites((ArrayList<Sprite>) spritesBuffer.clone());
        spritesBuffer.clear();
        for (int i = 1; i < 12; i++) {
            spritesBuffer.add(new Sprite("src/res/enemySprites/demon/attack/demon-attack" + i + ".png"));
        }
        enemy.setAttackSprites((ArrayList<Sprite>) spritesBuffer.clone());
        spritesBuffer.clear();

        for (int i = 1; i < 6; i++) {
            spritesBuffer.add(new Sprite("src/res/enemySprites/demon/attack/demon-attack" + i + ".png"));
        }
        enemy.setDeathSprites(spritesBuffer);
        return this;
    }

    @Override
    public Enemy build() throws IOException {
        return enemy;
    }
}
