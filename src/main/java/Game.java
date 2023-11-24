import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalSize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Game {
    Hero hero = new Hero(new Position(10,10));

    List<Wall> walls = new ArrayList<>();
    List<Coin> coins = new ArrayList<>();

    List<Monster> monsters = new ArrayList<>();

    Arena arena = new Arena(50, 20, hero, walls, coins, monsters);
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
            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        hero.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            arena.moveMonsters();
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
