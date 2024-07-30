package diceGame.presentation;

import diceGame.business.Coordinator;
import java.util.Scanner;

public class View {
    private Coordinator coordinator;
    private Scanner scanner;

    public View (Coordinator coordinator) {
        this.coordinator = coordinator;
        this.scanner = new Scanner(System.in);
    }

    public void startUI() {

    }
}
