import java.io.IOException;

public class ConsoleUtils {
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            
        }
    }
    public static void printEffect(String effect) {
        clearConsole();
        System.out.println(effect);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        clearConsole();
    }
}