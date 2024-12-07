public class Main {
    public static void main(String[] args) {
        ConsoleUtils.clearConsole();
        Menus.mainMenu();
    }
    public static void playBot() {
        ConsoleUtils.printEffect("Under Construction...");
    }
    public static void passAndPlay() {
        ConsoleUtils.clearConsole();
        String[] nameList = Menus.namePicker();
        Player player1 = new Player(nameList[0]);
        Player player2 = new Player(nameList[1]);
        TurnManager turnManager = new TurnManager();
        while (true) {
            turnManager.takeTurn(player1, player2);
            if (!(player1.health > 0 && player2.health > 0)) break;
            ConsoleUtils.printEffect("Please pass to your opponent.");
            turnManager.takeTurn(player2, player1);
            if (!(player1.health > 0 && player2.health > 0)) break;
            ConsoleUtils.printEffect("Please pass to your opponent.");
        }
        if (player1.health > 0) {
            ConsoleUtils.printEffect("Player 1 wins!");
        } else if (player2.health > 0){
            ConsoleUtils.printEffect("Player 2 wins!");
        }
    }
    public static void tutorial() {
        ConsoleUtils.printEffect("Under Construction...");
    }
}

