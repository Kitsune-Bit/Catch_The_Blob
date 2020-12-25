package entities;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class InfoLabel extends Label{
    public final static String BG_IMAGE = "/images/subScenePanel.png";

    public InfoLabel(String text) {
        setPrefWidth(600);
        setPrefHeight(30);
        setPadding(new Insets(10, 40, 40, 50));
        setText(text);
        setWrapText(true);

//        setAlignment(Pos.CENTER);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(BG_IMAGE, 380, 50, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        setBackground(new Background(backgroundImage));

    }

}
