package com.island.controller;

import java.util.*;

public class PlayerController {
    private GameController gameController;
    private Room room;
    private Card chosenCard;

    public PlayerController() {

    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        room = gameController.getRoomController().getRoom();
    }

    public void initPlayers(long seed) {

    }

    public void dealCards(Deque<Card> treasureDeck) {

    }

    public Room getRoom() {
        return room;
    }

    public void setChosenCard(Card chosenCard) {
        this.chosenCard = chosenCard;
    }

    public Card getChosenCard() {
        return chosenCard;
    }

    public boolean canPlaySpecialCard(Player player) {

        return false;
    }

    public boolean canShoreUpTile(Player player) {
        return false;
    }

    public boolean canGiveCard(Player player) {

        return false;
    }

    public boolean canCaptureTreasure(Player player) {

        return false;
    }

    public boolean hasDrawnTreasureCards() {
    }

    public int getDrawnFloodCards() {
    }

    public void setHasDrawnTreasureCards(boolean hasDrawnTreasureCards) {

    }

    public void addDrawnFloodCards(int count) {
    }

    public void resetPlayerState() {
    }

    public void shutdown() {

    }

    public void movePlayer(Player player, Position position) {

    }

    public void addCard(Player player, Card card) {

    }

    public void removeCard(Player player, Card card) {

    }
}

