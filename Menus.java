import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menus {

    private static final Scanner scanner = new Scanner(System.in); // Reuse Scanner

    public static String[] namePicker() {
        System.out.println("Player 1, choose your name: ");
        String name1 = scanner.nextLine();
        System.out.println("Player 2, choose your name: ");
        String name2 = scanner.nextLine();
        return new String[]{name1, name2};
    }

    public static void mainMenu() {
        while (true) { // Allow returning to the main menu
            System.out.println("Main Menu");
            System.out.println("1. Play a Bot");
            System.out.println("2. Pass and Play");
            System.out.println("3. Tutorial");
            System.out.println("4. Exit");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> Main.playBot();
                case "2" -> Main.passAndPlay();
                case "3" -> Main.tutorial();
                case "4" -> {
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static Card turnMenu(Player player) {
        System.out.printf("%s, your turn.%n", player.name);
        int i = 1;

        Map<Integer, Card> options = new HashMap<>();
        for (Card card : player.hand) {
            System.out.printf("%d. %s - %d%n", i, card.name, card.damage);
            options.put(i, card); // Maps menu number to card
            i++;
        }

        Card selection = null;

        while (selection == null) {
            System.out.printf("%nEnter the number of the card to play, or 0 to exit: ");
            String selectionString = scanner.nextLine();

            try {
                int selectedOption = Integer.parseInt(selectionString);
                if (selectedOption == 0) {
                    System.out.println("You chose to exit your turn.");
                    return null; // Exit signal
                }
                selection = options.get(selectedOption);
                if (selection == null) {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        player.hand.remove(selection);
        return selection;
    }
}
