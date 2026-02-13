// MultiDice.java
public class MultiDice implements Dice {
    private final Die d1;
    private final Die d2;

    public MultiDice(Die d1, Die d2) {
        this.d1 = d1;
        this.d2 = d2;
    }

    @Override
    public int roll() {
        return d1.roll() + d2.roll();
    }
}
