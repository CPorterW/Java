import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
public class TurnManager {
    int turnNumber = 0;

    // The map of turns to functions. Each function is called when the turn is reached.
    Map <Integer, List<BiConsumer<Player, Player>>> turnMap = new HashMap<>();

    // A public method to add a turn effect to the map
    public void addToMap(Card card, Player player1, Player player2, Integer turn) {
        BiConsumer<Player, Player> cardEffect = (p1, p2) -> {
            // Call the card's effect method
            card.cardEffect(p1, p2, this);
        };
        // Get or initialize the list of turn effects for next turn
        turnMap.putIfAbsent(turn, new ArrayList<>());
        List<BiConsumer<Player, Player>> turnEffects = turnMap.get(turn);
        
        // Add the card effect to the turn effects for the specified turn
        turnEffects.add(cardEffect);
    }

    public void takeTurn(Player player1, Player player2) {
        ConsoleUtils.clearConsole();
        player1.drawCard();

        // The player will select the card.
        Card card = Menus.turnMenu(player1);
        if (card == null) {
            player1.health = 0;
            player2.health = 0;
            ConsoleUtils.printEffect("It's a draw!");
            return;
        }

        addToMap(card, player1, player2, this.turnNumber);
        executeTurnEffects(player1, player2);

        ConsoleUtils.printEffect("Your health: \t" + player1.healthBar() + "\nHis health: \t" + player2.healthBar());
    }

    public void executeTurnEffects(Player player1, Player player2) {
        if (turnMap.containsKey(this.turnNumber)) {
            List<BiConsumer<Player, Player>> turnEffects = turnMap.get(turnNumber);
    
            for (BiConsumer<Player, Player> effect : turnEffects) {
                effect.accept(player1, player2); // Execute the effect
            }
            
            // Remove the effects for this turn after executing
            turnMap.remove(this.turnNumber);
        }
        
        this.turnNumber++; // Move to the next turn
    }
}
