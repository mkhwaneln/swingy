package swingy.view.select;

import swingy.Main;
import swingy.controller.SelectHeroController;
import swingy.view.create.CreateHeroConsole;
import swingy.view.rpg.RPGConsoleView;

import java.util.Scanner;

public class SelectHeroConsole implements SelectHeroView {
    private SelectHeroController controller;
    private int index = -1;

    @Override
    public void select() {
        controller = new SelectHeroController(this);
        getUserInput();
    }

    private boolean verifyHeroNumber(String str, int max) {
        try {
            int num = Integer.parseInt(str);
            if (num <= 0 || num > max) {
                return false;
            }
        } catch (NumberFormatException ne) {
            return false;
        }
        return true;
    }

    private void getUserInput() {
        Scanner scanner = Main.getScanner();

        System.out.println("--------------------------");
        System.out.println("List of available heroes:");
        System.out.println("--------------------------");
        System.out.println("No. Name => Class |\t Level");
        printHeroes(controller.getHeroList());
        System.out.println("\nVIEW HERO INFORMATION - enter the number of the hero whose information you would lke to display");
        System.out.println("CREATE - create a new hero");
        System.out.println("EXIT - exit the Swingy game");
        System.out.println("Enter one of the commands:");

        while (scanner.hasNext()) {
            String input = scanner.next();

            if (verifyHeroNumber(input, controller.getHeroList().length)) {
                index = Integer.parseInt(input);
                controller.selectedHero(index);
                chooseHero(index);
                break;
            }
            else if ("create".equalsIgnoreCase(input)) {
                controller.createButtonClicked();
                break;
            }
            else if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting Swingy");
                System.exit(1);
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void chooseHero(int id) {
        Scanner scanner = Main.getScanner();

        System.out.println("Type CHOOSE if you want to select this hero or BACK if you want to view the other available heroes");
        while (scanner.hasNext()) {
            String choice = scanner.next();

            if ("choose".equalsIgnoreCase(choice) && id != -1) {
                controller.selectButtonClicked(id);
                break;
            }
            else if ("back".equalsIgnoreCase(choice)) {
                getUserInput();
                break;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void printHeroes(String[] heroes) {
        if (heroes.length == 0) {
            System.out.println("No heroes saved yet");
        }
        for (String hero : heroes) {
            System.out.println(hero);
        }
    }

    @Override
    public void updateHeroInfo(String heroInfo) {
        System.out.println(heroInfo);
    }

    @Override
    public void displayErrorMessages(String message) {
        System.out.println("Error: " + message);

    }

    @Override
    public void startGame() {
        new RPGConsoleView().start();
    }

    @Override
    public void createHero() {
        new CreateHeroConsole().create();
    }
}
