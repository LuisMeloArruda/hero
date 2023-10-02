import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;
    public Hero(Position position) {
        this.position = position;
    }
    public void draw(Screen screen) {
        screen.setCharacter(position.getHorizontal(), position.getVertical(), TextCharacter.fromCharacter('X')[0]);
    }
    public Position moveUp() {
        return new Position(position.getHorizontal(), position.getVertical() - 1);
    }
    public Position moveDown() {
        return new Position(position.getHorizontal(), position.getVertical() + 1);
    }
    public Position moveRight() {
        return new Position(position.getHorizontal() + 1, position.getVertical());
    }
    public Position moveLeft() {
        return new Position(position.getHorizontal() - 1, position.getVertical());
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}

