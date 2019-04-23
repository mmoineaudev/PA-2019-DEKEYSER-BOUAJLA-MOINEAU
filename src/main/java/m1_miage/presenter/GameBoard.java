package m1_miage.presenter;

import javafx.scene.canvas.GraphicsContext;
import m1_miage.abstraction.BasicSprite;
import m1_miage.abstraction.SpriteProvider;

import java.util.Iterator;

/**
 * L'objet qui contient le jeu
 */
public class GameBoard {

	private int width;
	private int height;
	public SpriteProvider spriteProvider;

	public GameBoard(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		spriteProvider = new SpriteProvider();
	}


	public void addSprite(BasicSprite p) {
		spriteProvider.add(p);
	}

	public Iterator<BasicSprite> spriteIterator() {
		return spriteProvider.iterator();
	}

	/**
	 * permet d'animer tous les sprites 1 fois chacun
	 *
	 * @param t               le temps d'une opération ?
	 * @param graphicsContext
	 */
	public void animate(double t, GraphicsContext graphicsContext) {
		spriteProvider.removeTheDead();
		Iterator<BasicSprite> it = spriteIterator();
		while (it.hasNext()) {
			BasicSprite s = it.next();
			s.update(t, this);
			spriteProvider.checkForCollision(s, this);
			s.render(graphicsContext);
		}
	}


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


}


