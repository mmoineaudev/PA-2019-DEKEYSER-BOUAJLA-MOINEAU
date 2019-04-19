package m1_miage.presenter;

import javafx.scene.canvas.GraphicsContext;
import m1_miage.abstraction.Sprite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * L'objet qui contient le jeu
 */
public class GameBoard {

	private int width;
	private int height;

	private ArrayList<Sprite> list = new ArrayList<>();

	public GameBoard(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	
	public void addSprite(Sprite p) {
		this.list.add(p);
	}
	
	public Iterator<Sprite> spriteIterator() {
		return list.iterator();
	}

	/**
	 * permet d'animer tous les sprites 1 fois chacun
	 * @param t le temps d'une opération ?
	 * @param graphicsContext
	 */
	public void animate(double t, GraphicsContext graphicsContext) {
		Iterator<Sprite> it = spriteIterator();
		while (it.hasNext()) {
			Sprite s = it.next();
			s.update(t, this);
			checkForCollision(s);
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

	public void checkForCollision(Sprite s) {
		Iterator<Sprite> it = spriteIterator();
		while (it.hasNext()) {
			Sprite d = it.next();
			if (d != s) {
				if (s.getBoundingShape().getBoundsInParent().intersects(d.getBoundingShape().getBoundsInParent())) {
					System.out.println(" it's a crash !!!");
					s.handleCollision(this, d);
				}
			}
		}

	}


	public void removeSprite(Sprite p) {
		Iterator<Sprite> it = spriteIterator();
		while (it.hasNext()) {
			Sprite d = it.next();
			if(d.equals(p)) {
				it.remove();
			}
		}
	}
}
