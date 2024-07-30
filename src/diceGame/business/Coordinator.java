package diceGame.business;

import diceGame.data.DAO;
import diceGame.data.DAOImplementation;

public class Coordinator {
    private DAO dao;

    public Coordinator() {
        dao = new DAOImplementation();
    }
}
