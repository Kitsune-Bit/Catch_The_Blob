package view;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.ArrayList;
import entities.MainMenuButtons;

public class ViewManager {

    private static final int width = 1280;
    private static final int height = 720;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int mainMenuButtonsX = 100;
    private final static int mainMenuButtonsY = 200;

    List<MainMenuButtons> menuButtons;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, width, height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createBackground();
        createLogo();
        createKandyLogo();
        createButtons();

    }

    public Stage getMainStage(){
        mainStage.setResizable(false);
        return mainStage;
    }

    private void createBackground() {
        BackgroundImage background = new BackgroundImage(new Image("/pictures/background.jpg", 1280,
                720, false, false), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        mainPane.setBackground(new Background(background));
    }

    private void createLogo(){
        ImageView logo = new ImageView("/pictures/logo_640x160.png");

        logo.setLayoutX(340);
        logo.setLayoutY(25);

        mainPane.getChildren().add(logo);

    }

    private void createKandyLogo() {
        ImageView kandyLogo = new ImageView("/pictures/kandyLogo.png");
        kandyLogo.setLayoutX(1100);
        kandyLogo.setLayoutY(650);

        mainPane.getChildren().add(kandyLogo);

    }


    /*
        Creating Menu Buttons Below
     */

    private void addMenuButtons(MainMenuButtons button) {
        button.setLayoutX(mainMenuButtonsX);
        button.setLayoutY(mainMenuButtonsY + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createInstructionsButton();
        createHighscoresButton();
        createCreditsButton();
        createExitButton();

    }

    private void createStartButton() {
        MainMenuButtons startButton = new MainMenuButtons("Play!");
        addMenuButtons(startButton);
    }

    private void createInstructionsButton() {
        MainMenuButtons instructionsButton = new MainMenuButtons("Instructions");
        addMenuButtons(instructionsButton);
    }

    private void createHighscoresButton() {
        MainMenuButtons highscoresButton = new MainMenuButtons("High Scores");
        addMenuButtons(highscoresButton);
    }

    private void createCreditsButton() {
        MainMenuButtons creditsButton = new MainMenuButtons("Credits");
        addMenuButtons(creditsButton);
    }

    private void createExitButton() {
        MainMenuButtons exitButton = new MainMenuButtons("Exit");
        addMenuButtons(exitButton);
    }




}