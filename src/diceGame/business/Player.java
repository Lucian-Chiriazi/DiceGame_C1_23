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

    public String getPlayerScores(int index) {
        return playerScores[index];
    }

    public int getTotalScore(){
        int temp = 0;
        for (String value : playerScores) {
            if (value != null) {
                temp += Integer.parseInt(value);
            }else {
                temp += 0;
            }
        }
        return temp;
    }

    public void resetVariables() {
        this.diceLeft = 8;
    }
}
