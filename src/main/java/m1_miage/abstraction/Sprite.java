package m1_miage.abstraction;


import m1_miage.presenter.GameBoard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 * cette classe doit etre surchargée pour crée un objet de jeu, qu'on pourra animer
 */
public abstract class Sprite {
	protected double x;
	protected double y;
	protected double speedX;
	protected double speedY;

	public Sprite(double x, double y, double speedX, double speedY) {
		super();
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY; 
	}

	/**
	 * Cette méthode permet d'animer un objet de jeu
	 * @param time
	 * @param b
	 */
	public void update(double time, GameBoard b) {
		x += speedX * time;
		y += speedY * time;
	}

	public abstract Shape getBoundingShape() ;
	
	public abstract void render(GraphicsContext gc);
	
	public abstract void handleCollision(GameBoard b, Sprite p);

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

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speed) {
		this.speedX = speed;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speed) {
		this.speedY = speed;
	}

}
