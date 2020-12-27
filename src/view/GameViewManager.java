package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.Blob;
import src.Entity;
import src.GameController;

public class GameViewManager {

    private static final int height = 600;
    private static final int width = 800;
    private static final int border = 50;
    private static final int frequency = 40;

    private AnchorPane gameAnchorPane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private GameController gameController;

    private ImageView player;
    private ImageView[] blobImage;
    private ImageView[] playerBlobImage;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private AnimationTimer gameTimer;
    private ArrayList<Blob> blobs;

    
    private Label pointLabel;

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
        blobs = new ArrayList<Blob>();
        blobImage = new ImageView[3];
        playerBlobImage = new ImageView[3];
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
        displayPlayer();
        displayText();
//        Testing

//        Testing
        createGameLoop();
        gameStage.show();
    }

    private void displayPlayer() {
        player = new ImageView("/images/player.png");
        trackPosition(gameController.getPlayer(), player);
        gameAnchorPane.getChildren().add(player);
    }

    private void displayText() {
        pointLabel = new Label("Points: 0");
        pointLabel.setLayoutX(border);
        gameAnchorPane.getChildren().add(pointLabel);
        Label blobLabel = new Label("Current blobs");
        blobLabel.setLayoutX(border);
        blobLabel.setLayoutY(height/2);
        gameAnchorPane.getChildren().add(blobLabel);
    }

    private void createGameLoop() {
        Random rand = new Random();
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movePlayer();
                if (rand.nextInt(frequency) == 1) {
                    generateBlob();
                }
                moveBlob();
            }
        };
        gameTimer.start();
    }

    public void stopGame() {
        gameStage.close();
        gameTimer.stop();
        menuStage.show();
    }

    private void movePlayer() {
        if (isLeftKeyPressed && !isRightKeyPressed) {
            gameController.movePlayerLeft(width, border);
        }
        
        if (isRightKeyPressed && !isLeftKeyPressed) {
            gameController.movePlayerRight(width, border);
        }
    }

    private void trackPosition(Entity entity, Node node) {
        node.setLayoutX(entity.getX());
        node.setLayoutY(entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                node.setLayoutX(newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                node.setLayoutY(newValue.intValue());
            }
        });
    }

    private void generateBlob() {
        Random rand = new Random();
        // Number of different blobs
        int code = rand.nextInt(3) + 1;
        String imagePath = String.format("/images/%s.png", code);
        Blob tmp = new Blob(rand.nextInt(width - 2 * border) + 2 * border, -1*rand.nextInt(height), code);
        ImageView tmpImage = new ImageView(imagePath);
        tmpImage.setFitHeight(40);
        tmpImage.setFitWidth(40);
        trackPosition(tmp, tmpImage);
        tmp.setImage(tmpImage);
        gameAnchorPane.getChildren().add(tmpImage);
        blobs.add(tmp);
    }

    private void moveBlob() {
        for (Iterator<Blob> it = blobs.iterator(); it.hasNext();) {
            Blob b = it.next();
            if (b.getY() < height + border) {
                b.y().set(b.getY() + 5);
            } else {
                it.remove();
                gameAnchorPane.getChildren().remove(b.getImage());
            }
            if (b.getImage().getBoundsInParent().intersects(player.getBoundsInParent())) {
                it.remove();
                gameAnchorPane.getChildren().remove(b.getImage());
                gameController.insertBlob(b);
                pointLabel.setText("Points: " + gameController.checkPlayerPoint());
            }
        }
    }

// Refactor displayblob later
    public void displayBlobs(ArrayList<Blob> blobs) {
        for(int i = 0; i < blobImage.length; i++) {
            gameAnchorPane.getChildren().remove(blobImage[i]);
            String imagePath = String.format("/images/%s.png", blobs.get(i).getCode());
            blobImage[i] = new ImageView(imagePath);
            blobImage[i].setFitHeight(40);
            blobImage[i].setFitWidth(40);
            blobImage[i].setLayoutX(border);
            blobImage[i].setLayoutY(blobImage.length * border - i * border);
            gameAnchorPane.getChildren().add(blobImage[i]);
        }
    }

    public void displayPlayerBlobs(ArrayList<Blob> blobs) {
        for(int i = 0; i < blobs.size(); i++) {
            gameAnchorPane.getChildren().remove(playerBlobImage[i]);
            String imagePath = String.format("/images/%s.png", blobs.get(i).getCode());
            playerBlobImage[i] = new ImageView(imagePath);
            playerBlobImage[i].setFitHeight(40);
            playerBlobImage[i].setFitWidth(40);
            playerBlobImage[i].setLayoutX(border);
            playerBlobImage[i].setLayoutY(height - (i + 2) * border);
            gameAnchorPane.getChildren().add(playerBlobImage[i]);
        }
    }

    public void refreshPlayerBlobs() {
        for(int i = 0; i < blobImage.length; i++) {
            gameAnchorPane.getChildren().remove(playerBlobImage[i]);
        }
    }

}
