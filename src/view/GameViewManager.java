package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
    }

}
