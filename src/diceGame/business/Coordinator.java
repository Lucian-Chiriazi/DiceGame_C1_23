package diceGame.business;

import diceGame.data.DAO;
import diceGame.data.DAOImplementation;

import java.util.*;

public class Coordinator {
    private DAO dao;
    private Scanner scanner;
    private Validation validator;
    private List<Player> players;
    private int currentPlayer;
    private int round;
    private ArrayList<Integer> currentThrow;

    public Coordinator() {
        this.dao = new DAOImplementation();
        this.scanner = new Scanner(System.in);
        this.players = dao.getPlayers();
        this.validator = new Validation();
        this.currentPlayer = 0;
        this.round = 0;
        this.currentThrow = new ArrayList<>();
    }

    public void startGame () {
        while (round <= 3) {
            startRound();
            round++;
        }
    }

    public void startRound() {
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
        this.currentThrow = generateThrow(players.get(currentPlayer));
        System.out.print("Throw:  ");
        System.out.print(printThrow(currentThrow));
        System.out.println();
        System.out.print("Sorted: ");
        sortThrow(currentThrow);
        System.out.print(printThrow(currentThrow));


    }

    public ArrayList<Integer> generateThrow(Player player) {
        ArrayList<Integer> temp = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < player.getDiceLeft(); i++) {
            temp.add(rand.nextInt(6) + 1);
        }
        return temp;
    }

    public void sortThrow(ArrayList<Integer> temp) {
        Collections.sort(temp, Collections.reverseOrder());
    }

    public StringBuilder printThrow(ArrayList<Integer> temp) {
        StringBuilder diceList = new StringBuilder();
        for (int i = 0; i < temp.size(); i++) {
            diceList.append("[ ");
            diceList.append(temp.get(i));
            diceList.append(" ] ");
        }
        return diceList;
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
