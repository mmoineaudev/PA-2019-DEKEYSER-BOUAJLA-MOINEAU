package m1_miage.controler;

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
				if(s.getLifes()<=0)
					continue;//c'est moche mais au mons ca déclenchera pas les collisions engendrées par des IntelligentSprites morts
				else{
					s.update(t, this);
					spriteProvider.checkForCollision(s, this);
					s.render(graphicsContext);
				}
			}
		}else{
			displayScores(graphicsContext);
		}

	}

	/**
	 * Mets a jour les scores des sprites morts
	 * @param deadSprites
	 */
	private void updateScores(ArrayList<IntelligentSprite> deadSprites) {
		for(IntelligentSprite s : deadSprites){
			if(s instanceof VaisseauSprite)
				scoresPerVaisseau.put(s.getId(), s.getScore());
		}

	}

	/**
	 * Affiche un récapitulatif des scores a la fin de la partie
	 * @param graphicsContext
	 */
	private void displayScores(GraphicsContext graphicsContext) {
	    if(scoresPerVaisseau!=null && !scoresPerVaisseau.isEmpty()) {
            Paint save = graphicsContext.getStroke();
            graphicsContext.setStroke(Color.WHITE);
            Font saveFont = graphicsContext.getFont();
            int size = (int) (this.getHeight()/(scoresPerVaisseau.size()*2.5));
            if(size>40)size=40;
            if(size<8)size=8;
            Font BIG = new Font(saveFont.getName(), size-2);
            graphicsContext.setFont(BIG);
            graphicsContext.strokeText("Partie terminée !", 30, size);
            int x = width/5;
            int y = 2*size;
			String idMax = findMax(scoresPerVaisseau);
			String idSurvivor = findSurvivor(scoresPerVaisseau);
            for (String id : scoresPerVaisseau.keySet()) {
                graphicsContext.strokeText(
                		((id==idMax)?
								" * \tVAINQUEUR => "
								:" * "+((id==idSurvivor)?"\tSURVIVOR => ":""
						))
						+ id + " " + scoresPerVaisseau.get(id), x, y);
                y += 2*size;
            }
            graphicsContext.setStroke(save);
            graphicsContext.setFont(saveFont);
            graphicsContext.save();
        }
	}

	/**
	 * Trouve le vaisseau qui a le plus de points
	 * @param scoresPerVaisseau
	 * @return le vaisseau gagnant
	 */
	private String findMax(HashMap<String, Score> scoresPerVaisseau) {
		String max = "";
		int maxPoint = 0;
		for(String id : scoresPerVaisseau.keySet()){
			if(maxPoint<scoresPerVaisseau.get(id).getPoints()){
				maxPoint=scoresPerVaisseau.get(id).getPoints();
				max=id;
			}
		}
		return max;
	}

	/**
	 * Trouve le vaisseau qui a survécu le plus longtemps
	 * @param scoresPerVaisseau
	 * @return le vaisseau survivant
	 */
	private String findSurvivor(HashMap<String, Score> scoresPerVaisseau) {
		String max = "";
		int maxPoint = 0;
		for(String id : scoresPerVaisseau.keySet()){
			if(maxPoint<scoresPerVaisseau.get(id).getTime()){
				maxPoint= (int) scoresPerVaisseau.get(id).getTime();
				max=id;
			}
		}
		return max;
	}

	/**
	 * @return true si le famebord ne contient plus de vaisseau
	 */
	public boolean partyIsOver() {
		return !spriteProvider.containsVaisseau();
	}

	/**
	 * @return true si le famebord ne contient plus de vaisseau
	 */
	public boolean ASingleOneIsLeft() {
		return spriteProvider.containsASingleVaisseau();
	}

	/**
	 * Affiche un compteur d'éléments dans le gameboard a chaque frame
	 * @param graphicsContext
	 */
	private void displayNumberOfSprites(GraphicsContext graphicsContext) {
		Paint save = graphicsContext.getStroke();
		graphicsContext.setStroke(Color.RED);
		graphicsContext.strokeText("NumberOFSprites: "+spriteProvider.getLength(),10, 30);
		graphicsContext.setStroke(save);
		graphicsContext.save();
	}

	/**
	 * permet a un vaisseau de savoir s'il doit tirer
	 * @param vaisseauSprite
	 * @return true si un enemy est en face
	 */
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


