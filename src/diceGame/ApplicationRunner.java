package diceGame;

import diceGame.business.Coordinator;
import diceGame.presentation.View;

public class ApplicationRunner {
    public static void main(String[] args) {
        Coordinator coordinator = new Coordinator();
        View view = new View(coordinator);
        view.startUI();
    }
}
