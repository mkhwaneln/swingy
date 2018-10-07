package swingy.controller;

import swingy.model.Enemy;
import swingy.model.Game;
import swingy.model.Hero;
import swingy.model.artefacts.Armor;
import swingy.model.artefacts.Artefact;
import swingy.model.artefacts.Helm;
import swingy.model.artefacts.Weapon;
import swingy.utils.*;
import swingy.view.rpg.RPGView;

import java.util.Random;

public class GameController {
    private RPGView view;
    private Game game;
    private Coordinates previousPosition;

    public GameController(RPGView view) {
        this.view = view;
        game = Game.loadGame();
        previousPosition = new Coordinates(0, 0);
    }

    public void onStart() {
        view.update(game);
    }

    public void onPrintMap() {
        view.printMap(game.getMap(), game.getHeroCoords());
        view.update(game);
    }

    public void onMove(String direction) {
        int x = game.getHeroCoords().getX();
        int y = game.getHeroCoords().getY();

        previousPosition.setX(x);
        previousPosition.setY(y);

        switch (direction.toUpperCase()) {
            case "NORTH":
                y--;
                break;
            case "SOUTH":
                y++;
                break;
            case "EAST":
                x++;
                break;
            case "WEST":
                x--;
                break;
        }

        if (x < 0 || y < 0 || x >= game.getMapSize() || y >= game.getMapSize()) {
            view.displayMessage("You win! You won an additional " + game.getMapSize() * 100 + "XP!");
            addExperience(game.getMapSize() * 100);
            updateHero();
            view.gameOver();
            return;
        }

        game.getHeroCoords().setX(x);
        game.getHeroCoords().setY(y);

        if (game.getMap()[y][x]) {
            enemyCollision();
        }

        if (game.getHero().getHitPoints() > 0) {
            view.update(game);
        }
    }

    private void addExperience(int addXP) {
        int level = game.getHero().getLevel();

        game.getHero().addExperience(addXP);
        if (level != game.getHero().getLevel())
            view.displayMessage("Level up!\nHP, attack and defence were increased!");
    }

    private void updateHero() {
        Hero hero = game.getHero();
        HeroDB.updateHeroInfo(hero);
    }

    private void enemyCollision() {
        view.getEnemyCollisionInput();
    }

    public void onRun() {
        if (new Random().nextBoolean()) {
            view.displayMessage("Success! You have been moved back to the previous position");
            game.getHeroCoords().setX(previousPosition.getX());
            game.getHeroCoords().setY(previousPosition.getY());
        }
        else {
            view.displayMessage("You must fight the villain");
            onFight();
        }
    }

    public void onFight() {
        Enemy enemy = game.createEnemy();
        int exp = game.resultOfFight(enemy);
        System.out.println("Simulating fight.......");

        if (exp >= 0) {
            view.displayMessage("W * I * N * N * E * R!! You got " + exp + "XP");
            addExperience(exp);
            game.getMap()[game.getHeroCoords().getY()][game.getHeroCoords().getX()] = false;
            setArtefact(enemy.getArtefact());
        }
        else {
            view.displayMessage("YOU LOSE!!\n*-----GAME OVER-----*");
            updateHero();
            view.gameOver();
        }
    }

    private void setArtefact(Artefact artefact) {
        if (artefact != null) {
            if (artefact instanceof Helm) {
                if (game.getHero().getHelm() == null || view.replaceArtefact("your current helmet [" + game.getHero().getHelm().getName() + "] with the helmet found [" + artefact.getName() + "]")) {
                    game.getHero().equipWithHelm((Helm) artefact);
                    view.displayMessage("You have been equipped with a new helmet: " + artefact.getName());
                }
            }
            else if (artefact instanceof Weapon) {
                if (game.getHero().getWeapon() == null || view.replaceArtefact("your current weapon [" + game.getHero().getWeapon().getName() + "] with the weapon found [" + artefact.getName() + "]")) {
                    game.getHero().equipWithWeapon((Weapon) artefact);
                    view.displayMessage("You have been equipped with a new weapon: " + artefact.getName());
                }
            }
            else if (artefact instanceof Armor) {
                if (game.getHero().getArmor() == null || view.replaceArtefact("your current armor [" + game.getHero().getArmor().getName() + "] with the armor found [" + artefact.getName() + "]")) {
                    game.getHero().equipWithArmor((Armor) artefact);
                    view.displayMessage("You have been equipped with a new armor: " + artefact.getName());
                }
            }
        }
    }

    public void switchButtonClicked() {
        view.changeView();
    }
}
