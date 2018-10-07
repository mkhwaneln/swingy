package swingy.controller;

import swingy.exceptions.HeroValidationException;
import swingy.model.Game;
import swingy.model.HeroFactory;
import swingy.utils.HeroDB;
import swingy.view.create.CreateHeroView;
import swingy.model.Hero;

public class CreateHeroController {
    private CreateHeroView view;
    private Game newGame;

    public CreateHeroController(CreateHeroView view) {
        this.view = view;
        newGame = Game.loadGame();
    }

    public void createButtonClicked(String name, String heroClass) {
        Hero hero;

        try {
            hero = HeroFactory.newHero(name, heroClass);
            hero.validate();
        } catch (IllegalArgumentException | HeroValidationException e) {
            view.displayErrorMessages(e.getMessage());
            view.getInput();
            return;
        }

        HeroDB.createHeroTable();
        int id = HeroDB.insert(hero.getName(), hero.getHeroClass(), hero.getLevel(), hero.getExp(), hero.getAttack(), hero.getDefence(), hero.getHitPoints());
        hero.setHeroID(id);
        newGame.initGame(hero);
        view.startGame();
    }
}
