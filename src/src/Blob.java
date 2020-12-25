package src;

public class Blob extends Entity {

    private int code;

    public Blob(int x, int y, int code) {
        super(x, y);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setColour(int code) {
        this.code = code;
    }

}
