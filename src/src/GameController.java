package src;

import java.util.ArrayList;

import view.GameViewManager;

public class GameController {

    private GameViewManager gameView;
    private Player player;
    private ArrayList<String> pointBlobs;

    public GameController(GameViewManager gameView) {
        this.gameView = gameView;
        initializePlayer();
        initializePointBlobs();
    }

    public void initializePlayer() {
        player = new Player(3);
    }

    private void initializePointBlobs() {
        pointBlobs = new ArrayList<String>();
//        Logic for different blobs not created
        pointBlobs.add("Blue");
        pointBlobs.add("Red");
    }

    public void removePlayerLife() {
        player.setLifeCount(player.getLifeCount() - 1);
        if (player.getLifeCount() <= 0 ) {
            gameView.stopGame();
        }
    }

    public void checkPlayerPoint() {
        if (player.submitBlob(pointBlobs)) {
            // Increase points
        }
    }

}
