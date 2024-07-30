package diceGame.data;

import diceGame.business.Player;

import java.util.List;

public interface DAO {
    List<Player> getPlayers();
}
