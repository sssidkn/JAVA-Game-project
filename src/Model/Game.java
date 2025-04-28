package Model;

import Build.DemonBuilder;
import Build.EnemyBuilder;
import Build.EnemyMaker;
import Build.GhostBuilder;
import Contorller.State;
import View.Canvas;
import View.MainWindow;

import java.io.IOException;
import java.util.ArrayList;

import static Tools.Func.distance;
import static java.lang.Math.abs;

public class Game extends Thread {
    private ArrayList<Object> objects;
    private static Hero hero;
    private Enemy enemy;
    private Enemy demon;
    private Enemy ghost;
    private BossEnemyAdapter boss;
    private static boolean isRunning;
    private int enemyIndex = 1;
    private MainWindow window;

    public Game() throws IOException {
        newGame();
    }

    public void newGame() throws IOException {
        hero = new Hero();
        Canvas.getInstance().addHero(hero);

        EnemyMaker enemyMaker = new EnemyMaker();
        ghost = enemyMaker.makeEnemy(new GhostBuilder());

        EnemyBuilder enemyBuilder = new DemonBuilder();
        demon = enemyMaker.makeEnemy(enemyBuilder);

        BossEnemy bossEnemy = new BossEnemy();
        boss = new BossEnemyAdapter(bossEnemy);
        enemy = ghost;
        Canvas.getInstance().addEnemy(enemy);

        objects = new ArrayList<>();

        objects.add(hero);
        objects.add(ghost);
        objects.add(demon);
        objects.add(boss);

        isRunning = false;
    }

    public void startGame() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                heroMoving();
                enemyMoving();
                Canvas.getInstance().repaint();
                Thread.sleep(100);
            } catch (InterruptedException | IOException e) {
                interrupt();
            }
        }
    }

    public void heroMoving() throws IOException {
        if (hero.isLive()) {
            hero.move();
            if (hero.getState() == Contorller.State.ATTACK && distance(enemy, hero) <= hero.getHitForce()) {
                hero.attack(enemy);
            }
        } else {
            endGame();
        }
    }

    public void enemyMoving() throws IOException {
        if (enemy.state != Contorller.State.DEATH) {
            if (hero.getX() < enemy.getX() - enemy.getWidth()) {
                if (enemy.getSpeedX() >= 0) {
                    enemy.setLeft(true);
                    enemy.setState(Contorller.State.RUN);
                    enemy.setSpeedX(-enemy.getSpeedX());
                }
            }
            if (hero.getX() - hero.getWidth() > enemy.getX()) {
                if (enemy.getSpeedX() <= 0) {
                    enemy.setLeft(false);
                    enemy.setState(Contorller.State.RUN);
                    enemy.setSpeedX(-enemy.getSpeedX());
                }
            }
            enemy.move();
            if (distance(enemy, hero) <= enemy.getHitRange() || enemy.getClass() == BossEnemyAdapter.class) {
                enemy.attack(hero);
            }
            if (enemy.getClass() == BossEnemyAdapter.class) {
                for (BossEnemy.FireBall fireBall : enemy.getFireBalls()) {
                    fireBall.update();
                    if (abs(hero.cord.x - fireBall.getCord().x - fireBall.getWidth()) <= 20 && hero.cord.y > fireBall.getCord().y) {
                        hero.getDamage(fireBall.getDamage());
                    }
                }
            }
        }
        if (!enemy.isLive()) {
            enemyIndex += 1;
            if (enemyIndex < objects.size()) {
                setEnemy((Enemy) objects.get(enemyIndex));
            } else {
                enemy = null;
                endGame();
            }
        }
    }

    public void endGame() throws IOException {
        isRunning = false;
        interrupt();
        if (hero.isLive) {
            window.showMessageMenu("Win");
        } else {
            window.showMessageMenu("Lose");
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public static Hero getHero() {
        return hero;
    }

    public static void setHero(Hero hero) {
        Game.hero = hero;
    }

    public void setEnemy(Enemy enemy) throws IOException {
        this.enemy = enemy;
        Canvas.getInstance().addEnemy(enemy);
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void addWindow(MainWindow window) {
        this.window = window;
    }
}
