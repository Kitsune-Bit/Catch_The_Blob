package entities;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CharacterSelector extends VBox {

    private ImageView circleImage;
    private ImageView characterImage;

    private String circleNotChosen = "/images/untickedCircle.png";
    private String circleChosen = "/images/tickedCircle.png";

    private CHARACTER character;

    private boolean isCircleChosen;

    public CharacterSelector(CHARACTER character) {
        circleImage = new ImageView(circleNotChosen);
        characterImage = new ImageView(character.getUrl());
        this.character = character;
        isCircleChosen = false;

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(characterImage);

    }

    public CHARACTER getCharacter() {
        return character;
    }

    public boolean getIsCircleChosen() {
        return isCircleChosen;
    }

    public void setIsCircleChosen(boolean isCircleChosen) {
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen ? circleChosen : circleNotChosen;
        circleImage.setImage(new Image(imageToSet));
    }



}
