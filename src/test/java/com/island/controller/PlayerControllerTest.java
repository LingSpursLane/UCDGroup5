package com.island.controller;


import com.island.models.Room;
import com.island.models.adventurers.Diver;
import com.island.models.adventurers.Player;
import com.island.models.adventurers.PlayerRole;
import com.island.models.card.Card;
import com.island.models.card.CardType;
import com.island.models.island.Island;
import com.island.models.island.Position;
import com.island.models.island.Tile;
import com.island.models.treasure.TreasureType;
import com.island.network.RoomController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PlayerController class.
 */
public class PlayerControllerTest {
    @Mock
    private GameController gameController;
    @Mock
    private Room room;
    @Mock
    private Player player;
    @Mock
    private Card card;
    @Mock
    private Island island;
    @Mock
    private Tile tile;

    private PlayerController playerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playerController = new PlayerController();
        when(gameController.getRoomController()).thenReturn(mock(RoomController.class));
        when(gameController.getRoomController().getRoom()).thenReturn(room);
        when(gameController.getIslandController()).thenReturn(mock(IslandController.class));
        when(gameController.getIslandController().getIsland()).thenReturn(island);
        playerController.setGameController(gameController);
    }

    /**
     * Test initPlayers method.
     */
    @Test
    void testInitPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Diver("A"));
        players.add(new Diver("B"));
        when(room.getPlayers()).thenReturn(players);
        when(gameController.getCurrentPlayer()).thenReturn(players.get(0));
        when(room.isHost(anyString())).thenReturn(true);
        playerController.initPlayers(123L);
        // As long as no exceptions are thrown, the test passes
    }

    /**
     * Test dealCards method.
     */
    @Test
    void testDealCards() {
        Deque<Card> deck = new ArrayDeque<>();
        Card treasureCard1 = mock(Card.class);
        Card treasureCard2 = mock(Card.class);
        Card waterRiseCard = mock(Card.class);
        
        // Setup treasure cards
        when(treasureCard1.getType()).thenReturn(CardType.TREASURE);
        when(treasureCard2.getType()).thenReturn(CardType.TREASURE);
        deck.add(treasureCard1);
        deck.add(treasureCard2);
        
        // Setup water rise card
        when(waterRiseCard.getType()).thenReturn(CardType.WATER_RISE);
        deck.add(waterRiseCard);

        List<Player> players = new ArrayList<>();
        players.add(player);

        // Create a list to track player's cards
        List<Card> playerCards = new ArrayList<>();
        when(room.getPlayers()).thenReturn(players);
        when(player.getCards()).thenReturn(playerCards);
        doAnswer(invocation -> {
            Card card = invocation.getArgument(0);
            playerCards.add(card);
            return null;
        }).when(player).addCard(any());

        playerController.dealCards(deck);

        // Verify treasure cards were added to player's hand
        verify(player).addCard(treasureCard1);
        verify(player).addCard(treasureCard2);
        // Verify water rise card was not added to player's hand
        verify(player, never()).addCard(waterRiseCard);
        // Verify player has correct number of cards
        assertEquals(2, playerCards.size());
    }

    /**
     * Test canMovePlayer method.
     */
    @Test
    void testCanPlaySpecialCard() {
        List<Card> cards = new ArrayList<>();
        Card c = Card.createSpecialCard(CardType.HELICOPTER);
        cards.add(c);
        when(player.getCards()).thenReturn(cards);
        assertTrue(playerController.canPlaySpecialCard(player));
    }

    /**
     * Test canMovePlayer method.
     */
    @Test
    void testCanShoreUpTile() {
        Map<Position, Tile> tiles = new HashMap<>();
        tiles.put(new Position(0, 0), mock(Tile.class));
        when(island.getTiles()).thenReturn(tiles);
        when(gameController.getIsland()).thenReturn(island);
        when(player.getShorePositions(any())).thenReturn(List.of(new Position(0,0)));
        assertTrue(playerController.canShoreUpTile(player));
    }

    /**
     * Test canMovePlayer method.
     */
    @Test
    void testCanGiveCard() {
        when(player.getCards()).thenReturn(List.of(card));
        when(player.getRole()).thenReturn(PlayerRole.MESSENGER);
        assertTrue(playerController.canGiveCard(player));
    }

    /**
     * Test canCaptureTreasure method.
     */
    @Test
    void testCanCaptureTreasure() {
        when(player.getPosition()).thenReturn(new Position(0,0));
        when(island.getTile(any())).thenReturn(tile);
        when(tile.getTreasureType()).thenReturn(TreasureType.EARTH_STONE);
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Card c = Card.createTreasureCard(TreasureType.EARTH_STONE, "");
            cards.add(c);
        }
        when(player.getCards()).thenReturn(cards);
        when(gameController.getIsland()).thenReturn(island);
        assertTrue(playerController.canCaptureTreasure(player));
    }

    /**
     * Test hasDrawnTreasureCards method.
     */
    @Test
    void testHasDrawnTreasureCards() {
        when(room.getCurrentProgramPlayer()).thenReturn(player);
        when(player.isHasDrawnTreasureCards()).thenReturn(true);
        assertTrue(playerController.hasDrawnTreasureCards());
    }

    /**
     * Test getDrawnFloodCards method.
     */
    @Test
    void testGetDrawnFloodCards() {
        when(room.getCurrentProgramPlayer()).thenReturn(player);
        when(player.getDrawnFloodCards()).thenReturn(2);
        assertEquals(2, playerController.getDrawnFloodCards());
    }

    /**
     * Test setHasDrawnTreasureCards method.
     */
    @Test
    void testSetHasDrawnTreasureCards() {
        when(room.getCurrentProgramPlayer()).thenReturn(player);
        doNothing().when(player).setHasDrawnTreasureCards(true);
        playerController.setHasDrawnTreasureCards(true);
        verify(player).setHasDrawnTreasureCards(true);
    }

    /**
     * Test addDrawnFloodCards method.
     */
    @Test
    void testAddDrawnFloodCards() {
        when(room.getCurrentProgramPlayer()).thenReturn(player);
        doNothing().when(player).addDrawnFloodCards(1);
        playerController.addDrawnFloodCards(1);
        verify(player).addDrawnFloodCards(1);
    }

    /**
     * Test resetPlayerState method.
     */
    @Test
    void testResetPlayerState() {
        when(room.getCurrentProgramPlayer()).thenReturn(player);
        doNothing().when(player).resetState();
        playerController.resetPlayerState();
        verify(player).resetState();
    }
}
