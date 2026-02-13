// TurnManager.java
public class TurnManager {
    private int current;
    private Phase phase;

    public TurnManager() {
        this.current = 0;
        this.phase = Phase.SETUP;
    }

    public int currentPlayerIndex() {
        return current;
    }

    public Phase phase() {
        return phase;
    }

    public void nextTurn(int nPlayers) {
        if (nPlayers <= 0) return;
        current = (current + 1) % nPlayers;
    }

    public void setPhase(Phase p) {
        if (p == null) return;
        this.phase = p;
    }
}
