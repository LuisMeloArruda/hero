import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;


public class Game {
    private int horizontal = 10;
    private int vertical = 10;
    private Screen screen = null;
    Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();

            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                vertical--;
                break;
            case ArrowDown:
                vertical++;
                break;
            case ArrowRight:
                horizontal++;
                break;
            case ArrowLeft:
                horizontal--;
                break;
        }
    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(horizontal, vertical, TextCharacter.fromCharacter('X')[0]);
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
            processKey(key);
        }
    }
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }
}
