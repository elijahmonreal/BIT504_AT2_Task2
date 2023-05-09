package com.example.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


//class for cell
public class Cell {
    private StackPane pane;
    private Label content;

    public enum Player {
        Nought, Cross, None
    }

    public Cell() {

//        visual representation of cell
        pane = new StackPane();
        pane.setMinSize(GameMain.CELL_WIDTH, GameMain.CELL_HEIGHT);
        Rectangle border = new Rectangle();
        border.setWidth(GameMain.CELL_WIDTH);
        border.setHeight(GameMain.CELL_HEIGHT);
        border.setFill(GameMain.STORKE_FILL_COLOR);
        border.setStroke(GameMain.STORKE_COLOR);
        border.setStrokeWidth(GameMain.STORKE_WIDTH);
        pane.getChildren().add(border);

        content = new Label("");
        content.setAlignment(Pos.CENTER);
        content.setFont(Font.font(24));
        pane.getChildren().add(content);

        this.pane.setOnMouseClicked(event -> {
            GameMain.cellClickHandler(this);
        });
    }

    public void paint(Player p) {
        if (p == Player.Cross) {
            content.setText("X");
        } else if (p == Player.Nought) {
            content.setText("0");

        }
    }

    public StackPane getStackPane() {
        return this.pane;
    }

    public String getContent() {
        return content.getText();
    }

    public void clear() {
        this.content.setText("");
    }
}
