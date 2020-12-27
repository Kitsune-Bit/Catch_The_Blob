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
        player = new Player(400,550,3);
        player.setSpeed(5);
        points = 0;
    }

    public Player getPlayer() {
        return player;
    }
    private void initializePointBlobs() {
        pointBlobs = new ArrayList<Blob>();
        numberToUse = 3; // Number of blobs needed
        Random rand = new Random();
        for (int i = 0; i < numberToUse; i++) {
            pointBlobs.add(new Blob(0,0,rand.nextInt(3) + 1));
        }
        gameView.displayBlobs(pointBlobs);
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
            gameView.refreshPlayerBlobs();
        } else if (player.getBlobs().size() >= numberToUse) {
            player.resetBlobs();
            gameView.refreshPlayerBlobs();
        }
        return points;
    }

    public void movePlayerLeft(int width, int border) {
        if(player.getX() > 2 * border) {
            player.x().set(player.getX() - player.getSpeed());
        }
    }

    public void movePlayerRight(int width, int border) {
        if(player.getX() < width - border) {
            player.x().set(player.getX() + player.getSpeed());
        }
    }

//    public void debugBlob() {
//        System.out.print("Currently have: ");
//        for (Blob blob : player.getBlobs()) {
//            System.out.print(blob.getCode());
//        }
//        System.out.println("");
//        System.out.print("Currently need: ");
//        for (Blob blob : pointBlobs) {
//            System.out.print(blob.getCode());
//        }
//        System.out.println("\n=====================");
//    }

    public void insertBlob(Blob blob) {
        player.addBlob(blob);
        gameView.displayPlayerBlobs(player.getBlobs());
    }

}
