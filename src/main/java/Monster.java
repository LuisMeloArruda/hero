import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.Random;

public class Monster extends Element {
    public Monster(Position position) {
        super(position);
    }
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getHorizontal(), getPosition().getVertical()), "+");
    }

    public Position move() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0: //cima
                return new Position(getPosition().getHorizontal(), getPosition().getVertical()-1);
            case 1: //baixo
                return new Position(getPosition().getHorizontal(), getPosition().getVertical()+1);
            case 2: //direita
                return new Position(getPosition().getHorizontal()+1, getPosition().getVertical());
            case 3: //esquerda
                return new Position(getPosition().getHorizontal()-1, getPosition().getVertical());

            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
    }
}
