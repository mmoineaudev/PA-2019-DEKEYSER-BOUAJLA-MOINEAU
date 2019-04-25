package m1_miage.controler;

import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class which launches the game and associated classes
 */

public class GameMenu extends Application {

    @Override

    public void start(Stage primaryStage) throws Exception{

        Text guiText = new Text(60,80,"GUI");
        guiText.setFont(new Font(40));
//        guiText.setFill(Color.BLUE);

        MenuBar menuBar = new MenuBar();

        //Create menus
        Menu plugginMenu = new Menu("Pluggin1");
        Menu plugginMenu2 = new Menu("Pluggin2");

        //Create menu items
        MenuItem option1 = new MenuItem("option 1");
        MenuItem option2 = new MenuItem("option 2");

        plugginMenu.getItems().addAll(option1);
        plugginMenu2.getItems().addAll(option2);

        menuBar.getMenus().addAll(plugginMenu,plugginMenu2);

        Group root = new Group();
       // root.setTop(menuBar);
        Scene scene = new Scene(root,512,512);
        root.getChildren().add(guiText);
        root.getChildren().add(menuBar);

        primaryStage.setTitle("Asteroids Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}
