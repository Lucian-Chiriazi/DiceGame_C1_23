package diceGame.data;

import diceGame.business.Player;

import java.util.ArrayList;
import java.util.List;


public class DAOImplementation implements DAO {

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));

        return players;
    }

}
