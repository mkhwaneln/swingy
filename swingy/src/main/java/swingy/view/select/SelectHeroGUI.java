package swingy.view.select;

import swingy.Main;
import swingy.controller.SelectHeroController;
import swingy.view.create.CreateGUIView;
import swingy.view.create.CreateHeroGUI;
import swingy.view.rpg.RPG_GUIView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectHeroGUI extends JPanel implements SelectHeroView {
    private JFrame SelectFrame = Main.createFrame();
    private JEditorPane heroInformation;
    private JScrollPane displayList;
    private JList heroList;
    private JButton btnSelect = new JButton("Select Hero");
    private JButton btnCreate = new JButton("Create a New Hero");
    private JButton btnBack = new JButton("Go Back to Home");

    private SelectHeroController controller;
    private int lastIndex;

    @Override
    public void select() {
        controller = new SelectHeroController(this);
        createGUI();
    }

    private void createGUI() {
        JLabel lblHeading = new JLabel("List of Heroes", JLabel.CENTER);
        heroInformation = new JEditorPane();

        lblHeading.setFont(new Font("Arial", Font.ITALIC, 20));

        String[] heroes = controller.getHeroList();
        heroList = new JList(heroes);
        heroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        heroList.setLayoutOrientation(JList.VERTICAL);

        displayList = new JScrollPane(heroList);
        displayList.setPreferredSize(new Dimension(200, 200));
        displayList.setMinimumSize(new Dimension(150, 150));

        heroInformation.setEditable(false);
        heroInformation.setText("Choose a hero to swingy.view information");
        heroInformation.setPreferredSize(new Dimension(200, 200));
        heroInformation.setMinimumSize(new Dimension(150, 150));
        if (heroes.length == 0)
            heroInformation.setText("No heroes saved yet");
        btnSelect.setEnabled(false);

        heroList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (heroList.getSelectedIndex() == -1) {
                        btnSelect.setEnabled(false);
                    }
                    else {
                        controller.selectedHero(heroList.getSelectedIndex() + 1);
                        btnSelect.setEnabled(true);
                        lastIndex = heroList.getSelectedIndex() + 1;
                    }
                }
            }
        });
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.selectButtonClicked(lastIndex);
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.createButtonClicked();
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateGUIView().create();
            }
        });

        SelectFrame.getContentPane().removeAll();
        createLayout(lblHeading, displayList, heroInformation, btnSelect, btnCreate, btnBack);
    }

    private void createLayout(JComponent... arg) {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        SelectFrame.setTitle("Select An Existing Hero");
        SelectFrame.setLayout(grid);

        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.weighty = 1.0;
        grid.setConstraints(arg[0], gbc);
        SelectFrame.add(arg[0]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        grid.setConstraints(arg[1], gbc);
        SelectFrame.add(arg[1]);

        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        grid.setConstraints(arg[2], gbc);
        SelectFrame.add(arg[2]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 6;
        grid.setConstraints(arg[3], gbc);
        SelectFrame.add(arg[3]);

        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 6;
        grid.setConstraints(arg[4], gbc);
        SelectFrame.add(arg[4]);

        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 6;
        grid.setConstraints(arg[5], gbc);
        SelectFrame.add(arg[5]);

        SelectFrame.setVisible(true);
    }

    @Override
    public void updateHeroInfo(String heroInfo) {
        heroInformation.setText(heroInfo);
    }

    @Override
    public void displayErrorMessages(String message) {
        JOptionPane.showMessageDialog(SelectFrame, message);
    }

    @Override
    public void startGame() {
        SelectFrame.setVisible(false);
        new RPG_GUIView().start();
    }

    @Override
    public void createHero() {
        SelectFrame.setVisible(false);
        new CreateHeroGUI().create();
    }
}
