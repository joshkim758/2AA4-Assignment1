// Player.java
import java.util.EnumMap;
import java.util.Map;

public class Player {
    private final int id;
    private final EnumMap<Resource, Integer> hand;

    private int roadsLeft;
    private int settlementsLeft;
    private int citiesLeft;

    private int victoryPoints;

    public Player(int id) {
        this.id = id;
        this.hand = new EnumMap<>(Resource.class);
        for (Resource r : Resource.values()) {
            hand.put(r, 0);
        }

        // Typical initial piece counts; adjust as needed.
        this.roadsLeft = 15;
        this.settlementsLeft = 5;
        this.citiesLeft = 4;

        this.victoryPoints = 0;
    }

    public int id() {
        return id;
    }

    public int vp() {
        return victoryPoints;
    }

    public void addResource(Resource r, int n) {
        if (r == null || n <= 0) return;
        hand.put(r, hand.getOrDefault(r, 0) + n);
    }

    public boolean hasResources(Map<Resource, Integer> cost) {
        if (cost == null) return false;
        for (Map.Entry<Resource, Integer> e : cost.entrySet()) {
            Resource r = e.getKey();
            int need = e.getValue() == null ? 0 : e.getValue();
            if (need <= 0) continue;
            if (hand.getOrDefault(r, 0) < need) return false;
        }
        return true;
    }

    public void pay(Map<Resource, Integer> cost) {
        if (cost == null) return;
        if (!hasResources(cost)) return;

        for (Map.Entry<Resource, Integer> e : cost.entrySet()) {
            Resource r = e.getKey();
            int need = e.getValue() == null ? 0 : e.getValue();
            if (need <= 0) continue;
            hand.put(r, hand.getOrDefault(r, 0) - need);
        }
    }

    public void addVP(int n) {
        if (n <= 0) return;
        victoryPoints += n;
    }

    public boolean hasSettlementPiece() {
        return settlementsLeft > 0;
    }

    public boolean hasCityPiece() {
        return citiesLeft > 0;
    }

    public boolean hasRoadPiece() {
        return roadsLeft > 0;
    }

    /* Package-friendly helpers for piece consumption */
    void useRoadPiece() {
        if (roadsLeft > 0) roadsLeft--;
    }

    void useSettlementPiece() {
        if (settlementsLeft > 0) settlementsLeft--;
    }

    void upgradeSettlementToCityPiece() {
        // In Catan: returning a settlement piece to supply, consuming a city piece.
        if (citiesLeft > 0) {
            citiesLeft--;
            settlementsLeft++;
        }
    }

    /**
     * Very simple placeholder "AI" turn. Replace with your command pattern later.
     */
    public void takeTurn(Game game) {
        if (game == null) return;

        // Placeholder: do nothing. You can insert scripted actions here.
        // Example (commented):
        // game.buildRoad(this, 0);
        // game.buildSettlement(this, 0);
        // game.upgradeToCity(this, 0);
    }
}
