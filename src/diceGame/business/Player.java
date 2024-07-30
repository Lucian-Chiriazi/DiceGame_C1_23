package diceGame.business;

public class Player {
    private String playerName;
    private String[] playerScores;
    private int diceLeft;


    public Player(String playerName) {
        this.playerName = playerName;
        this.playerScores = new String[3];
        this.diceLeft = 8;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerScore(int score, int index) {
        this.playerScores[index] = Integer.toString(score);
    }

    public int getDiceLeft() {
        return diceLeft;
    }

    public void setDiceLeft(int choice) {
        this.diceLeft -= choice;
    }
}
