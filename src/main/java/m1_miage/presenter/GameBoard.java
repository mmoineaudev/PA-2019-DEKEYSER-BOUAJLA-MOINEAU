package m1_miage.presenter;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import m1_miage.abstraction.SpriteProvider;
import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.Score;
import m1_miage.abstraction.game_objects.VaisseauSprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * L'objet qui contient le jeu, la carte
 */
public class GameBoard {

	private int width;
	private int height;
	private SpriteProvider spriteProvider;
	private HashMap<String, Score> scoresPerVaisseau;

	public GameBoard(int width, int height) {
		super();
		scoresPerVaisseau=new HashMap<>();
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
		updateScores(spriteProvider.removeTheDead());
		spriteProvider.removeLostSprites(this);
		if(!partyIsOver()) {
			displayNumberOfSprites(graphicsContext);
			Iterator<IntelligentSprite> it = spriteIterator();
			while (it.hasNext()) {
				IntelligentSprite s = it.next();
				s.update(t, this);
				spriteProvider.checkForCollision(s, this);
				s.render(graphicsContext);
			}
		}else{
			displayScores(graphicsContext);
		}

	}

	private void updateScores(ArrayList<IntelligentSprite> deadSprites) {
		for(IntelligentSprite s : deadSprites){
			if(s instanceof VaisseauSprite)
				scoresPerVaisseau.put(s.getId(), s.getScore());
		}

	}

	private void displayScores(GraphicsContext graphicsContext) {
	    if(scoresPerVaisseau!=null && !scoresPerVaisseau.isEmpty()) {
            Paint save = graphicsContext.getFill();
            graphicsContext.setFill(Color.color(Math.random(), Math.random(), Math.random()));
            Font saveFont = graphicsContext.getFont();
            Font BIG = new Font(saveFont.getName(), 50 / scoresPerVaisseau.size());
            graphicsContext.setFont(BIG);
            graphicsContext.strokeText("Partie terminée !", 30, 30);
            int x = width / 4;
            int y = height / 4;
            for (String id : scoresPerVaisseau.keySet()) {
                graphicsContext.strokeText("* " + id + " " + scoresPerVaisseau.get(id), x, y);
                y += height / scoresPerVaisseau.size();
            }
            graphicsContext.setFill(save);
            graphicsContext.setFont(saveFont);
            graphicsContext.save();
        }
	}

	/**
	 * @return true si le famebord ne contient plus de vaisseau
	 */
	public boolean partyIsOver() {
		return !spriteProvider.containsVaisseau();
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

	public SpriteProvider getSpriteProvider() {
		return this.spriteProvider;
	}

	@Override
	public String toString() {
		return "GameBoard{" +
				"width=" + width +
				", height=" + height +
				", spriteProvider=" + spriteProvider +
				'}';
	}
}


