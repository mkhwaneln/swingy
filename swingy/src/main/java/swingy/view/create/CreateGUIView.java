package swingy.view.create;

import swingy.Main;
import swingy.controller.CreateController;
import swingy.view.select.SelectHeroGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGUIView extends JPanel implements CreateView {
    private JFrame GUIFrame = Main.createFrame();
    private JButton btnCreateHero = new JButton("Create New Hero");
    private JButton btnSelectHero = new JButton("Select An Existing Hero");
    private JButton btnSwitchView = new JButton("Switch To Console View");
    private JButton btnQuit = new JButton("Quit Swingy");

    private CreateController controller;

    @Override
    public void create() {
        controller = new CreateController(this);
    	GUIFrame.setContentPane(this);
       	GUIFrame.revalidate();
		createGUI();
    }

    private void createGUI() {
        JLabel lblHeading = new JLabel("WELCOME TO SWINGY", JLabel.CENTER);
        lblHeading.setFont(new Font("Serif", Font.BOLD, 30));
        lblHeading.setForeground(Color.red);

        btnCreateHero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.createHeroButtonClicked();
            }
        });
        btnSelectHero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.selectHeroButtonClicked();
            }
        });
        btnSwitchView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.switchButtonClicked();
            }
        });
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        GUIFrame.getContentPane().removeAll();
        createLayout(btnCreateHero, btnSelectHero, btnQuit, lblHeading, btnSwitchView);
    }

    private void createLayout(JComponent... arg) {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        GUIFrame.setTitle("Swingy RPG Home");
        GUIFrame.setLayout(grid);

        gbc.ipady = 30;
        gbc.ipadx = 40;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        grid.setConstraints(arg[3], gbc);
        GUIFrame.add(arg[3]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 30;
        gbc.ipadx = 40;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.weighty = 1.0;
        grid.setConstraints(arg[0], gbc);
        GUIFrame.add(arg[0]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 30;
        gbc.ipadx = 40;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.weighty = 1.0;
        grid.setConstraints(arg[1], gbc);
        GUIFrame.add(arg[1]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 30;
        gbc.ipadx = 40;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.weighty = 1.0;
        grid.setConstraints(arg[4], gbc);
        GUIFrame.add(arg[4]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 30;
        gbc.ipadx = 40;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.weighty = 1.0;
        grid.setConstraints(arg[2], gbc);
        GUIFrame.add(arg[2]);

        GUIFrame.setVisible(true);
    }

    @Override
    public void createHero() {
        GUIFrame.setVisible(false);
        new CreateHeroGUI().create();
    }

    @Override
    public void selectHero() {
        GUIFrame.setVisible(false);
        new SelectHeroGUI().select();
    }

    @Override
    public void switchView() {
        Main.hideFrame();
        new CreateConsoleView().create();
    }
}
