package com.example.tictactoe;
import javafx.scene.layout.StackPane;

// class for Board Grid
public class Board {
    private StackPane pane;
    public static Cell[][] cells = new Cell[GameMain.TOTAL_CELLS][GameMain.TOTAL_CELLS];

    public Board() {
        pane = new StackPane();
        pane.setMinSize(GameMain.APP_WIDTH, GameMain.TILE_BOARD_HEIGHT);
        pane.setTranslateX(GameMain.APP_WIDTH / 2);
        pane.setTranslateY((GameMain.INFO_CENTER_HEIGHT / 2) + GameMain.INFO_CENTER_HEIGHT);

//        Initiating Cells
        this.paint();
    }

//        Initiating Cells

    private void paint() {
        int total = GameMain.TOTAL_CELLS;
        for (int row = 0; row < total; row++) {
            for (int col = 0; col < total; col++) {
                Cell cell = new Cell();
                cell.paint(Cell.Player.None);
                cell.getStackPane().setTranslateX((col * 100) - 100);
                cell.getStackPane().setTranslateY((row * 100));
                pane.getChildren().add(cell.getStackPane());
                cells[row][col] = cell;
            }
        }
    }

    public StackPane getStackPane() {
        return this.pane;
    }

    public void hideBoard() {
        this.pane.setVisible(false);
    }

    public void showBoard() {
        this.pane.setVisible(true);
    }


    public GameMain.GameState isDraw() {
        GameMain.state = GameMain.GameState.draw;
        return GameMain.state;
    }

    public GameMain.GameState hasWon() {
        GameMain.state = GameMain.GameState.player_won;
        return GameMain.state;

    }
}
