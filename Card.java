public abstract class Card {
    public String name;
    public String description;
    public int damage = 0;
    public abstract void cardEffect(Player player1, Player player2, TurnManager turnManager);
}
