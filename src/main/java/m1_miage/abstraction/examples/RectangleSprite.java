package m1_miage.abstraction.examples;

import java.util.Random;

import m1_miage.abstraction.Sprite;
import m1_miage.presenter.GameBoard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * On garde les classes exemples pour s'en servir de modele, mais il faut les enlever avant de merge
 */
public class RectangleSprite extends Sprite {
	private int w =20;
	private int h = 20;
	
	private Color[] colors = new Color[] { Color.RED, Color.CYAN, Color.DARKCYAN };
	private int currentColor = 0; 
	
	private Random r = new Random();
	
	public RectangleSprite(double x, double y) {
		super(x, y);
	}
	

	@Override
	public void update(double time, GameBoard b) {
		if ((this.x+w)>b.getWidth() || this.x < 0) {
			//this.speedX=-this.speedX;
		}
		if ((this.y+h)>b.getWidth() || this.y < 0) {
			//this.speedY=-this.speedY;
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		Paint save = gc.getFill();
		System.out.println(" colors " + colors[currentColor] );
		gc.setFill(colors[currentColor]);
		gc.fillRect(x, y, w, h);
		
		gc.setFill(save);
	}

	@Override
	public void handleCollision(GameBoard b, Sprite p) {
		currentColor =  r.nextInt(colors.length); 
	}

	@Override
	public Shape getBoundingShape() {
		return new Rectangle(x,y,w,h);
		
	}

}
