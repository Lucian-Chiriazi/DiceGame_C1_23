package diceGame.data;

import diceGame.business.Player;

import java.util.ArrayList;

public class DAOImplementation implements DAO {

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));

        return players;
    }

}
