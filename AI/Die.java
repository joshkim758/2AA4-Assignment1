// Die.java
import java.util.Random;

public class Die {
    private static final Random RNG = new Random();

    public int roll() {
        return 1 + RNG.nextInt(6);
    }
}
