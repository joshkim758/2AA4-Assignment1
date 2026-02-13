// Edge.java
public class Edge {
    private final int id;
    private final int a; // node endpoint id
    private final int b; // node endpoint id

    private int roadOwner; // -1 means empty

    public Edge(int id, int a, int b) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.roadOwner = -1;
    }

    public int id() {
        return id;
    }

    public int a() {
        return a;
    }

    public int b() {
        return b;
    }

    public boolean empty() {
        return roadOwner < 0;
    }

    public int roadOwner() {
        return roadOwner;
    }

    public void setRoadOwner(int playerId) {
        this.roadOwner = playerId;
    }
}
