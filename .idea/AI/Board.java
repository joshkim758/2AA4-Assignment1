// Board.java
import java.util.Arrays;

public class Board {
    private final Tile[] tiles;      // [19]
    private final Node[] nodes;      // [*]
    private final Edge[] edges;      // [*]
    private final int[][] tileNodes; // [19][6] mapping: tileId -> 6 node ids

    public Board(Tile[] tiles, Node[] nodes, Edge[] edges, int[][] tileNodes) {
        this.tiles = tiles == null ? new Tile[0] : tiles;
        this.nodes = nodes == null ? new Node[0] : nodes;
        this.edges = edges == null ? new Edge[0] : edges;
        this.tileNodes = tileNodes == null ? new int[0][0] : tileNodes;
    }

    public Tile tile(int tileId) {
        if (tileId < 0 || tileId >= tiles.length) return null;
        return tiles[tileId];
    }

    public Node node(int nodeId) {
        if (nodeId < 0 || nodeId >= nodes.length) return null;
        return nodes[nodeId];
    }

    public Edge edge(int edgeId) {
        if (edgeId < 0 || edgeId >= edges.length) return null;
        return edges[edgeId];
    }

    public int[] nodesAroundTile(int tileId) {
        if (tileId < 0 || tileId >= tileNodes.length) return new int[0];
        return Arrays.copyOf(tileNodes[tileId], tileNodes[tileId].length);
    }

    public boolean canPlaceRoad(int playerId, int edgeId) {
        Edge e = edge(edgeId);
        if (e == null) return false;
        if (!e.empty()) return false;

        // Placeholder rule: allow any empty edge.
        // Later: enforce adjacency to player's existing road/building.
        return true;
    }

    public boolean canPlaceSettlement(int playerId, int nodeId, Phase phase) {
        Node n = node(nodeId);
        if (n == null) return false;
        if (!n.empty()) return false;

        // Placeholder rule:
        // - Setup: allow any empty node
        // - Main: also allow any empty node (later: enforce distance rule + connectivity)
        return phase != null;
    }

    public boolean canUpgradeToCity(int playerId, int nodeId) {
        Node n = node(nodeId);
        if (n == null) return false;

        return n.building() == BuildingType.SETTLEMENT && n.owner() == playerId;
    }

    public void placeRoad(int playerId, int edgeId) {
        Edge e = edge(edgeId);
        if (e == null) return;
        e.setRoadOwner(playerId);
    }

    public void placeSettlement(int playerId, int nodeId) {
        Node n = node(nodeId);
        if (n == null) return;
        n.setBuilding(playerId, BuildingType.SETTLEMENT);
    }

    public void upgradeToCity(int playerId, int nodeId) {
        Node n = node(nodeId);
        if (n == null) return;
        n.setBuilding(playerId, BuildingType.CITY);
    }
}
