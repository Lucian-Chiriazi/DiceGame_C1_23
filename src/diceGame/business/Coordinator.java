package diceGame.business;

import diceGame.data.DAO;
import diceGame.data.DAOImplementation;

import java.util.List;
import java.util.Scanner;

public class Coordinator {
    private DAO dao;
    private Scanner scanner;
    private Validation validator;
    private List<Player> players;
    private int currentPlayer;

    public Coordinator() {
        this.dao = new DAOImplementation();
        this.scanner = new Scanner(System.in);
        this.players = dao.getPlayers();
        this.validator = new Validation();
        this.currentPlayer = 0;
    }

    public void startGame () {
        System.out.println();
        System.out.println(getPlayerName(currentPlayer));
        System.out.println(printMessage1(players.get(currentPlayer)));
        System.out.print(printMessage2());
        String input = scanner.nextLine().trim();

        while (!validator.validation2(input)) {
            System.out.println(printInvalid1());
            System.out.println(printMessage2());
            input = scanner.nextLine().trim();
        }

    }

    public boolean runValidation1(String input) {
        return validator.validation1(input);
    }

    public String getPlayerName(int id) {
        return players.get(id).getPlayerName();
    }

    public StringBuilder printMessage1(Player player) {
        StringBuilder temp = new StringBuilder();
        temp.append("First throw of this turn, starting with ");
        temp.append(player.getDiceLeft());
        temp.append(" dice.");
        return temp;
    }

    public StringBuilder printMessage2() {
        StringBuilder temp = new StringBuilder();
        temp.append("Enter 't' to throw > ");
        return temp;
    }

    public StringBuilder printInvalid1() {
        StringBuilder temp = new StringBuilder();
        temp.append("Invalid input!");
        return temp;
    }

}
