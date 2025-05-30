package com.island.views.ui;

import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ActionLogView extends VBox {
    private ListView<String> logList;

    public ActionLogView() {
        setPadding(new Insets(10));
        setPrefWidth(200);
//        setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-border-color: black;");
        setStyle("-fx-background-color:transparent;");

        Text title = new Text("Action Log");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        logList = new ListView<>();
        logList.setPrefHeight(400);
        logList.setStyle("-fx-background-color:transparent;");

        logList.setCellFactory(param -> new javafx.scene.control.ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    // Create a Text object to support automatic text wrapping
                    Text text = new Text(item);
                    text.setWrappingWidth(logList.getWidth() - 20); // Set wrapping width, leaving some margin
                    setGraphic(text);
                    setText(null);
                    setStyle("-fx-background-color: rgba(255, 255, 255, 0.2); " +
                            "-fx-background-radius: 5; " +
                            "-fx-padding: 8; " +
                            "-fx-text-fill: black;");
                    // Add top and bottom spacing
                    setPadding(new Insets(5, 0, 5, 0));
                }
            }
        });
        getChildren().addAll(title, logList);
    }

    public void addLog(String message) {
        // Show maximum of 13 logs, with new logs displayed at top
        if (logList.getItems().size() >= 7) {
            logList.getItems().remove(6);
        }
        logList.getItems().addFirst(message);
    }

    public void clear() {
        logList.getItems().clear();
    }

    public void shutdown() {
        // Clean up log list
        if (logList != null) {
            logList.getItems().clear();
        }
    }
}