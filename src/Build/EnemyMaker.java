package Build;

import Model.Enemy;

import java.io.IOException;

public class EnemyMaker {
    public Enemy makeEnemy(EnemyBuilder enemyBuilder) throws IOException {
        return enemyBuilder.setSprites().setSettings().build();
    }
}
