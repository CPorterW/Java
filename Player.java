import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {

    //#region Attributes
    public String name = "Huds";
    private final int deckSize = 20;
    private final int initialHandSize = 4;
    public final int maxHealth = 75;
    public int health = maxHealth;
    public List<Card> deck = new ArrayList<>();
    public List<Card> hand = new ArrayList<>();

    public static final String RESET = "\u001B[0m"; // Resets color
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    //#endregion 

    //#region Constructors
    Player(String name) {
        this.name = YELLOW + name + RESET;
        populateDeck();
        populateInitialHand();
    }
    //#endregion

    private void populateDeck() {
        this.deck = new Cards().generateRandomDeck(this.deckSize);
    }
    private void populateInitialHand() {
        Iterator<Card> iterator = this.deck.iterator();
        while (iterator.hasNext() && hand.size() < this.initialHandSize) {
            Card card = iterator.next();
            hand.add(card);
            iterator.remove(); // Safely remove the card from the deck
        }
    }
    
    public void drawCard() {
        Card card = deck.get(0);
        hand.add(card);
        deck.remove(card);
    }

    public String healthBar() {
        int i = 0;
        String healthBar = "";
        while (i < this.maxHealth) {
            i++;
            if (i <= this.health) {
                healthBar += GREEN + "=" + RESET;
            }
            else {
                healthBar += RED + "=" + RESET;
            }
        }
        return healthBar;
    }
}