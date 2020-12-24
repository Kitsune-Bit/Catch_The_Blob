package src;

public class Blob extends Entity {

    private String colour;

    public Blob(String colour) {
        super();
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

}
