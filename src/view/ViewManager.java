package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import entities.*;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int height = 720;
    private static final int width = 1280;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    List<CharacterSelector> characterList;
    private CHARACTER chosenCharacter;

    List<MainMenuButtons> menuButtons;
    private final static int mainMenuButtonsX = 100;
    private final static int mainMenuButtonsY = 200;

    private MainMenuSubScene characterSelectionSubScene;
    private MainMenuSubScene instructionsSubScene;
    private MainMenuSubScene highscoresSubScene;
    private MainMenuSubScene creditsSubScene;

    private MainMenuSubScene subSceneToHide;

    public ViewManager(){

        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, width, height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        createSubScenes();
        createBackground();
        createLogo();
        createKandyLogo();
        createButtons();

    }

    /*
    Main Menu Sub-Scenes Below
     */

    private void showSubScene(MainMenuSubScene subScene) {
        if(subSceneToHide != null) {
            subSceneToHide.moveSubScene();

        }

        subScene.moveSubScene();
        subSceneToHide = subScene;

    }

    private void createSubScenes() {

        creditsSubScene = new MainMenuSubScene();
        mainPane.getChildren().add(creditsSubScene);

        createHighscoresSubScene();
        createInstructionsSubScene();
        createCreditsSubScene();
        createCharacterSelectionSubScene();
    }

    private void createCharacterSelectionSubScene() {
        characterSelectionSubScene = new MainMenuSubScene();
        mainPane.getChildren().add(characterSelectionSubScene);

        InfoLabel chooseCharacterLabel = new InfoLabel("Select your character: ");
        chooseCharacterLabel.setLayoutX(110);
        chooseCharacterLabel.setLayoutY(25);
        characterSelectionSubScene.getPane().getChildren().add(chooseCharacterLabel);

//        VBox chooseCharacterLabelContainer = new VBox();
//        chooseCharacterLabelContainer.setLayoutX(150);
//        chooseCharacterLabelContainer.setLayoutY(150);

        characterSelectionSubScene.getPane().getChildren().add(createCharacterToChoose());
        characterSelectionSubScene.getPane().getChildren().add(createButtonToStart());


    }

    private HBox createCharacterToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        characterList = new ArrayList<>();
        for (CHARACTER character : CHARACTER.values()) {
            CharacterSelector characterToPick = new CharacterSelector(character);
            characterList.add(characterToPick);
            box.getChildren().add(characterToPick);
            characterToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (CharacterSelector character : characterList) {
                        character.setIsCircleChosen(false);
                    }

                    characterToPick.setIsCircleChosen(true);
                    chosenCharacter = characterToPick.getCharacter();

                }
            });

        }
        box.setLayoutX(300 - (118 * 2));
        box.setLayoutY(100);
        return box;

    }

    private MainMenuButtons createButtonToStart() {
        MainMenuButtons startButton = new MainMenuButtons("Go!");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (chosenCharacter != null) {
                    GameViewManager gameManager = new GameViewManager();
                    gameManager.createNewGame(mainStage);
                }
            }
        });

        return startButton;

    }

    private void createHighscoresSubScene() {
        highscoresSubScene = new MainMenuSubScene();
        mainPane.getChildren().add(highscoresSubScene);
        InfoLabel highscore = new InfoLabel("    High scores    ");
        highscore.setLayoutX(115);
        highscore.setLayoutY(20);

        VBox highscoreContainer = new VBox();
        highscoreContainer.setLayoutX(150);
        highscoreContainer.setLayoutY(150);
        Label highscoreHeading = new Label("     Name           Score   ");
        highscoreHeading.setUnderline(true);
        Label score1 = new Label("PBear        100");
        Label score2 = new Label("Hippo       50");
        Label score3 = new Label("Dango      20");

        highscoreContainer.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(20),
                new Insets(-20,-20,-20,-20))));
        highscoreContainer.getChildren().addAll(highscoreHeading, score1, score2, score3);
        highscoresSubScene.getPane().getChildren().addAll(highscore, highscoreContainer);//, score1, score2, score3);
    }

    private void createInstructionsSubScene() {
        instructionsSubScene = new MainMenuSubScene();
        mainPane.getChildren().add(instructionsSubScene);
        InfoLabel instructions = new InfoLabel("    Instructions    ");
        instructions.setLayoutX(115);
        instructions.setLayoutY(20);

        VBox instructionsContainer = new VBox();
        instructionsContainer.setLayoutX(20);
        instructionsContainer.setLayoutY(100);
        Label instructionsHeading1 = new Label("Character Movement \nDango");
        Label instructionsHeading2 = new Label("Dango");

        instructionsHeading1.setUnderline(true);
        instructionsHeading2.setUnderline(true);


        instructionsContainer.getChildren().addAll(instructionsHeading1, instructionsHeading2);
        instructionsSubScene.getPane().getChildren().addAll(instructions, instructionsContainer);


    }

    private void createCreditsSubScene() {
        creditsSubScene = new MainMenuSubScene();
        mainPane.getChildren().add(creditsSubScene);
        InfoLabel credits = new InfoLabel("    Credits    ");
        credits.setLayoutX(115);
        credits.setLayoutY(20);

        VBox creditsContainer = new VBox();
        creditsContainer.setLayoutX(20);
        creditsContainer.setLayoutY(100);
        Label creditsHeading1 = new Label("Beast and");
        Label creditsHeading2 = new Label("Boop");

        creditsHeading1.setUnderline(true);
        creditsHeading2.setUnderline(true);


        creditsContainer.getChildren().addAll(creditsHeading1, creditsHeading2);
        creditsSubScene.getPane().getChildren().addAll(credits, creditsContainer);
    }


    public Stage getMainStage(){

        mainStage.setResizable(false);
        return mainStage;

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

//        createForceStartButton(); // For Debuging Uses

    }

    private void createStartButton() {
        MainMenuButtons startButton = new MainMenuButtons("Play!");
        addMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(characterSelectionSubScene);
            }
        });


    }

    private void createInstructionsButton() {
        MainMenuButtons instructionsButton = new MainMenuButtons("Instructions");
        addMenuButtons(instructionsButton);

        instructionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(instructionsSubScene);
            }
        });

    }

    private void createHighscoresButton() {
        MainMenuButtons highscoresButton = new MainMenuButtons("High Scores");
        addMenuButtons(highscoresButton);

        highscoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(highscoresSubScene);
            }
        });

    }

    private void createCreditsButton() {
        MainMenuButtons creditsButton = new MainMenuButtons("Credits");
        addMenuButtons(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditsSubScene);

            }
        });

    }

    private void createExitButton() {
        MainMenuButtons exitButton = new MainMenuButtons("Exit");
        addMenuButtons(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }

        });

    }


//For Debugging Uses
//    private void createForceStartButton() {
//            MainMenuButtons startButton = new MainMenuButtons("Play!");
//            addMenuButtons(startButton);
//
//            startButton.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    GameViewManager gameManager = new GameViewManager();
//                    gameManager.createNewGame(mainStage);;
//                }
//            });
//    }


    private void createBackground() {
        BackgroundImage background = new BackgroundImage(new Image("/images/background.jpg", 1280,
                720, false, false), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        mainPane.setBackground(new Background(background));
    }

    private void createLogo(){
        ImageView logo = new ImageView("/images/logo_640x160.png");

        logo.setLayoutX(340);
        logo.setLayoutY(25);

        mainPane.getChildren().add(logo);

    }

    private void createKandyLogo() {
        ImageView kandyLogo = new ImageView("/images/kandyLogo.png");
        kandyLogo.setLayoutX(1100);
        kandyLogo.setLayoutY(650);

        mainPane.getChildren().add(kandyLogo);

    }








}
