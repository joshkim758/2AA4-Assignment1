// Game.java
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final Board board;
    private final Bank bank;
    private final TurnManager turns;
    private final Dice dice;
    private final List<Player> players;

    // Basic costs (classic Catan)
    private static final Map<Resource, Integer> ROAD_COST = cost(Resource.WOOD, 1, Resource.BRICK, 1);
    private static final Map<Resource, Integer> SETTLEMENT_COST = cost(Resource.WOOD, 1, Resource.BRICK, 1, Resource.SHEEP, 1, Resource.WHEAT, 1);
    private static final Map<Resource, Integer> CITY_COST = cost(Resource.WHEAT, 2, Resource.ORE, 3);

    public Game(Board board, Bank bank, TurnManager turns, Dice dice, Player[] players) {
        this.board = board;
        this.bank = bank;
        this.turns = turns;
        this.dice = dice;
        this.players = new ArrayList<>();
        if (players != null) {
            for (Player p : players) this.players.add(p);
        }
    }

    public void run(int maxTurns) {
        if (maxTurns <= 0) return;
        if (players.isEmpty()) return;

        for (int t = 0; t < maxTurns; t++) {
            Player current = players.get(turns.currentPlayerIndex());

            // Roll dice (placeholder; distribution & robber not implemented)
            int roll = dice.roll();
            // You can later distribute resources based on roll+tile ownership.

            // Player acts
            current.takeTurn(this);

            // End turn
            turns.nextTurn(players.size());

            // Example: switch to MAIN after first round (placeholder)
            if (t == players.size() - 1) {
                turns.setPhase(Phase.MAIN);
            }
        }
    }

    public Board board() {
        return board;
    }

    public Bank bank() {
        return bank;
    }

    public Phase phase() {
        return turns.phase();
    }

    public boolean buildRoad(Player p, int edgeId) {
        if (p == null) return false;
        if (!p.hasRoadPiece()) return false;

        if (!board.canPlaceRoad(p.id(), edgeId)) return false;

        // In setup phase, many variants allow free placement; we keep it simple:
        if (turns.phase() == Phase.MAIN) {
            if (!p.hasResources(ROAD_COST)) return false;
            p.pay(ROAD_COST);
        }

        board.placeRoad(p.id(), edgeId);
        p.useRoadPiece();
        return true;
    }

    public boolean buildSettlement(Player p, int nodeId) {
        if (p == null) return false;
        if (!p.hasSettlementPiece()) return false;

        if (!board.canPlaceSettlement(p.id(), nodeId, turns.phase())) return false;

        if (turns.phase() == Phase.MAIN) {
            if (!p.hasResources(SETTLEMENT_COST)) return false;
            p.pay(SETTLEMENT_COST);
        }

        board.placeSettlement(p.id(), nodeId);
        p.useSettlementPiece();
        p.addVP(1);
        return true;
    }

    public boolean upgradeToCity(Player p, int nodeId) {
        if (p == null) return false;
        if (!p.hasCityPiece()) return false;

        if (!board.canUpgradeToCity(p.id(), nodeId)) return false;

        if (!p.hasResources(CITY_COST)) return false;
        p.pay(CITY_COST);

        board.upgradeToCity(p.id(), nodeId);
        p.upgradeSettlementToCityPiece();
        p.addVP(1); // settlement->city adds +1 VP (2 total on that node)
        return true;
    }

    private static Map<Resource, Integer> cost(Object... pairs) {
        EnumMap<Resource, Integer> m = new EnumMap<>(Resource.class);
        for (Resource r : Resource.values()) m.put(r, 0);

        for (int i = 0; i + 1 < pairs.length; i += 2) {
            Resource r = (Resource) pairs[i];
            Integer n = (Integer) pairs[i + 1];
            m.put(r, n);
        }
        return m;
    }
}
