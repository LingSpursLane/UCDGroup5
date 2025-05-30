package com.island.views.ui;

import com.island.controller.ActionBarController;
import com.island.models.Room;
import com.island.models.adventurers.Player;
import com.island.models.adventurers.PlayerRole;
import com.island.models.island.Tile;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ActionBarView {
    private ActionBarController actionBarController;

    private HBox actionBar; // Action bar
    private Label actionLabel; // Action prompt text
    private HBox actionButtons; // Action buttons container
    private Button moveButton;
    private Button shoreUpButton;
    private Button endTurnButton;
    private Button playSpecialButton;
    private Button drawFloodButton;
    private Button giveCardButton; // Give card button
    private Button moveOtherPlayerButton; // Move other player button
    private Button captureTreasureButton; // Capture treasure button
    private Button discardButton; // Discard button

    public ActionBarView(HBox actionBar) {
        this.actionBar = actionBar;
        this.actionBar.setAlignment(Pos.CENTER_LEFT);
        this.actionBar.setPadding(new Insets(10));
        this.actionBar.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: #cccccc; -fx-border-width: 1px;");
    }

    public void initActionButtons() {
        actionLabel = new Label();
        actionLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

        actionButtons = new HBox(5);
        actionButtons.setAlignment(Pos.CENTER_RIGHT);

        // Initialize buttons
        moveButton = createMoveButton("Move");
        shoreUpButton = createShoreUpButton("Shore Up");
        giveCardButton = createGiveCardButton("Give Card");
        moveOtherPlayerButton = createMoveOtherButton("Move Other");
        captureTreasureButton = createCaptureTreasureButton("Capture Treasure");
        endTurnButton = createEndTurnButton("End Turn");
        playSpecialButton = createPlaySpecialButton("Play Special");
        drawFloodButton = createDrawFloodButton("Draw Flood");
        discardButton = createDiscardButton("Discard");

        // Set button events
        moveButton.setOnAction(e -> actionBarController.handleMoveAction());
        shoreUpButton.setOnAction(e ->actionBarController. handleShoreUpAction());
        giveCardButton.setOnAction(e -> actionBarController.handleGiveCardAction());
        moveOtherPlayerButton.setOnAction(e -> actionBarController.handleMoveOtherPlayerAction());
        captureTreasureButton.setOnAction(e -> actionBarController.handleCaptureTreasureAction());
        endTurnButton.setOnAction(e -> actionBarController.handleEndTurnAction());
        playSpecialButton.setOnAction(e -> actionBarController.handlePlaySpecialAction());
        drawFloodButton.setOnAction(e -> actionBarController.handleDrawFloodAction());
        discardButton.setOnAction(e -> actionBarController.handleDiscardAction());

        // Add label and button container to action bar
        actionBar.getChildren().addAll(actionLabel, new Region(), actionButtons);
        HBox.setHgrow(actionBar.getChildren().get(1), Priority.ALWAYS);

        updateActionBar();
    }

    private Button createMoveButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-weight: bold;");
        button.setPrefWidth(60);
        return button;
    }

    private Button createShoreUpButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        button.setPrefWidth(80);
        return button;
    }

    private Button createGiveCardButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white; -fx-font-weight: bold;");
        button.setPrefWidth(80);
        return button;
    }

    private Button createMoveOtherButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-font-weight: bold;");
        button.setPrefWidth(100);
        return button;
    }

    private Button createCaptureTreasureButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        button.setPrefWidth(120);
        return button;
    }

    private Button createEndTurnButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-font-weight: bold;");
        button.setPrefWidth(80);
        return button;
    }

    private Button createPlaySpecialButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #ff1744; -fx-text-fill: black; -fx-font-weight: bold;");
        button.setPrefWidth(100);
        return button;
    }

    private Button createDrawFloodButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #3F51B5; -fx-text-fill: white; -fx-font-weight: bold;");
        button.setPrefWidth(100);
        return button;
    }

    private Button createDiscardButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        button.setPrefWidth(80);
        return button;
    }

    /**
     * Update action bar status
     */
    public void updateActionBar() {
        if (actionButtons == null) {
            return;
        }
        actionButtons.getChildren().clear();
        if (actionBarController.getGameController().isGameOver()) {
            actionLabel.setText("Game Over!");
            return;
        }
        Player currentPlayer = actionBarController.getCurrentPlayer();
        Player currentProgramPlayer = actionBarController.getRoom().getCurrentProgramPlayer();
        int remainingActions = actionBarController.getRemainingActions();
        Room room = actionBarController.getRoom();
        // Check if current player exists
        if (currentPlayer == null) {
            actionLabel.setText("Waiting for game to start...");
            return;
        }

        // Update action label with player info
        actionLabel.setText("Current Player: " + currentPlayer.getName() +
                " | Actions: " + remainingActions);

        if (actionBarController.isAnyPlayerSunk()) {
            if (actionBarController.getIsland().getTile(currentProgramPlayer.getPosition()).getState() == Tile.TileState.SUNK) {
                actionLabel.setText("You are sunk! Please choose a tile to swim to.");
                actionBarController.handlePlayerSunk(currentProgramPlayer);
                actionButtons.getChildren().add(moveButton);
            } else {
                actionLabel.setText("Someone is sunk! Please wait for them to swim to a tile.");
            }
        } else {
            if (currentPlayer.getName().equals(room.getCurrentProgramPlayer().getName())) {
                if (currentPlayer.getCards().size() > 5) {
                    // Show alert dialog
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Discard Card");
                    alert.setHeaderText("You have more than 5 cards, please discard one or play a special card.");
                    alert.show();

                    actionButtons.getChildren().add(discardButton);
                    if (actionBarController.canPlaySpecialCard(currentPlayer)) {
                        actionButtons.getChildren().add(playSpecialButton);
                    }
                } else {
                    // Current player's turn
                    if (remainingActions > 0) {
                        // Still have remaining actions
                        actionLabel.setText("You may take " + remainingActions + " action(s)");
                        actionButtons.getChildren().add(moveButton);

                        // Check if can shore up tile
                        if (actionBarController.canShoreUpTile(currentPlayer)) {
                            actionButtons.getChildren().add(shoreUpButton);
                        }

                        if (actionBarController.canGiveCard(currentPlayer)) {
                            actionButtons.getChildren().add(giveCardButton);
                        }

                        // If player is Navigator, add move other player button
                        if (currentPlayer.getRole() == PlayerRole.NAVIGATOR) {
                            actionButtons.getChildren().add(moveOtherPlayerButton);
                        }

                        if (actionBarController.canCaptureTreasure(currentPlayer)) {
                            actionButtons.getChildren().add(captureTreasureButton);
                        }

                        actionButtons.getChildren().add(endTurnButton);

                        if (actionBarController.canPlaySpecialCard(currentPlayer)) {
                            actionButtons.getChildren().add(playSpecialButton);
                        }
                    } else if (!actionBarController.hasDrawnTreasureCards()) {
                        actionLabel.setText("Draw 2 Treasure Cards");
                        actionBarController.setHasDrawnTreasureCards(true);
                        actionBarController.sendDrawTreasureCardsMessage(2, currentPlayer);
                    } else if (actionBarController.getDrawnFloodCards() != 2) {
                        // Need to draw flood cards
                        actionLabel.setText("Draw A Flood Card or Play Special");
                        actionButtons.getChildren().addAll(drawFloodButton);
                        if (actionBarController.canPlaySpecialCard(currentPlayer)) {
                            actionButtons.getChildren().add(playSpecialButton);
                        }
                    } else {
                        // Turn end
                        actionBarController.nextTurn();
                    }
                }
            } else {
                // Other player's turn
                String status = "";
                if (remainingActions > 0) {
                    status = currentPlayer.getName() + " is taking actions (" + remainingActions + " remaining)";
                } else if (actionBarController.hasDrawnTreasureCards()) {
                    status = currentPlayer.getName() + " is drawing Treasure Cards";
                } else if (actionBarController.getDrawnFloodCards() != 2) {
                    status = currentPlayer.getName() + " is drawing Flood Cards";
                }
                actionLabel.setText(status);
                if (actionBarController.canPlaySpecialCard(room.getCurrentProgramPlayer())) {
                    actionButtons.getChildren().add(playSpecialButton);
                }
            }
        }

    }

    public void setActionBarController(ActionBarController actionBarController) {
        this.actionBarController = actionBarController;
    }

    /**
     * Close action bar view, clean up resources
     */
    public void shutdown() {
        // Clean up action bar resources
        if (actionBar != null) {
            actionBar.getChildren().clear();
        }
        // Clean up controller references
        actionBarController = null;
    }
}
