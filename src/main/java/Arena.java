import Elemento.Coin;
import Elemento.Hero;
import Elemento.Monster;
import Elemento.Wall;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Elemento.*;

public class Arena {
    private final int width;
    private final int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height, Hero hero, List<Wall> walls, List<Coin> coins, List<Monster> monsters) {
        this.width = width;
        this.height = height;
        this.hero = hero;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(new Position(c, 0)));
            walls.add(new Wall(new Position(c, height - 1)));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(new Position(0, r)));
            walls.add(new Wall(new Position(width - 1, r)));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1)));
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            monsters.add(new Monster(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1)));
        return monsters;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#54269B"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(getWidth(), getHeight()), ' ');

        for (Wall wall : walls)
            wall.draw(graphics);

        for (Coin coin : coins)
            coin.draw(graphics);

        for (Monster monster : monsters)
            monster.draw(graphics);
    }

    private Boolean canHeroOrMonsterMove(Position position) {
        for (Wall wall : walls) {
            if (position.equals(wall.getPosition())) {
                return false;
            }
        }
        return (0 <= position.getHorizontal()) && (position.getHorizontal() < width) && (0 <= position.getVertical()) && (position.getVertical() < height);
    }

    private void moveHero(Position position) {
        if (canHeroOrMonsterMove(position)) {
            retrieveCoins(position);
            hero.setPosition(position);
            verifyMonsterCollisions(position);
        }
    }

    public void verifyMonsterCollisions(Position position) {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(position)) {
                System.out.println("GAME OVER");
                System.exit(0);
            }
        }
    }

    public void moveMonsters() {
        Position position;
        for (Monster monster : monsters) {
            position = monster.move();
            if (canHeroOrMonsterMove(position)) {
                monster.setPosition(position);
            }
        }
    }

    void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
        }
    }

    public void retrieveCoins(Position position) {
        Iterator<Coin> iterator = coins.iterator();
        while (iterator.hasNext()) {
            Coin coin = iterator.next();
            if (coin.getPosition().equals(position)) {
                iterator.remove();
            }
        }
    }
}
