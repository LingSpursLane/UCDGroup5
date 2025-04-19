package com.island.controller;

import java.util.*;

public class GameController {
    private GameView gameView;
    private RoomController roomController;
    private Room room;
    private Island island;
    private IslandController islandController;

    private PlayerController playerController;
    private CardController cardController;
    private ActionBarController actionBarController;
    private Player currentPlayer;

    private List<Player> helicopterPlayers; // 要使用直升机移动的玩家
    private Card activeSpecialCard; // 当前正在使用的特殊卡

    private int remainingActions = 3;
    private boolean gameStart = false;
    private boolean gameOver = false;

    public boolean receiveMessage = true;
    // Observer pattern implementation
    private GameSubjectImpl gameSubject;

    public GameController(RoomController roomController) {
        this.roomController = roomController;
        roomController.setGameController(this);
        room = roomController.getRoom();

        gameSubject = new GameSubjectImpl();

        islandController = new IslandController();
        islandController.setGameController(this);
        island = islandController.getIsland();

        // 设置岛屿引用到RoomController
        roomController.setIsland(island);

        playerController = new PlayerController();
        playerController.setGameController(this);
        cardController = new CardController();
        cardController.setGameController(this);
        actionBarController = new ActionBarController();
        actionBarController.setGameController(this);
    }

    public void handlePlayerJoin(Message message) throws Exception {

    }

    public RoomController getRoomController() {
        return roomController;
    }

    public void shutdown() {

    }

    public void startGame(long seed) {

    }

    public void startTurn(Player player) {

    }

    public IslandController getIslandController() {
        return islandController;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public CardController getCardController() {
        return cardController;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ActionBarController getActionBarController() {
        return actionBarController;
    }

    public int getRemainingActions() {
        return remainingActions;
    }

    public void decreaseRemainingActions() {
        remainingActions--;
    }

    public void setRemainingActions(int remainingActions) {
        this.remainingActions = remainingActions;
    }

    public Room getRoom() {
        return room;
    }

    public Island getIsland() {
        return islandController.getIsland();
    }

    public Player getCurrentProgramPlayer() {
        return room.getCurrentProgramPlayer();
    }

    public void handleWaterRise() {

    }

    public MessageHandler getMessageHandler() {
        return roomController.getMessageHandler();
    }

    public void handleDrawTreasureCard(int count, Player player) {

    }

    public Tile getChosenTile() {
        return islandController.getChosenTile();
    }

    public void giveCard(Player fromPlayer, Player toPlayer, String card) {

    }

    public void handlePlaySpecialAction() {

    }

    private void handleSandbagsCard(Card chosenCard) {

    }

    private void handleHelicopterCard(Card chosenCard) {

    }

    public void handleUseSpecialCard(Position position) {

    }

    private void executeSandbagsUse(Position position) {

    }

    private void executeHelicopterMove(Position position) {

    }

    public void addTreasureDiscardPile(Card card) {
        cardController.addTreasureDiscardPile(card);
    }

    public List<Position> drawFloodCards(int count) {
        return null;
    }

    public void handleDiscardAction() {

    }

    public void nextTurn() {

    }

    public void handlePlayerSunk(Player currentProgramPlayer) {

    }

    // 获取玩家可移动的板块
    public List<Tile> getValidTilesOnSunk(Player player) {
        return null;
    }

    public void showToast(String message) {

    }

    public void showSuccessToast(String message) {

    }

    public void showWarningToast(String message) {

    }

    public void showErrorToast(String message) {
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setWaterLevel(int waterLevel) {
        islandController.setWaterLevel(waterLevel);
    }

    public void returnToMainMenu() {
    }

    public void resetTileBorders() {
    }

    public boolean isGameStart() {
        return gameStart;
    }
}

