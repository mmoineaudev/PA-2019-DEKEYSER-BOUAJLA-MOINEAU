package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import m1_miage.presenter.GameBoard;

/**
 * Le futur objet de jeu : asteroid
 */
public class AsteroidSprite extends IntelligentSprite {
    private final int max_speed = 100;
    private int diameter = (int) (Math.random()*30);

    public AsteroidSprite(double x, double y) {
        super(x, y);
        this.setSpeed(Math.random()*max_speed);
        lifes = 1;
        if(diameter<10) diameter=10;
    }

    @Override
    public void update(double time, GameBoard b) {
        setSpeed(getSpeed()+time);
        super.update(time, b);
    }

    @Override
    public Shape getBoundingShape() {
        if(isDead()) return null;
        return new Rectangle(x, y, diameter,diameter);//en cohérence avec drawHitbox
    }

    @Override
    public void render(GraphicsContext gc) {
        if(!isDead()) {
            Paint save = gc.getFill();
            gc.setFill(Color.DARKSLATEGRAY);
            gc.strokeOval(x, y, diameter, diameter);
            gc.fillOval(x, y, diameter, diameter);
            gc.setFill(save);
        }
        //permet de savoir visuellement si les AsteroidSprites morts on bien été retirés de la liste de sprites par
        //{@link SpriteProvider#removeLostSprites}
        drawHitBox(gc);
    }

    @Override
    public void handleCollision(GameBoard b, IntelligentSprite p) {
        super.handleCollision(b,p);
    }

    /**
     * Permet de débugger les problèmes de collision
     * @param gc
     */
    private void drawHitBox(GraphicsContext gc) {
        Paint save = gc.getFill();
        Paint saveStroke = gc.getStroke();
        gc.setStroke(Color.WHITE);
        gc.strokeRect(x,y, diameter,diameter);
        gc.setFill(save);
        gc.setStroke(saveStroke);
    }


}
