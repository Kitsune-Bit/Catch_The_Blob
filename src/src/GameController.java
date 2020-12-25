package src;

import java.util.ArrayList;
import java.util.Random;

import view.GameViewManager;

public class GameController {

    private GameViewManager gameView;
    private Player player;
    private ArrayList<Blob> pointBlobs;
    private int points;
    private int numberToUse;

    public GameController(GameViewManager gameView) {
        this.gameView = gameView;
        initializePlayer();
        initializePointBlobs();
    }

    public void initializePlayer() {
        this.player = new Player(200,200,3);
        this.points = 0;
    }

    public Player getPlayer() {
        return player;
    }
    private void initializePointBlobs() {
        pointBlobs = new ArrayList<Blob>();
        numberToUse = 3; // Number of blobs needed
        Random rand = new Random();
        for (int i = 0; i < numberToUse; i++) {
            pointBlobs.add(new Blob(0,0,rand.nextInt(5) + 1));
        }
    }

    public void removePlayerLife() {
        player.setLifeCount(player.getLifeCount() - 1);
        if (player.getLifeCount() <= 0 ) {
            gameView.stopGame();
        }
    }

    public int checkPlayerPoint() {
        if (player.submitBlob(pointBlobs)) {
            // Increase points
            initializePointBlobs();
            points++;
        } else if (player.getBlobs().size() > numberToUse) {
            player.resetBlobs();
        }
        return points;
    }

    public void debugBlob() {
        System.out.print("Currently have: ");
        for (Blob blob : player.getBlobs()) {
            System.out.print(blob.getCode());
        }
        System.out.println("");
        System.out.print("Currently need: ");
        for (Blob blob : pointBlobs) {
            System.out.print(blob.getCode());
        }
        System.out.println("\n=====================");
    }

    public void insertBlob(int code) {
        player.addBlob(new Blob(0,0,code));
    }
}
