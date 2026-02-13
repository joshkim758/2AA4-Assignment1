// Bank.java
import java.util.EnumMap;

public class Bank {
    private final EnumMap<Resource, Integer> counts;

    public Bank() {
        this.counts = new EnumMap<>(Resource.class);
        // Classic Catan bank has 19 of each resource; adjust as needed.
        for (Resource r : Resource.values()) {
            counts.put(r, 19);
        }
    }

    public boolean canGive(Resource r, int n) {
        if (r == null || n <= 0) return false;
        return counts.getOrDefault(r, 0) >= n;
    }

    public void give(Player p, Resource r, int n) {
        if (p == null || r == null || n <= 0) return;
        if (!canGive(r, n)) return;

        counts.put(r, counts.get(r) - n);
        p.addResource(r, n);
    }
}
