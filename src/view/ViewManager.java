package view;

import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewManager {

    private static final int height = 600;
    private static final int width = 800;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, width, height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainPane.getChildren().add(createButtonToStart());
    }

    public Stage getMainStage(){
        return mainStage;
    }

    private Button createButtonToStart() {
        Button startButton = new Button("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);
        
        
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameViewManager gameManager = new GameViewManager();
                gameManager.createNewGame(mainStage);;
            }
        });
        return startButton;
    }

}
