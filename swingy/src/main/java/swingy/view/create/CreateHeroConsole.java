package swingy.view.create;

import swingy.Main;
import swingy.controller.CreateHeroController;
import swingy.view.rpg.RPGConsoleView;

import java.util.Scanner;

public class CreateHeroConsole implements CreateHeroView {
    private CreateHeroController controller;

    @Override
    public void create() {
        controller = new CreateHeroController(this);
        getInput();
    }

    @Override
    public void displayErrorMessages(String message) {
        System.out.println("Error: " + message);
    }

    @Override
    public void getInput() {
        Scanner scanner = Main.getScanner();

        System.out.println("**************CREATE A HERO***************");
        System.out.println("------------------------------------------");
        System.out.println("Enter a hero name:");
        String name = scanner.next();

        System.out.println("\tClasses\t\tHit points\tAttack\t\tDefence");
        System.out.println("<=====================================================================>");
        System.out.println("\tKnight\t\t90\t\t40\t\t50");
        System.out.println("\tGladiator\t90\t\t60\t\t40");
        System.out.println("\tSorcerer\t110\t\t80\t\t60");
        System.out.println("\tWizard\t\t80\t\t40\t\t30");
        System.out.println("\tAssassin\t90\t\t50\t\t15");
        System.out.println("\tNinja\t\t100\t\t60\t\t25");
        System.out.println("\tHealer\t\t80\t\t10\t\t20");
        System.out.println("Select one of the above classes:");
        String heroClass = scanner.next();

        System.out.println("To create a new hero, type CREATE to confirm");
        while (scanner.hasNext()) {
            String input = scanner.next();

            if ("create".equalsIgnoreCase(input)) {
                controller.createButtonClicked(name, heroClass);
                break;
            }
            else {
                System.out.println("Invalid input! Hero not confirmed");
            }
        }
    }

    @Override
    public void startGame() {
        new RPGConsoleView().start();
    }
}
