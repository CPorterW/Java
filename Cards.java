
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Cards {
    class SwordOfLaban extends Card {
        public SwordOfLaban() {
            this.name = "Sword of Laban";
            this.description = "The sword that smote off Laban's head, slew many Lamanites, and was briefly given to Joseph Smith.";
            this.damage = 7;
        }
        @Override
        public void cardEffect(Player player1, Player player2, TurnManager turnManager) {
            player2.health -= this.damage;

            ConsoleUtils.printEffect("A slash draws blood, and he dances back, wincing, out of your reach.");
        }
    }
    class TeancumsJavelin extends Card {
        public TeancumsJavelin() {
            this.name = "Teancum's Javelin";
            this.description = "The wieldy weapon Teancum used to slay two wicked kings, but in slaying the second, his rage caused his death. May backfire.";
            this.damage = 14;
        }
        @Override
        public void cardEffect(Player player1, Player player2, TurnManager turnManager) {
            player2.health -= this.damage;
            int backfireDmg = Randomizers.CoinFlip() ? this.damage : 0;
            player1.health -= backfireDmg;
            ConsoleUtils.printEffect("You blend into the shadows and find your opponent's vulnerability.");
            if (backfireDmg > 0) {
                ConsoleUtils.printEffect("In your anger, you toss your javelin, forgetting stealth and allowing a counterstrike.");
            }
            else {
                ConsoleUtils.printEffect("You deftly pierce the leather between plates and the flesh beneath, then slip back into the shadows.");
            }
            
        }
    }
    class PoisonByDegrees extends Card {
        public PoisonByDegrees() {
            this.name = "Poison by Degrees";
            this.description = "How Amalackiah slew Lehonti and gained control of his army, and later, the kingdom. Stackable and permanently recurring.";
            this.damage = 1;
        }
        @Override
        public void cardEffect(Player player1, Player player2, TurnManager turnManager) {
            player2.health -= this.damage;
        
            // Schedule the effect for next turn
            int nextTurn = turnManager.turnNumber + 2;
            turnManager.addToMap(this, player1, player2, nextTurn);
            ConsoleUtils.printEffect("...");
            ConsoleUtils.printEffect("The poison creeps through his veins, inching towards his heart.");
        }
    }
    class NephisBow extends Card {
        public NephisBow() {
            this.name = "Nephi's Bow";
            this.description = "Handcrafted in the wilderness by guidance from God to stave off his family's starvation. Hits next turn.";
            this.damage = 10;
        }
        List<Integer> turnToHit = new ArrayList<>();
        // This card will hit the player on the next turn
@Override
public void cardEffect(Player player1, Player player2, TurnManager turnManager) {
    if (turnToHit.isEmpty()) {
        // First time playing the card
        int nextTurn = turnManager.turnNumber + 2;
        turnToHit.add(nextTurn);
        turnManager.addToMap(this, player1, player2, nextTurn);
        ConsoleUtils.printEffect("An arrow is shot high into the air.");
    } else {
        // Check if current turn matches the turn to hit
        if (turnToHit.contains(turnManager.turnNumber)) {
            player2.health -= this.damage;
            turnToHit.clear(); // Remove the turn after hitting
            ConsoleUtils.printEffect("An arrow falls from the sky and finds a gap in his armor.");
        }
    }
}
    }


    public List<Card> generateRandomDeck(int deckSize) {
        List<Card> deck = new ArrayList<>();
        Random random = new Random();

        // Possible card types
        Card[] cardTypes = {
            new SwordOfLaban(),
            new TeancumsJavelin(),
            new PoisonByDegrees(),
            new NephisBow()
        };

        for (int i = 0; i < deckSize; i++) {
            // Randomly select a card type
            Card randomCard = cardTypes[random.nextInt(cardTypes.length)];

            // Add a new instance of the card to the deck
            if (randomCard instanceof SwordOfLaban) {
                deck.add(new SwordOfLaban());
            } else if (randomCard instanceof TeancumsJavelin) {
                deck.add(new TeancumsJavelin());
            } else if (randomCard instanceof PoisonByDegrees) {
                deck.add(new PoisonByDegrees());
            } else if (randomCard instanceof NephisBow) {
                deck.add(new NephisBow());
            }
        }

        return deck;
    }
    
}
