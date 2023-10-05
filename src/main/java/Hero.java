import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero {
    private Position position;
    public Hero(Position position) {
        this.position = position;
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

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getHorizontal(), position.getVertical()), "H");
    }
}

