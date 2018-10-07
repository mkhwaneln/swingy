package swingy;

import swingy.utils.HeroDB;
import swingy.view.create.CreateGUIView;
import swingy.view.create.CreateConsoleView;

import javax.swing.*;
import java.util.*;

public class Main {
    private static JFrame GUIFrame;
    private static Scanner scanner;

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void close() {
        HeroDB.close();
        if (scanner != null)
            scanner.close();
    }

    public static JFrame createFrame() {
        if (GUIFrame == null) {
            GUIFrame = new JFrame();
            GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GUIFrame.setSize(500, 500);
            GUIFrame.setLocationRelativeTo(null);
        }
        return (GUIFrame);
    }

    public static void hideFrame() {
        if (GUIFrame != null)
            GUIFrame.setVisible(false);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Only one argument is required");
            System.exit(1);
        }

        HeroDB.connect();

        if (args[0].equalsIgnoreCase("console")) {
            new CreateConsoleView().create();
        }
        else if (args[0].equalsIgnoreCase("gui")) {
            new CreateGUIView().create();
        }
        else {
            System.out.println("Invalid option: " + args[0] + "\nEnter either gui or console");
            System.exit(1);
        }
    }
}
