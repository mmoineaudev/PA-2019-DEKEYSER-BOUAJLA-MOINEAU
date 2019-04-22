package m1_miage.abstraction;


import m1_miage.presenter.GameBoard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 * cette classe doit etre surchargée pour crée un objet de jeu, qu'on pourra animer
 */
public abstract class BasicSprite {
	protected double x;
	protected double y;

	public BasicSprite(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}


	/**
	 * Cette méthode permet d'animer un objet de jeu
	 * @param time
	 * @param b
	 */
	public abstract void update(double time, GameBoard b);

	public abstract Shape getBoundingShape() ;
	
	public abstract void render(GraphicsContext gc);
	
	public abstract void handleCollision(GameBoard b, BasicSprite p);

	/*
	 *  getters/setters
	 */

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
