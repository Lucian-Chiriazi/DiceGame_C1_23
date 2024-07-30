package diceGame.business;

public class Player {
    private String playerName;
    private String[] playerScores;


    public Player(String playerName) {
        this.playerName = playerName;
        this.playerScores = new String[3];
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerScore(int score, int index) {
        this.playerScores[index] = Integer.toString(score);
    }
}
