// Tile.java
public class Tile {
    private final int id;
    private final Terrain terrain;
    private final Integer number; // null allowed for desert

    public Tile(int id, Terrain terrain, Integer number) {
        this.id = id;
        this.terrain = terrain;
        this.number = number;
    }

    public int id() {
        return id;
    }

    public Terrain terrain() {
        return terrain;
    }

    public Integer number() {
        return number;
    }
}
