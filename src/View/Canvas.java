package View;

import Contorller.State;
import Model.*;
import Model.Object;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Canvas extends JPanel {
    private int indexHero = 0;
    private int indexEnemy = 0;
    public Hero hero;
    private static Canvas instance = null;
    private Image bg;
    private Enemy enemy;

    Canvas() throws IOException {
        super(true);
        bg = ImageIO.read(new File("src/res/Background.png"));
    }

    public static Canvas getInstance() throws IOException {
        if (instance == null) {
            instance = new Canvas();
            instance.setFocusable(false);
        }
        return instance;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        try {
            drawEnemy(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        drawHero(g);
    }

    public void drawEnemy(Graphics g) throws IOException {
        if (enemy != null && enemy.isLive()) {
            Sprite sprite = enemy.getSprites().get(indexEnemy % enemy.getSprites().size());
            if (!enemy.isLeft()) {
                sprite = sprite.flipHorizontal();
            }

            enemy.setWidth(sprite.width);
            enemy.setHeight(sprite.height);

            sprite.draw(g, enemy.getCord(), enemy.getWidth() * enemy.getSizeCoefficient(),
                    enemy.getHeight() * enemy.getSizeCoefficient());
            if (enemy.getState() == State.DEATH && indexEnemy % enemy.getSprites().size() == enemy.getSprites().size() - 1) {
                enemy.setLive(false);
            }
            if (enemy.getClass() == BossEnemyAdapter.class && enemy.isLive()) {
                if (enemy.getState() == State.ATTACK && indexEnemy % enemy.getSprites().size() == enemy.getSprites().size() - 1) {
                    enemy.setState(State.RUN);
                }
                for (BossEnemy.FireBall fireBall : enemy.getFireBalls()) {
                    fireBall.draw(g);
                }
            }
            drawHealthBar(g, enemy);
            indexEnemy += 1;
        }
    }

    public void drawHero(Graphics g) {
        if (hero != null && hero.isLive()) {
            Sprite sprite = hero.getSprites().get(indexHero % hero.getSprites().size());
            if (hero.isLeft()) {
                sprite = sprite.flipHorizontal();
            }

            hero.setHeight(sprite.height);
            hero.setWidth(sprite.width);

            if (hero.getState() == State.ATTACK && indexHero % hero.getSprites().size() == hero.getSprites().size() - 1) {
                try {
                    hero.setState(State.STAY);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (hero.getState() == State.DEATH && indexHero % hero.getSprites().size() == hero.getSprites().size() - 1) {
                hero.setLive(false);
            }
            drawHealthBar(g, hero);
            sprite.draw(g, hero.getCord(), hero.getWidth() * hero.getSizeCoefficient(), hero.getHeight() * hero.getSizeCoefficient());
            indexHero += 1;
        }
    }

    public void drawHealthBar(Graphics g, Object object) {
        int widthK = 0;
        if (object.getClass() == Hero.class) {
            g.setColor(Color.blue);
            widthK = 5;
        }
        if (object.getClass() == Enemy.class || object.getClass() == BossEnemyAdapter.class) {
            g.setColor(Color.red);
            widthK = 65;
        }

        g.fillRect((getWidth() * widthK) / 100, getHeight() * 5 / 100, (int) ((double) object.getHealth() / (double) object.getMaxHealth() * 100) * 2, getHeight() / 100);

    }

    public void addHero(Hero hero) {
        this.hero = hero;
    }

    public void addEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public void setIndexHero(int indexHero) {
        this.indexHero = indexHero;
    }

    public void setIndexEnemy(int indexEnemy) {
        this.indexEnemy = indexEnemy;
    }
}
