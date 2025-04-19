package com.island.controller;


import java.util.*;

public class CardController {
    private GameController gameController;
    // 卡牌管理相关字段
    private final Deque<Card> treasureDeck;
    private final Deque<Card> floodDeck;
    private final List<Card> treasureDiscardPile;
    private final List<Card> floodDiscardPile;
    private long seed;
    private Island island;


    public CardController() {
        this.treasureDeck = new ArrayDeque<>();
        this.floodDeck = new ArrayDeque<>();
        this.treasureDiscardPile = new ArrayList<>();
        this.floodDiscardPile = new ArrayList<>();
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        island = gameController.getIslandController().getIsland();
    }

    public void initCards(long seed) {

    }

    public List<Position> drawFloodCards(int count) {
        return null;
    }

    public void drawTreasureCard(int count, Player player) {

    }

    private void shuffleDecks() {
    }

    public Deque<Card> getTreasureDeck() {
        return treasureDeck;
    }

    public Deque<Card> getFloodDeck() {
        return floodDeck;
    }

    public List<Card> getFloodDiscardPile() {
        return floodDiscardPile;
    }

    public List<Card> getTreasureDiscardPile() {
        return treasureDiscardPile;
    }

    public void addTreasureDiscardPile(Card card) {
        treasureDiscardPile.add(card);
    }

    public void handleWaterRise() {
    }

    public void shutdown() {
    }
}
