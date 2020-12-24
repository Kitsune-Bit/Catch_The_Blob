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

    public void addBlob(Blob blob) {
        blobs.add(blob);
    }

    public boolean submitBlob(ArrayList<String> required) {
        int n = blobs.size() - 1;
        int m = required.size() - 1;
        for (int i = 0; i < required.size(); i++) {
            if (blobs.get(n-i).getColour() != required.get(m-i)) {
                return false;
            }
        }
        for (int i = 0; i < required.size(); i++) {
            blobs.remove(n-i);
        }
        return true;
    }

}
