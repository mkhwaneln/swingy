package swingy.view.create;

import swingy.Main;
import swingy.controller.CreateHeroController;
import swingy.view.rpg.RPG_GUIView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CreateHeroGUI extends JPanel implements ItemListener, CreateHeroView {
    private JFrame CreateFrame = Main.createFrame();
    private JTextField txtName;
    private JComboBox<String> cbxClass;
    private JEditorPane displayClasses;
    private JButton btnCreate = new JButton("Create Hero");
    private JButton btnBack = new JButton("Go Back to Home");

    private CreateHeroController controller;

    @Override
    public void create() {
        controller = new CreateHeroController(this);
        createGUI();
    }

    private void createGUI() {
        JLabel lblHeading = new JLabel("Create a new hero", JLabel.CENTER);
        JLabel lblName = new JLabel("Enter a hero name: ");
        JLabel lblClass = new JLabel("Select a hero class: ");
        txtName = new JTextField(20);
        String[] classes = new String[]{"Assassin", "Gladiator", "Healer", "Knight", "Ninja", "Sorcerer", "Wizard"};

        cbxClass = new JComboBox<>(classes);
        displayClasses = new JEditorPane();

        cbxClass.addItemListener(this);

        lblHeading.setFont(new Font("Times New Roman", Font.BOLD, 25));
        displayClasses.setContentType("text/plain");
        displayClasses.setEditable(false);
        displayClasses.setText("------------< Hero Class Information >------------\n" +
                "\n # Hero class\n" +
                "\n # Attack points\n" +
                "\n # Defence points\n" +
                "\n # Hit points\n");

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.createButtonClicked(txtName.getText(), cbxClass.getSelectedItem().toString());
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateGUIView().create();
            }
        });

        CreateFrame.getContentPane().removeAll();
        createLayout(lblHeading, lblName, txtName, lblClass, cbxClass, displayClasses, btnCreate, btnBack);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            selectHeroClass(e.getItem().toString());
        }
    }

    private void selectHeroClass(String classChosen) {
        switch (classChosen) {
            case "Assassin":
                displayClasses.setText("------------< Hero Class Information >------------\n" +
                        "\n # Hero class\t                      " + classChosen + "\n" +
                        "\n # Attack points\t                   50\n" +
                        "\n # Defence points\t                 15\n" +
                        "\n # Hit points\t\t90\n");
                break;
            case "Gladiator":
                displayClasses.setText("------------< Hero Class Information >------------\n" +
                        "\n # Hero class\t                      " + classChosen + "\n" +
                        "\n # Attack points\t                   60\n" +
                        "\n # Defence points\t                 40\n" +
                        "\n # Hit points\t\t90\n");
                break;
            case "Healer":
                displayClasses.setText("------------< Hero Class Information >------------\n" +
                        "\n # Hero class\t                      " + classChosen + "\n" +
                        "\n # Attack points\t                   10\n" +
                        "\n # Defence points\t                 20\n" +
                        "\n # Hit points\t\t80\n");
                break;
            case "Knight":
                displayClasses.setText("------------< Hero Class Information >------------\n" +
                        "\n # Hero class\t                      " + classChosen + "\n" +
                        "\n # Attack points\t                   40\n" +
                        "\n # Defence points\t                 50\n" +
                        "\n # Hit points\t\t90\n");
                break;
            case "Ninja":
                displayClasses.setText("------------< Hero Class Information >------------\n" +
                        "\n # Hero class\t                      " + classChosen + "\n" +
                        "\n # Attack points\t                   60\n" +
                        "\n # Defence points\t                 25\n" +
                        "\n # Hit points\t\t100\n");
                break;
            case "Sorcerer":
                displayClasses.setText("------------< Hero Class Information >------------\n" +
                        "\n # Hero class\t                      " + classChosen + "\n" +
                        "\n # Attack points\t                   80\n" +
                        "\n # Defence points\t                 60\n" +
                        "\n # Hit points\t\t110\n");
                break;
            case "Wizard":
                displayClasses.setText("------------< Hero Class Information >------------\n" +
                        "\n # Hero class\t                      " + classChosen + "\n" +
                        "\n # Attack points\t                   40\n" +
                        "\n # Defence points\t                 30\n" +
                        "\n # Hit points\t\t80\n");
                break;
            default:
                displayClasses.setText("------------< Hero Class Information >------------\n" +
                        "\n # Hero class\n" +
                        "\n # Attack points\n" +
                        "\n # Defence points\n" +
                        "\n # Hit points\n");
                break;
        }
    }

    private void createLayout(JComponent... arg) {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        CreateFrame.setTitle("Create New Hero");
        CreateFrame.setLayout(grid);

        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.weighty = 1.0;
        grid.setConstraints(arg[0], gbc);
        CreateFrame.add(arg[0]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        grid.setConstraints(arg[1], gbc);
        CreateFrame.add(arg[1]);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        grid.setConstraints(arg[2], gbc);
        CreateFrame.add(arg[2]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        grid.setConstraints(arg[3], gbc);
        CreateFrame.add(arg[3]);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        grid.setConstraints(arg[4], gbc);
        CreateFrame.add(arg[4]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.weighty = 0.5;
        grid.setConstraints(arg[5], gbc);
        CreateFrame.add(arg[5]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 4;
        grid.setConstraints(arg[6], gbc);
        CreateFrame.add(arg[6]);

        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 5;
        grid.setConstraints(arg[7], gbc);
        CreateFrame.add(arg[7]);

        CreateFrame.setVisible(true);
    }

    @Override
    public void getInput() {
    }

    @Override
    public void displayErrorMessages(String message) {
        JOptionPane.showMessageDialog(CreateFrame, message);
    }

    @Override
    public void startGame() {
        CreateFrame.setVisible(false);
        new RPG_GUIView().start();
    }
}
