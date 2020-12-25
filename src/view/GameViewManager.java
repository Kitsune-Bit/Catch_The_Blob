package view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.GameController;

public class GameViewManager {

    private static final int height = 600;
    private static final int width = 800;
    private static final int border = 50;

    private AnchorPane gameAnchorPane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private GameController gameController;

    private ImageView player;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private AnimationTimer gameTimer;
    
    private Label pointLabel;

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
    }

    private void createKeyListeners() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.LEFT) {
                isLeftKeyPressed = true;
            } else if (event.getCode() == KeyCode.RIGHT) {
                isRightKeyPressed = true;
            }
        }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.LEFT) {
                isLeftKeyPressed = false;
            } else if (event.getCode() == KeyCode.RIGHT) {
                isRightKeyPressed = false;
            }
        }
        });
    }

    private void initializeStage() {
        gameAnchorPane = new AnchorPane();
        gameScene = new Scene(gameAnchorPane, width, height);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        gameController = new GameController(this);
        player = new ImageView("/images/player.png");
        player.setLayoutX(width/2);
        player.setLayoutY(height - border);
        gameAnchorPane.getChildren().add(player);
//        Testing
        testGameButton();
        pointLabel = new Label("Points: 0");
        pointLabel.setLayoutX(600);
        gameAnchorPane.getChildren().add(pointLabel);
//        Testing
        createGameLoop();
        gameStage.show();
    }

    private void movePlayer() {
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if(player.getLayoutX() > border) {
                player.setLayoutX(player.getLayoutX() - 5);
            }
        }
        
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if(player.getLayoutX() < width - border) {
                player.setLayoutX(player.getLayoutX() + 5);
            }
        }
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movePlayer();
            }
        };
        gameTimer.start();
    }

    public void stopGame() {
        gameStage.close();
        gameTimer.stop();
        menuStage.show();
    }

    private void testGameButton() {
        Button lifeButton = new Button("- 1 Life");
        lifeButton.setLayoutX(100);
        lifeButton.setLayoutY(100);
        lifeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.removePlayerLife();
            }
        });
        gameAnchorPane.getChildren().add(lifeButton);
        Button pointButton = new Button("Force Check points");
        pointButton.setLayoutX(150);
        pointButton.setLayoutY(100);
        pointButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pointLabel.setText("Points: " + gameController.checkPlayerPoint());
                gameController.debugBlob();
            }
        });
        gameAnchorPane.getChildren().add(pointButton);
        Button aButton = new Button("1 blob");
        aButton.setLayoutX(200);
        aButton.setLayoutY(100);
        aButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.insertBlob(1);
                pointLabel.setText("Points: " + gameController.checkPlayerPoint());
                gameController.debugBlob();
            }
        });
        gameAnchorPane.getChildren().add(aButton);
        Button bButton = new Button("2 blob");
        bButton.setLayoutX(250);
        bButton.setLayoutY(100);
        bButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.insertBlob(2);
                pointLabel.setText("Points: " + gameController.checkPlayerPoint());
                gameController.debugBlob();
            }
        });
        gameAnchorPane.getChildren().add(bButton);
        Button cButton = new Button("3 blob");
        cButton.setLayoutX(300);
        cButton.setLayoutY(100);
        cButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.insertBlob(3);
                pointLabel.setText("Points: " + gameController.checkPlayerPoint());
                gameController.debugBlob();
            }
        });
        gameAnchorPane.getChildren().add(cButton);
        Button dButton = new Button("4 blob");
        dButton.setLayoutX(350);
        dButton.setLayoutY(100);
        dButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.insertBlob(4);
                pointLabel.setText("Points: " + gameController.checkPlayerPoint());
                gameController.debugBlob();
            }
        });
        gameAnchorPane.getChildren().add(dButton);
        Button eButton = new Button("5 blob");
        eButton.setLayoutX(400);
        eButton.setLayoutY(100);
        eButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.insertBlob(5);
                pointLabel.setText("Points: " + gameController.checkPlayerPoint());
                gameController.debugBlob();
            }
        });
        gameAnchorPane.getChildren().add(eButton);
    }
}
