package diceGame.business;

import diceGame.data.DAO;
import diceGame.data.DAOImplementation;

import java.util.List;

public class Coordinator {
    private DAO dao;
    private Validation validator;
    private List<Player> players;

    public Coordinator() {
        this.dao = new DAOImplementation();
        this.players = dao.getPlayers();
        this.validator = new Validation();
    }

    public boolean runValidation1(String input) {
        boolean temp = validator.validation1(input);
        return temp;
    }


}
