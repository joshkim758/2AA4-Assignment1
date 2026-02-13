package Assignment1PAPYRUS;

public class Main {
    public static void main(String[] args) {
        //congiruing the max turns
        int MAX_TURNS = 8192; //upper limit of the max turns

        //game components
        Board board = new Board();
        Bank bank = new Bank();
        TurnManager turnManager = new TurnManager();
        Dice dice = new DicePair();

        //setting up the 4 players
        Player[] players = new Player[4];
        for(int i=0; i<4; i++) players[i] = new Player(i);

        //create and run the game logic
        Game game = new Game(board, bank, turnManager, dice, players);

        //finished
        System.out.println("Starting Catan Simulation...");
        game.run(MAX_TURNS);
        System.out.println("Simulation Ended.");
    }
}
