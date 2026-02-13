// Node.java
public class Node {
    private final int id;

    // Stored as int for simplicity; -1 means unowned
    private int owner;
    private BuildingType building; // null means empty

    public Node(int id) {
        this.id = id;
        this.owner = -1;
        this.building = null;
    }

    public int id() {
        return id;
    }

    public boolean empty() {
        return building == null;
    }

    public int owner() {
        return owner;
    }

    public BuildingType building() {
        return building;
    }

    public void setBuilding(int playerId, BuildingType t) {
        if (t == null) return;
        this.owner = playerId;
        this.building = t;
    }

    public void clear() {
        this.owner = -1;
        this.building = null;
    }
}
