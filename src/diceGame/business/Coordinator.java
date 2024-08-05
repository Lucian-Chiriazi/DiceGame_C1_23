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
    private int currentScore;
    private ArrayList<Integer> currentThrow;
    private ArrayList<Integer> currentDiceKept;

    public Coordinator() {
        this.dao = new DAOImplementation();
        this.scanner = new Scanner(System.in);
        this.players = dao.getPlayers();
        this.validator = new Validation();
        this.currentScore = 0;
        this.currentPlayer = 0;
        this.round = 0;
        this.currentThrow = new ArrayList<>();
        this.currentDiceKept = new ArrayList<>();
    }

    public void startGame () {
        while (round < 3) {
            startRound();
            System.out.println(printScoreboard());
            round++;
        }
    }

    private void startRound() {
        currentPlayer = 0;
        for (int i = 0; i < 3; i++) {
            playTurn();
            resetVariables();
            currentPlayer++;
        }
    }

    private void playTurn() {
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

        generateAndPrintThrow();

        System.out.println();
        System.out.print(printMessage3());
        String input2 = scanner.nextLine().trim();

        while (!validator.validation3(input2, currentThrow, currentDiceKept)) {
            System.out.println(printInvalid1());
            System.out.println(printMessage4());
            System.out.print(printMessage3());
            input2 = scanner.nextLine().trim();
        }

        updateDiceKept(input2);

        processChoice(input2);

    }

    private void continueTurn() {
        if (players.get(currentPlayer).getDiceLeft() > 0) {
            System.out.println(printMessage10());
            System.out.println(printMessage11());
            System.out.print(printMessage12());
            String input = scanner.nextLine().trim();

            while (!validator.validation7(input)) {
                System.out.println(printInvalid1());
                System.out.print(printMessage12());
                input = scanner.nextLine().trim();
            }

            generateAndPrintThrow();

            System.out.println();
            System.out.println(printCurrentDiceKept());
            System.out.println(printMessage13());

            System.out.println(printCurrentValidOptions());
            System.out.print(printMessage3());
            String input2 = scanner.nextLine().trim();

            while (!validator.validation3(input2, currentThrow, currentDiceKept)) {
                System.out.println(printInvalid1());
                System.out.print(printMessage3());
                input2 = scanner.nextLine().trim();
            }

            updateDiceKept(input2);

            processChoice(input2);

        }
    }


    private void processChoice(String input) {
        int occurrences = countOccurrences(input, currentThrow);
        System.out.println(printMessage5(input, occurrences));

        System.out.print(printMessage6());
        String input3 = scanner.nextLine().trim();

        while(!validator.validation5(input3, occurrences)) {
            System.out.println(printInvalid1());
            System.out.print(printMessage6());
            input3 = scanner.nextLine().trim();
        }

        updateCurrentScore(input, input3);
        System.out.println(printMessage7());

        players.get(currentPlayer).setDiceLeft(Integer.parseInt(input3));
        System.out.println(printMessage8(input3, players.get(currentPlayer)));

        System.out.print(printMessage9());
        String input4 = scanner.nextLine().trim();

        while(!validator.validation6(input4)) {
            System.out.println(printInvalid1());
            System.out.print(printMessage9());
            input4 = scanner.nextLine().trim();
        }

        if (input4.equals("c")) {
            continueTurn();
        }else {
            finishTurn();
        }
    }

    private void finishTurn() {
        System.out.println(printMessage14());
        players.get(currentPlayer).setPlayerScore(currentScore, round);
    }

    private void updateDiceKept(String input) {
        currentDiceKept.add(Integer.parseInt(input));
    }

    private void generateAndPrintThrow() {
        this.currentThrow = generateThrow(players.get(currentPlayer));

        System.out.print("Throw:  ");
        System.out.print(printThrow(currentThrow));
        System.out.println();
        System.out.print("Sorted: ");
        sortThrow(currentThrow);
        System.out.print(printThrow(currentThrow));
    }

    private void updateCurrentScore(String choice, String multiplier) {
        this.currentScore += Integer.parseInt(multiplier) * Integer.parseInt(choice);
    }

    private ArrayList<Integer> generateThrow(Player player) {
        ArrayList<Integer> temp = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < player.getDiceLeft(); i++) {
            temp.add(rand.nextInt(6) + 1);
        }
        return temp;
    }

    private int countOccurrences(String input, ArrayList<Integer> currentThrow) {
        int temp = 0;
        for (int i = 0; i < currentThrow.size(); i++) {
            if (Integer.parseInt(input) == currentThrow.get(i)) {
                temp++;
            }
        }
        return temp;
    }

    private void sortThrow(ArrayList<Integer> temp) {
        temp.sort(Collections.reverseOrder());
    }

    private StringBuilder printThrow(ArrayList<Integer> temp) {
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

    private String getPlayerName(int currentPlayer) {
        return players.get(currentPlayer).getPlayerName();
    }

    private void resetVariables() {
        players.get(currentPlayer).resetVariables();
        this.currentScore = 0;
        this.currentThrow = new ArrayList<>();
        this.currentDiceKept = new ArrayList<>();
    }

    private StringBuilder printCurrentDiceKept() {
        StringBuilder temp = new StringBuilder();
        temp.append("You have already set aside: ");
        for (int i = 0; i < currentDiceKept.size(); i++) {
            temp.append("[ ");
            temp.append(currentDiceKept.get(i));
            temp.append(" ] ");
        }

        return temp;
    }

    private StringBuilder printCurrentValidOptions() {
        StringBuilder temp = new StringBuilder();
        Set<Integer> options = new HashSet<>(currentThrow);
        options.removeAll(currentDiceKept);

        temp.append("You can now select one of the following: ");
        for (Integer value : options) {
            temp.append("[ ");
            temp.append(value);
            temp.append(" ] ");
        }

        return temp;
    }

    private StringBuilder printMessage1(Player player) {
        StringBuilder temp = new StringBuilder();
        temp.append("First throw of this turn, starting with ");
        temp.append(player.getDiceLeft());
        temp.append(" dice.");
        return temp;
    }

    private StringBuilder printMessage2() {
        StringBuilder temp = new StringBuilder();
        temp.append("Enter 't' to throw > ");
        return temp;
    }

    private StringBuilder printMessage3() {
        StringBuilder temp = new StringBuilder();
        temp.append("Select die value to set aside > ");
        return temp;
    }

    private StringBuilder printMessage4() {
        StringBuilder temp = new StringBuilder();
        temp.append("You already chose that category or current throw dose not contain that value!");
        return temp;
    }

    private StringBuilder printMessage5(String input, int occurrences) {
        StringBuilder temp = new StringBuilder();
        temp.append("There are ");
        temp.append(occurrences);
        temp.append(" dice that have that value.\n");
        temp.append("You can choose to keep ");
        for (int i = 1; i <= occurrences; i++) {
            if (i == occurrences && occurrences != 1) {
                temp.append("or ");
                temp.append(i);
                temp.append(" ");
            }else {
                temp.append(i);
                temp.append(" ");
            }
        }
        temp.append("dice of value ");
        temp.append(input);
        temp.append(".");
        return temp;
    }

    private StringBuilder printMessage6() {
        StringBuilder temp = new StringBuilder();
        temp.append("How many do you want to set aside > ");
        return temp;
    }

    private StringBuilder printMessage7() {
        StringBuilder temp = new StringBuilder();
        temp.append("Score so far = ");
        temp.append(currentScore);

        return temp;
    }

    private StringBuilder printMessage8(String input, Player player) {
        StringBuilder temp = new StringBuilder();
        temp.append("You have kept ");
        temp.append(8 - player.getDiceLeft());
        temp.append(" dice so far.");
        return temp;
    }

    private StringBuilder printMessage9() {
        StringBuilder temp = new StringBuilder();
        temp.append("Finish turn or continue (enter 'f' to finish turn or 'c' to continue and throw again) > ");

        return temp;
    }

    private StringBuilder printMessage10() {
        StringBuilder temp = new StringBuilder();
        temp.append("Taking ");
        temp.append(players.get(currentPlayer).getDiceLeft());
        temp.append(" dice forward to the next throw.");

        return temp;
    }

    private StringBuilder printMessage11() {
        StringBuilder temp = new StringBuilder();
        temp.append("Next throw of this turn.");

        return temp;
    }

    private StringBuilder printMessage12() {
        StringBuilder temp = new StringBuilder();
        temp.append("Enter 't' to throw > ");

        return temp;
    }

    private StringBuilder printMessage13() {
        StringBuilder temp = new StringBuilder();
        temp.append("You must now select a different die value.");

        return temp;
    }

    private StringBuilder printMessage14() {
        StringBuilder temp = new StringBuilder();
        temp.append("Final score for that turn for ");
        temp.append(players.get(currentPlayer).getPlayerName());
        temp.append(" = ");
        temp.append(currentScore);

        return temp;
    }

    private StringBuilder printScoreboard() {
        StringBuilder temp = new StringBuilder();
        temp.append("--------------------------------------------\n");
        temp.append(String.format("|  %5s  | %8s | %8s | %8s |\n",
                "Round",
                players.get(0).getPlayerName(),
                players.get(1).getPlayerName(),
                players.get(2).getPlayerName()));
        temp.append("--------------------------------------------\n");
        temp.append(String.format("|    %1s    |    %-2s    |    %-2s    |    %-2s    |\n",
                "1",
                (players.get(0).getPlayerScores(0) == null) ? "--" : players.get(0).getPlayerScores(0),
                (players.get(1).getPlayerScores(0) == null) ? "--" : players.get(1).getPlayerScores(0),
                (players.get(2).getPlayerScores(0) == null) ? "--" : players.get(2).getPlayerScores(0)
                ));
        temp.append("--------------------------------------------\n");
        temp.append(String.format("|    %1s    |    %-2s    |    %-2s    |    %-2s    |\n",
                "2",
                (players.get(0).getPlayerScores(1) == null) ? "--" : players.get(0).getPlayerScores(1),
                (players.get(1).getPlayerScores(1) == null) ? "--" : players.get(1).getPlayerScores(1),
                (players.get(2).getPlayerScores(1) == null) ? "--" : players.get(2).getPlayerScores(1)
        ));
        temp.append("--------------------------------------------\n");
        temp.append(String.format("|    %1s    |    %-2s    |    %-2s    |    %-2s    |\n",
                "3",
                (players.get(0).getPlayerScores(2) == null) ? "--" : players.get(0).getPlayerScores(2),
                (players.get(1).getPlayerScores(2) == null) ? "--" : players.get(1).getPlayerScores(2),
                (players.get(2).getPlayerScores(2) == null) ? "--" : players.get(2).getPlayerScores(2)
        ));
        temp.append("--------------------------------------------\n");
        temp.append(String.format("|  %5s  |    %-2s    |    %-2s    |    %-2s    |\n",
                "Total",
                players.get(0).getTotalScore(),
                players.get(1).getTotalScore(),
                players.get(2).getTotalScore()
                ));
        temp.append("--------------------------------------------\n");

        return temp;
    }

    private StringBuilder printInvalid1() {
        StringBuilder temp = new StringBuilder();
        temp.append("Invalid input!");
        return temp;
    }
}
