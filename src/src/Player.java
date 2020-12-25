package src;

import java.util.ArrayList;

public class Player {

    private int lifeCount;
    private ArrayList<Blob> blobs;

    public Player(int lifeCount) {
        this.lifeCount = lifeCount;
        this.blobs = new ArrayList<Blob>();
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public ArrayList<Blob> getBlobs() {
        return blobs;
    }

    public void addBlob(Blob blob) {
        blobs.add(blob);
    }

    public boolean submitBlob(ArrayList<Blob> required) {
        if (blobs.size() < required.size()) {
            return false;
        }
        for (int i = 0; i < required.size(); i++) {
            if (blobs.get(i).getCode() != required.get(i).getCode()) {
                return false;
            }
        }
        for (int i = 0; i < required.size(); i++) {
            blobs.remove(0);
        }
        return true;
    }

    public void resetBlobs() {
        this.blobs = new ArrayList<Blob>();
    }
}
