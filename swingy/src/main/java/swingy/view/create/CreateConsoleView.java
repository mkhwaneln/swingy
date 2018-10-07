package swingy.view.create;

import swingy.Main;
import swingy.controller.CreateController;
import swingy.view.select.SelectHeroConsole;

import java.util.Scanner;

public class CreateConsoleView implements CreateView {
    private CreateController controller;

    @Override
    public void create() {
        int number;

        controller = new CreateController(this);
        System.out.println("Welcome to Swingy RPG! Select one of the commands below:\n");
        System.out.println("1. Create a new hero");
        System.out.println("2. Select an existing hero");
        System.out.println("3. Switch to GUI view");
        System.out.println("4. Exit Swingy\n");

        Scanner scanner = Main.getScanner();

        while (scanner.hasNext()) {
            String num = scanner.nextLine();
            try {
                number = Integer.parseInt(num);
                switch (number) {
                    case 1:
                        controller.createHeroButtonClicked();
                        break;
                    case 2:
                        controller.selectHeroButtonClicked();
                        break;
                    case 3:
                        controller.switchButtonClicked();
                        break;
                    case 4:
                        System.out.println("Exiting Swingy");
                        System.exit(1);
                    default:
                        System.out.println("Incorrect option! Choose one of the options");
                        continue;
                }
            } catch (NumberFormatException ne) {
                System.out.println(ne.getMessage() + " is not an integer! Try again");
                continue;
            }
            break;
        }
    }

    @Override
    public void createHero() {
        new CreateHeroConsole().create();
    }

    @Override
    public void selectHero() {
        new SelectHeroConsole().select();
    }

    @Override
    public void switchView() {
        new CreateGUIView().create();
    }
}
