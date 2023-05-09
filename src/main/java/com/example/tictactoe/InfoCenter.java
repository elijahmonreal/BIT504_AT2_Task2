package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

//Class to show messages and Game Start Button
public class InfoCenter {
    private StackPane pane;
    private Label message;
    private Button startGameButton;

    public InfoCenter() {
        pane = new StackPane();
        pane.setMinSize(GameMain.APP_WIDTH, GameMain.INFO_CENTER_HEIGHT);
        pane.setTranslateX(GameMain.APP_WIDTH / 2);
        pane.setTranslateY(GameMain.INFO_CENTER_HEIGHT / 2);

        message = new Label("Tic Tac Toe");
        message.setMinSize(GameMain.APP_WIDTH, GameMain.APP_HEIGHT);
        message.setFont(Font.font(24));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-20);
        pane.getChildren().add(message);

        startGameButton = new Button("Start New Game");
        startGameButton.setMinSize(135, 30);
        startGameButton.setTranslateY(20);
        pane.getChildren().add(startGameButton);
    }

    public StackPane getStackPane() {
        return pane;
    }

    public void updateMessage(String msg) {
        this.message.setText(msg);
    }

    public void showStartButton() {
        startGameButton.setVisible(true);
    }

    public void setStartButtonText(String s) {
        this.startGameButton.setText(s);
    }

    public void hideStartButton() {
        startGameButton.setVisible(false);
    }

    //    event handler when button is clicked
    public void setStartGameButtonOnAction(EventHandler<ActionEvent> onAction) {
        startGameButton.setOnAction(onAction);
    }

}
