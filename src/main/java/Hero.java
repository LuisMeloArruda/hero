import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int horizontal;
    private int vertical;
    public Hero(int horizontal_, int vertical_) {
        this.horizontal = horizontal_;
        this.vertical = vertical_;
    }
    public int getHorizontal() {
        return horizontal;
    }
    public int getVertical() {
        return vertical;
    }
    public void setHorizontal(int horizontal_) {
        this.horizontal = horizontal_;
    }
    public void setVertical(int vertical_) {
        this.vertical = vertical_;
    }
    public void moveUp() {
        vertical--;
    }
    public void moveDown() {
        vertical++;
    }
    public void moveRight() {
        horizontal++;
    }
    public void moveLeft() {
        horizontal--;
    }
    public void draw(Screen screen) {
        screen.setCharacter(horizontal, vertical, TextCharacter.fromCharacter('X')[0]);
    }
}
