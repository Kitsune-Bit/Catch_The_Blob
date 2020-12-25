package entities;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class MainMenuSubScene extends SubScene {

    private final static String backgroundImage = "/images/subScenePanel.png";

    private boolean isHidden;

    public MainMenuSubScene() {
        super(new AnchorPane(), 640, 460);

        prefWidth(640);
        prefHeight(460);

        BackgroundImage image = new BackgroundImage(new Image(backgroundImage, 640, 460, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(new Background(image));

        isHidden = true;

        setLayoutX(340);
        setLayoutY(1500);

    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.35));
        transition.setNode(this);

        if(isHidden) {
            transition.setToY(-1300);
            isHidden = false;
        }

        else {
            transition.setToY(1500);
            isHidden = true;
        }

        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

}
