import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.ui.Button;
public class Game {
    private static final int CANVAS_WIDTH = 1400;
    private static final int CANVAS_HEIGHT = 800;
    private CanvasWindow canvas;

    public Game(){
        canvas = new CanvasWindow("Blackjack (aka 21)", CANVAS_WIDTH, CANVAS_HEIGHT);

    }

    public static void main(String[] args) {
        Game game = new Game();
    }
}
