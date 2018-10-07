package swingy.view.rpg;

import swingy.Main;
import swingy.controller.GameController;
import swingy.model.Game;
import swingy.utils.Coordinates;

import java.util.Scanner;

public class RPGConsoleView implements RPGView {
    private GameController controller;

    @Override
    public void start() {
        controller = new GameController(this);
        controller.onStart();
    }

    @Override
    public void printMap(boolean[][] map, Coordinates heroCoord) {
        System.out.printf("MAP SIZE: %dx%d", map.length, map.length);
        System.out.print("\n");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (heroCoord.getX() == j && heroCoord.getY() == i) {
                    System.out.print("o ");
                }
                else if (map[i][j]) {
                    System.out.print("* ");
                }
                else {
                    System.out.print(". ");
                }
            }
            System.out.print("\n");
        }
    }

    @Override
    public void update(Game game) {
        System.out.println("\n**********************************************");
        System.out.println("-------------------Hero Stats-----------------");
        System.out.println("**********************************************");
        System.out.println(game.getHero().toString() + "Position: x-coord => " + game.getHeroCoords().getX() + ", y-coord => " + game.getHeroCoords().getY());
        System.out.println("**********************************************");
        getInput();
    }

    private void getInput() {
        Scanner scanner = Main.getScanner();

        System.out.println("* NORTH");
        System.out.println("* SOUTH");
        System.out.println("* EAST");
        System.out.println("* WEST");
        System.out.println("Select one of the directions to move: ");
        System.out.println("Type 'MAP' to print out the map or SWITCH to change to the GUI view");
        while (scanner.hasNext()) {
            String direction = scanner.nextLine();

            if ("map".equalsIgnoreCase(direction)) {
                controller.onPrintMap();
                break;
            }
            else if ("north".equalsIgnoreCase(direction) || "south".equalsIgnoreCase(direction) || "east".equalsIgnoreCase(direction) || "west".equalsIgnoreCase(direction)) {
                controller.onMove(direction);
                break;
            }
            else if ("switch".equalsIgnoreCase(direction)) {
                controller.switchButtonClicked();
                break;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    @Override
    public void gameOver() {
        System.out.println("Thanks for playing!");
        Main.hideFrame();
        Main.createFrame().dispose();
        Main.close();
        System.exit(1);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void getEnemyCollisionInput() {
        Scanner scanner = Main.getScanner();

        System.out.println("\nYou moved to a position occupied with a villain. What would you like to do next?");
        System.out.println("FIGHT -> engage in a battle with the villain");
        System.out.println("RUN -> get a 50% chance of returning to the previous position");
        System.out.println("Choose f for FIGHT OR r for RUN:");
        while (scanner.hasNext()) {
            String choice = scanner.next();

            if ("f".equalsIgnoreCase(choice)) {
                System.out.println("You have chosen to fight the villain");
                controller.onFight();
                break;
            }
            else if ("r".equalsIgnoreCase(choice)) {
                controller.onRun();
                break;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    @Override
    public boolean replaceArtefact(String replaceMessage) {
        Scanner scanner = Main.getScanner();

        System.out.println("Would you like to replace " + replaceMessage + "?");
        System.out.println("Type LEAVE to remain with your current artefact OR type TAKE to get the new artefact");
        while (scanner.hasNext()) {
            String replace = scanner.next();

            if ("leave".equalsIgnoreCase(replace)) {
                System.out.println("You have chosen to leave the new artefact");
                return false;
            }
            else if ("take".equalsIgnoreCase(replace)) {
                return true;
            }
            else {
                System.out.println("Invalid command");
            }
        }
        return false;
    }

    @Override
    public void changeView() {
        new RPG_GUIView().start();
    }
}
