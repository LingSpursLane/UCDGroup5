package com.island.controller;

import java.util.*;

public class IslandController {
    private Island island;
    private GameController gameController;
    private Room room;
    private int waterLevel;
    private Tile chosenTile;
    private String[] treasures = new String[] { "Earth", "Wind", "Fire", "Ocean" };

    public IslandController() {
        this.island = new Island();
        chosenTile = null;
        waterLevel = 1;
    }

    public void initIsland(long seed) {

    }

    private void addTile(String name, Position position, TreasureType type) {

    }

    public Island getIsland() {
        return island;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        this.room = gameController.getRoomController().getRoom();
    }

    public Room getRoom() {
        return room;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void increaseWaterLevel() {

    }

    public void handleTileClick(Tile tile) {

    }

    public Tile getChosenTile() {
        return chosenTile;
    }

    public GameController getGameController() {
        return gameController;
    }

    public String[] getTreasures() {
        return treasures;
    }

    public void removeTreasure(String treasureName) {

    }

    public boolean checkTreasureTiles() {

        return true;
    }

    public boolean checkFoolsLanding() {

        return true;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void shoreUpTile(Player player, Position position) {

    }

    public void captureTreasure(Player player, TreasureType treasureType) {

    }

    public void floodTile(Position position) {

    }

    public void shoreUp(Position position) {

    }
}
