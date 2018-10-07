package swingy.view.rpg;

import swingy.Main;
import swingy.controller.GameController;
import swingy.model.Game;
import swingy.utils.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RPG_GUIView extends JPanel implements RPGView {
    private JFrame GameFrame = Main.createFrame();
    private JEditorPane displayInfo = new JEditorPane();
    private JEditorPane displayMap = new JEditorPane();
    private JComboBox<String> cbxDirections;
    private JButton btnMove = new JButton("Move Player");
    private JButton btnMap = new JButton("View Map");
    private JButton btnSwitch = new JButton("Switch to console");

    private GameController controller;

    @Override
    public void start() {
        controller = new GameController(this);
        createGUI();
        controller.onStart();
    }

    private void createGUI() {
        JLabel lblHeading = new JLabel("Choose a direction to move or swingy.view the map of the game", JLabel.CENTER);
        String[] directions = new String[]{"North", "East", "South", "West"};

        cbxDirections = new JComboBox<>(directions);

        GameFrame.setSize(600, 600);

        displayInfo.setEditable(false);
        displayInfo.setText("Hero information will be displayed here");
        displayInfo.setPreferredSize(new Dimension(450, 450));
        displayInfo.setMinimumSize(new Dimension(400, 400));

        displayMap.setEditable(false);
        displayMap.setText("Map will be displayed here");
        displayMap.setPreferredSize(new Dimension(600, 600));
        displayMap.setMinimumSize(new Dimension(550, 550));

        btnMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onMove(cbxDirections.getSelectedItem().toString());
            }
        });
        btnMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onPrintMap();
            }
        });
        btnSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.switchButtonClicked();
            }
        });

        GameFrame.getContentPane().removeAll();
        createLayout(lblHeading, displayInfo, cbxDirections, btnMove, btnMap, btnSwitch);
    }

    private void createLayout(JComponent... arg) {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        GameFrame.setTitle("Swingy");
        GameFrame.setLayout(grid);

        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.gridwidth = 2;
        grid.setConstraints(arg[0], gbc);
        GameFrame.add(arg[0]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        grid.setConstraints(arg[1], gbc);
        GameFrame.add(arg[1]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        grid.setConstraints(arg[2], gbc);
        GameFrame.add(arg[2]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        grid.setConstraints(arg[3], gbc);
        GameFrame.add(arg[3]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        grid.setConstraints(arg[4], gbc);
        GameFrame.add(arg[4]);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        grid.setConstraints(arg[5], gbc);
        GameFrame.add(arg[5]);

        GameFrame.setVisible(true);
    }

    @Override
    public void update(Game game) {
        displayInfo.setText("********************************************************\n" +
                "-----------------Hero Stats-----------------\n" +
                "********************************************************\n" +
                game.getHero().toString() + "Position: x-coord => " +
                game.getHeroCoords().getX() + ", y-coord => " +
                game.getHeroCoords().getY());
    }

    @Override
    public void printMap(boolean[][] map, Coordinates heroCoord) {
        StringBuilder sbMap = new StringBuilder();
        sbMap.append("MAP SIZE: " + Integer.toString(map.length) + "x" +
                Integer.toString(map.length) + "\n\n");
        for (int i = 0; i < map.length; i++) {
            sbMap.append("\t");
            for (int j = 0; j < map[i].length; j++) {
                if (heroCoord.getX() == j && heroCoord.getY() == i) {
                    sbMap.append("o ");
                }
                else if (map[i][j]) {
                    sbMap.append("* ");
                }
                else {
                    sbMap.append(". ");
                }
            }
            sbMap.append("\n");
        }
        displayMap.setText(sbMap.toString());
        JOptionPane.showMessageDialog(GameFrame, displayMap);
    }

    @Override
    public void gameOver() {
        Main.hideFrame();
        GameFrame.dispose();
        Main.close();
    }

    @Override
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(GameFrame, message);
    }

    @Override
    public void getEnemyCollisionInput() {
        Object options[] = {"Fight", "Run"};
        int x = JOptionPane.showOptionDialog(GameFrame,"You moved to a position occupied with a villain. What would you like to do next?", "Choose a strategy", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

        if (x == 0) {
            controller.onFight();
        }
        else {
            controller.onRun();
        }
    }

    @Override
    public boolean replaceArtefact(String replaceMessage) {
        Object options[] = {"Replace current artefact", "Leave the artefact"};
        int x = JOptionPane.showOptionDialog(GameFrame, "Would you like to replace " + replaceMessage + "?", "Artefact found", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

        if (x == 0) {
            JOptionPane.showMessageDialog(GameFrame, "You have a new artefact");
            return true;
        }
        else {
            JOptionPane.showMessageDialog(GameFrame, "You have chosen to leave the artefact");
            return false;
        }
    }

    @Override
    public void changeView() {
        Main.hideFrame();
        new RPGConsoleView().start();
    }
}
