package m1_miage.presenter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import m1_miage.presenter.fields.NumberTextField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Class which launches the game and associated classes
 */

public class GameMenuStart extends Application {
    private int width = 300;
    private int height = 600;
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private Stage stage;

    private int nbVaisseaux = 2;
    private ArrayList<Integer> weapons = new ArrayList<>();

    private GameGUI startGame;

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


//        //Create menu items
//        MenuItem option1 = new MenuItem("option 1");
//        MenuItem option2 = new MenuItem("option 2");
//
//        plugginMenu.getItems().addAll(option1);
//        plugginMenu2.getItems().addAll(option2);
//
//        menuBar.getMenus().addAll(plugginMenu,plugginMenu2);


        Label title = new Label("PARAMETRAGE DU JEU");
        title.setTextFill(Color.BLUE);
        title.setLayoutX(20);
        title.setLayoutY(10);


        Label joueursLabel = new Label("Nombre de joueurs : ");
        joueursLabel.setLayoutX(10);
        joueursLabel.setLayoutY(100);

        NumberTextField ntf = new NumberTextField();
        ntf.setNumber(new BigDecimal(nbVaisseaux));
        ntf.setLayoutX(10);
        ntf.setLayoutY(150);

        Button choixDesArmes = new Button("Choisir les armes des joueurs");
        choixDesArmes.setLayoutX(10);
        choixDesArmes.setLayoutY(200);

        Button demarrerPartie = new Button("Démarrer la partie");
        demarrerPartie.setLayoutX(10);
        demarrerPartie.setLayoutY(550);
        this.addHandler(demarrerPartie,ntf, choixDesArmes);
        root.getChildren().addAll(title, demarrerPartie, joueursLabel, ntf, choixDesArmes);

        primaryStage.show();
    }

    public void addHandler(Button demarrerPartie, NumberTextField ntf, Button choixDesArmes){

        demarrerPartie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(weapons==null) weapons=new ArrayList<>();
                nbVaisseaux = ntf.getNumber().intValue();
                System.out.println("Demarrage de la partie : \n"+nbVaisseaux+" joueur"+((nbVaisseaux>1)?"s":""));
                startGame = new GameGUI(nbVaisseaux, weapons);
                startGame.start(stage);
            }
        });

        choixDesArmes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                weapons = new ArrayList<>();
                nbVaisseaux = ntf.getNumber().intValue();
                System.out.println("Choix des armes de la partie : \n"+nbVaisseaux+" joueur"+((nbVaisseaux>1)?"s":""));
                for(int i=0 ; i<nbVaisseaux;i++){
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setTitle("Choix d'arme : Vaisseau "+(i+1));
                    ButtonType buttonTypeOne = new ButtonType("N°1");
                    ButtonType buttonTypeTwo = new ButtonType("N°2");

                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne){
                        weapons.add(1);
                    } else if (result.get() == buttonTypeTwo) {
                        weapons.add(2);
                    } else {
                        //annulation
                    }
                }
            }
        });





    }
    public static void main(String[] args){
        Application.launch(args);
    }
}
