import java.util.Random;
public class Randomizers {
    static Random random = new Random();
    public static boolean CoinFlip() {
        return random.nextInt(2) == 1;
    }
    public static int RandomNumber(int max) {
        return random.nextInt(max);
    }
}
