package m1_miage.presenter;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import m1_miage.abstraction.SpriteProvider;
import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.VaisseauSprite;

import java.util.Iterator;

/**
 * L'objet qui contient le jeu, la carte
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

	/**
	 * Ajoute un objet a l'affichage
	 * @param p
	 */
	public void addSprite(IntelligentSprite p) {
		spriteProvider.add(p);
	}

	/**
	 * @return un iterator sur tous les objets affichés
	 */
	public Iterator<IntelligentSprite> spriteIterator() {
		return spriteProvider.iterator();
	}

	/**
	 * permet d'animer tous les sprites 1 fois chacun
	 *
	 * @param t               le temps d'une opération ?
	 * @param graphicsContext
	 */
	public void animate(double t, GraphicsContext graphicsContext) {
		spriteProvider.addShots();
		spriteProvider.removeTheDead();
		spriteProvider.removeLostSprites(this);
		displayNumberOfSprites(graphicsContext);
		displayEPILEPSY(graphicsContext);
		Iterator<IntelligentSprite> it = spriteIterator();
		while (it.hasNext()) {
			IntelligentSprite s = it.next();
			s.update(t, this);
			spriteProvider.checkForCollision(s, this);
			s.render(graphicsContext);
		}
	}

	/**
	 * Affiche un compteur d'éléments dans le gameboard a chaque frame
	 * @param graphicsContext
	 */
	private void displayNumberOfSprites(GraphicsContext graphicsContext) {
		Paint save = graphicsContext.getFill();
		graphicsContext.setFill(Color.RED);
		graphicsContext.strokeText("NumberOFSprites: "+spriteProvider.getLength(),10, 30);
		graphicsContext.setFill(save);
		graphicsContext.save();
	}
	private void displayEPILEPSY(GraphicsContext graphicsContext) {
		Paint save = graphicsContext.getFill();
		graphicsContext.setFill(Color.color(Math.random(),Math.random(),Math.random()));
		Font saveFont = graphicsContext.getFont();
		Font BIG = new Font(saveFont.getName(), 30.);
		graphicsContext.setFont(BIG);
		for(int i=0; i<getWidth(); i+=30) {
			double shake = Math.random()*30;
			graphicsContext.strokeText("EPILEPSY",i+200+shake, i+shake);
			graphicsContext.strokeText("EPILEPSY",i+400+shake, i+shake);
			graphicsContext.strokeText("EPILEPSY",i-200+shake, i+shake);
			graphicsContext.strokeText("EPILEPSY",i-400+shake, i+shake);

			graphicsContext.strokeText("EPILEPSY",i+shake, i+shake);

		}
		graphicsContext.setFill(save);
		graphicsContext.setFont(saveFont);
		graphicsContext.save();
	}

	/**
	 * permet a un vaisseau de savoir s'il doit tirer
	 * @param vaisseauSprite
	 * @return true si un enemy est en face
	 *///TODO TEST
	public boolean facesAnEnemy(VaisseauSprite vaisseauSprite) {
		Iterator<IntelligentSprite> it = spriteIterator();
		int delta = 15;
		while (it.hasNext()) {
			IntelligentSprite s = it.next();
			if(!s.equals(vaisseauSprite)){
				switch (vaisseauSprite.getDirection()){
					case NORTH:
						if(s.getY()<vaisseauSprite.getY() && vaisseauSprite.getX()-delta<s.getX() && vaisseauSprite.getX()+delta>s.getX())
							return true;
					case SOUTH:
						if(s.getY()>vaisseauSprite.getY() && vaisseauSprite.getX()-delta<s.getX() && vaisseauSprite.getX()+delta>s.getX())
							return true;
					case EAST:
						if(s.getX()>vaisseauSprite.getX() && vaisseauSprite.getY()-delta<s.getY() && vaisseauSprite.getY()+delta>s.getY())
							return true;
					case WEST:
						if(s.getX()<vaisseauSprite.getX() && vaisseauSprite.getY()-delta<s.getY() && vaisseauSprite.getY()+delta>s.getY())
							return true;
				}
			}
		}
		return false;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}


