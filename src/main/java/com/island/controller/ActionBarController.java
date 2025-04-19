package com.island.controller;

public class ActionBarController {
    private GameController gameController;
    private Player currentPlayer;

    public ActionBarController() {

    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        currentPlayer = gameController.getCurrentPlayer();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getRemainingActions() {
        return gameController.getRemainingActions();
    }

    public Room getRoom() {
        return gameController.getRoom();
    }

    public GameController getGameController() {
        return gameController;
    }

    public Island getIsland() {
        return gameController.getIsland();
    }

    public boolean canPlaySpecialCard(Player player) {
    }

    public boolean canShoreUpTile(Player player) {
    }

    public boolean canGiveCard(Player player) {
    }

    public boolean canCaptureTreasure(Player player) {
    }

    public boolean hasDrawnTreasureCards() {
    }

    public void sendDrawTreasureCardsMessage(int i, Player player) {
    }

    public int getDrawnFloodCards() {
    }

    public void handleMoveAction() {

    }

    public void handleShoreUpAction() {

    }

    public void handleGiveCardAction() {

    }

    public void handleMoveOtherPlayerAction() {
    }

    public void handleCaptureTreasureAction() {
    }

    public void handleEndTurnAction() {
    }

    public void handlePlaySpecialAction() {
        gameController.handlePlaySpecialAction();
    }

    public void handleDrawFloodAction() {
    }

    public void setHasDrawnTreasureCards(boolean b) {
        gameController.getPlayerController().setHasDrawnTreasureCards(b);
    }

    public void handleDiscardAction() {
        gameController.handleDiscardAction();
    }

    public void nextTurn() {
        gameController.nextTurn();
    }

    public boolean isAnyPlayerSunk() {
        return false;
    }

    public void handlePlayerSunk(Player currentProgramPlayer) {
    }

    public void shutdown() {
    }

}
