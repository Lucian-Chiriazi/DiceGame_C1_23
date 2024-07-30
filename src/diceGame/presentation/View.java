package diceGame.presentation;

import diceGame.business.Coordinator;
import java.util.Scanner;

public class View {
    private Coordinator coordinator;
    private Scanner scanner;
    private boolean keepRunning;

    public View (Coordinator coordinator) {
        this.coordinator = coordinator;
        this.scanner = new Scanner(System.in);
        this.keepRunning = true;
    }

    public void startUI() {
        while (keepRunning) {
            System.out.print(printMessage1());
            String input = scanner.nextLine().trim();

            while (!coordinator.runValidation1(input)) {
                System.out.println(printInvalid1());
                System.out.print(printMessage1());
                input = scanner.nextLine().trim();
            }

            if (input.equals("1")){
                coordinator.startGame();
            }else {
                keepRunning = false;
                System.out.println("Exit Game!");
                System.out.println("Bye Bye");
            }
        }
    }


    private StringBuilder printMessage1() {
        StringBuilder temp = new StringBuilder();
        temp.append("This is a strategic dice game!\n");
        temp.append("Play game (1) or Exit game (0) >");
        return temp;
    }

    private StringBuilder printInvalid1() {
        StringBuilder temp = new StringBuilder();
        temp.append("Invalid input!\n");
        temp.append("Choose (1) or (0)!\n");
        return temp;
    }
}
