package diceGame.data;

import diceGame.business.Player;
import java.util.ArrayList;

public interface DAO {
    ArrayList<Player> getPlayers();
}
