import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private final int width;
    private final int height;
    private Hero hero;

    public Arena(int width, int height, Hero hero) {
        this.width = width;
        this.height = height;
        this.hero = hero;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Screen screen) {
        screen.setCharacter(hero.getPosition().getHorizontal(), hero.getPosition().getVertical(), TextCharacter.fromCharacter('X')[0]);
    }

    private Boolean canHeroMove(Position position) {
        return ((0 <= position.getHorizontal()) && (position.getHorizontal() < width) && (0 <= position.getVertical()) && (position.getVertical() < height));
    }
    private void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
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
}
