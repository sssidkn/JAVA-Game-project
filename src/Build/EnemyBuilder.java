package Build;

import Model.Enemy;
import java.io.IOException;

public interface EnemyBuilder {
    public EnemyBuilder setSettings() throws IOException;

    public EnemyBuilder setSprites() throws IOException;

    public Enemy build() throws IOException;
}
