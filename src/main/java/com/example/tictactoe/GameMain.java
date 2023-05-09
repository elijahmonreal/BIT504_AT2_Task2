package com.example.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class GameMain extends Application {

    //    Constants
    public static final int APP_WIDTH = 300;
    public static final int APP_HEIGHT = 400;
    public static final int CELL_WIDTH = 100;
    public static final int CELL_HEIGHT = 100;
    public static final int INFO_CENTER_HEIGHT = 100;
    public static final int TILE_BOARD_HEIGHT = 300;
    public static final int TOTAL_CELLS = 3;
    public static final Color STORKE_FILL_COLOR = Color.TRANSPARENT;
    public static final Color STORKE_COLOR = Color.GRAY;
    public static final int STORKE_WIDTH = 3;

    public enum GameState {
        player_won, draw, None
    }

    public static GameState state;
    public static boolean IS_PLAYER_X_TURN = true;
    private static InfoCenter infoCenter;
    private static Board board;

    //    Entry Point of Application
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);
        initRoot(root);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    //    Initiating Board Grid and Info Panel to display messages
    private void initRoot(BorderPane root) {
        initInfoCenter(root);
        initBoard(root);
    }

    private void initInfoCenter(BorderPane root) {
        infoCenter = new InfoCenter();
        infoCenter.setStartGameButtonOnAction(startNewGame());
        root.getChildren().add(infoCenter.getStackPane());
    }

    //    Whenevr cell is clicked updateGame() is called to check for results
    public static void updateGame() {
        String c1 = board.cells[0][0].getContent();
        String c2 = board.cells[0][1].getContent();
        String c3 = board.cells[0][2].getContent();

        String c4 = board.cells[1][0].getContent();
        String c5 = board.cells[1][1].getContent();
        String c6 = board.cells[1][2].getContent();

        String c7 = board.cells[2][0].getContent();
        String c8 = board.cells[2][1].getContent();
        String c9 = board.cells[2][2].getContent();

        boolean won = false;
        if (c1 == c2 && c2 == c3 && c3 != "") {
            String p = "Player " + ((c1 == "X") ? "X" : "0") + " Won";
            infoCenter.updateMessage(p);
            won = true;
            System.out.println("--1");
        }
        else if (c1 == c4 && c4 == c7 && c7 != "") {
            String p = "Player " + ((c1 == "X") ? "X" : "0") + " Won";
            infoCenter.updateMessage(p);
            won = true;
            System.out.println("--2");

        }
        else if (c4 == c5 && c5 == c6 && c6 != "") {
            String p = "Player " + ((c4 == "X") ? "X" : "0") + " Won";
            infoCenter.updateMessage(p);
            won = true;
            System.out.println("--3");

        }
        else if (c7 == c8 && c8 == c9 && c9 != "") {
            String p = "Player " + ((c7 == "X") ? "X" : "0") + " Won";
            infoCenter.updateMessage(p);
            won = true;
            System.out.println("--4");

        }
        else if (c3 == c5 && c5 == c7 && c7 != "") {
            String p = "Player " + ((c3 == "X") ? "X" : "0") + " Won";
            infoCenter.updateMessage(p);
            won = true;
            System.out.println("--5");

        }
        else if (c2 == c5 && c5 == c8 && c8 != "") {
            String p = "Player " + ((c8 == "X") ? "X" : "0") + " Won";
            infoCenter.updateMessage(p);
            won = true;
            System.out.println("--6");

        }
        else if (c3 == c6 && c6 == c9 && c9 != "") {
            String p = "Player " + ((c9 == "X") ? "X" : "0") + " Won";
            infoCenter.updateMessage(p);
            won = true;
            System.out.println("--7");

        }else{
            int filledCells = 0;
            for (int row = 0; row < TOTAL_CELLS; row++) {
                for (int col = 0; col < TOTAL_CELLS; col++) {
                    if(board.cells[row][col].getContent().length()>0){
                        filledCells += 1;
                    }
                }
            }
            System.out.println("filledCells = "+filledCells);
            if (filledCells == 9 ) {
                board.isDraw();
                state = GameState.None;
                infoCenter.showStartButton();
                infoCenter.setStartButtonText("Restart Game");
                infoCenter.updateMessage("Draw !!!");
            }
        }



        if (won) {
            board.hasWon();
            infoCenter.showStartButton();
            infoCenter.setStartButtonText("Restart Game");
            state = GameState.None;
        }
    }

    //    onMouseClick event that get called when click on cell
    public static void cellClickHandler(Cell c) {
        if (state == GameState.player_won || state == GameState.draw) {
            showAlert("Game finshed. Please resatrt game to reuse Board");
            infoCenter.showStartButton();
            infoCenter.setStartButtonText("Restart Game");
            state = GameState.None;
            return;
        }
        infoCenter.updateMessage("Player " + (!IS_PLAYER_X_TURN ? "X" : "0") + "'s Turn");
        Cell.Player p = IS_PLAYER_X_TURN ? Cell.Player.Cross : Cell.Player.Nought;
        if (c.getContent().length() > 0) {
            showAlert("Cannot click on this cell");
            return;
        }
        c.paint(p);
        IS_PLAYER_X_TURN = !IS_PLAYER_X_TURN;
        updateGame();
    }

    //    Alert Box to display messages
    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    //    Event handler when click on Button
    private EventHandler<ActionEvent> startNewGame() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                infoCenter.hideStartButton();
                board.showBoard();

                infoCenter.updateMessage("Player X's Turn");
                for (int row = 0; row < TOTAL_CELLS; row++) {
                    for (int col = 0; col < TOTAL_CELLS; col++) {
                        board.cells[row][col].clear();
                    }
                }
            }
        };
    }

    private void initBoard(BorderPane root) {
        board = new Board();
        root.getChildren().add(board.getStackPane());
        board.hideBoard();
    }

    public static void main(String[] args) {
        launch();
    }
}