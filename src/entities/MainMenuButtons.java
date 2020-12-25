package entities;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MainMenuButtons extends Button{

    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/images/lilacButtonPressed.png');";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/images/lilacButton.png');";

    public MainMenuButtons(String text) {

        setText(text);
        setPrefWidth(178);
        setPrefHeight(61);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();

    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(55);
        setLayoutY(getLayoutY() + 4);

    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(61);
        setLayoutY(getLayoutY() - 4);

    }

    private void initializeButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });

    }


}