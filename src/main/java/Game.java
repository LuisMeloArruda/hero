import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalSize;


import java.io.IOException;


public class Game {
    Hero hero = new Hero(new Position(10,10));
    Arena arena = new Arena(50, 20, hero);
    private Screen screen = null;
    Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(arena.getWidth(), arena.getHeight());
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen);
        screen.refresh();
    }
    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getCharacter() != null) {
                if (key.getCharacter() == 'q') {
                    screen.close();
                    break;
                }
            }
            if (key.getKeyType() == KeyType.EOF) {
                break;
            }
            arena.processKey(key);
        }
    }
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }
}
