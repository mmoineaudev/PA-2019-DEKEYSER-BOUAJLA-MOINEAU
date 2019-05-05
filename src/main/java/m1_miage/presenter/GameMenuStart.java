package m1_miage.presenter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Class which launches the game and associated classes
 */

public class GameMenuStart extends Application {
    private int width = 800;
    private int height = 800;
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private Stage stage;

    private GameGUI startGame = new GameGUI();

    @Override
    public void start(Stage stage) {
        initGame(stage);
    }

    private void initGame(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Asteroids Menu");
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        primaryStage.sizeToScene();

        Label guiLabel = new Label("GUI");
        guiLabel.setLayoutX(60);
        guiLabel.setLayoutY(50);

        Label pluggin1label = new Label("Pluggin1");
        pluggin1label.setLayoutX(160);
        pluggin1label.setLayoutY(70);

        Label pluggin2label = new Label("Pluggin2");
        pluggin2label.setLayoutX(360);
        pluggin2label.setLayoutY(70);

//        //Create menu items
//        MenuItem option1 = new MenuItem("option 1");
//        MenuItem option2 = new MenuItem("option 2");
//
//        plugginMenu.getItems().addAll(option1);
//        plugginMenu2.getItems().addAll(option2);
//
//        menuBar.getMenus().addAll(plugginMenu,plugginMenu2);


        Label joueursLabel = new Label("Joueurs");
        joueursLabel.setLayoutX(60);
        joueursLabel.setLayoutY(100);

        Label nomsJoueurLabel = new Label("Noms");
        nomsJoueurLabel.setLayoutX(60);
        nomsJoueurLabel.setLayoutY(120);

        Label mouvementsLabel = new Label("Mouvements");
        mouvementsLabel.setLayoutX(160);
        mouvementsLabel.setLayoutY(120);


        Label armesLabel = new Label("Armes");
        armesLabel.setLayoutX(360);
        armesLabel.setLayoutY(120);

        Label graphismesLabel = new Label("Graphismes");
        graphismesLabel.setLayoutX(560);
        graphismesLabel.setLayoutY(120);

        Button ajouterJoueurButton = new Button("Ajouter joueur");
        ajouterJoueurButton.setLayoutX(60);

        Button supprimerJoueurButton = new Button("Suppimer joueur");
        supprimerJoueurButton.setLayoutX(180);


        Button demarrerPartie = new Button("Démarrer la partie");
        demarrerPartie.setLayoutX(400);

        this.addHandler(demarrerPartie);
        root.getChildren().addAll(ajouterJoueurButton, supprimerJoueurButton, demarrerPartie, graphismesLabel, armesLabel, mouvementsLabel,nomsJoueurLabel, joueursLabel, pluggin2label, pluggin1label, guiLabel);

        primaryStage.show();
    }

    public void addHandler(Button demarrerPartie){

        //Button ajouterJoueurButton,Button supprimerJoueurButton

        demarrerPartie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Demarrage de la partie");
                startGame = new GameGUI();
                startGame.start(stage);
            }
        });
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}
