import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element{
    public Hero(Position position) {
        super(position);
    }

    public Position moveUp() {
        return new Position(getPosition().getHorizontal(), getPosition().getVertical() - 1);
    }
    public Position moveDown() {
        return new Position(getPosition().getHorizontal(), getPosition().getVertical() + 1);
    }
    public Position moveRight() {
        return new Position(getPosition().getHorizontal() + 1, getPosition().getVertical());
    }
    public Position moveLeft() {
        return new Position(getPosition().getHorizontal() - 1, getPosition().getVertical());
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getHorizontal(), getPosition().getVertical()), "H");
    }
}

