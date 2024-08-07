package diceGame.data;

import diceGame.business.Player;

import java.util.ArrayList;
import java.util.List;


public class DAOImplementation implements DAO {

    private List<Player> players;

    public DAOImplementation() {
         this.players = new ArrayList<>();
         load();
    }

    public List<Player> getPlayers() {

        return players;
    }

    private void load() {
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));
    }

}
