package swingy.controller;

import swingy.exceptions.HeroValidationException;
import swingy.model.Game;
import swingy.model.Hero;
import swingy.utils.HeroDB;
import swingy.view.select.SelectHeroView;

import java.util.ArrayList;

public class SelectHeroController {
    private SelectHeroView view;
    private Game game;

    public SelectHeroController(SelectHeroView view) {
        this.view = view;
        game = Game.loadGame();
    }

    public String[] getHeroList() {
        ArrayList<String> list = HeroDB.selectAll();
        String[] heroArr = new String[list.size()];

        heroArr = list.toArray(heroArr);
        return heroArr;
    }

    public void selectedHero(int index) {
        Hero hero = HeroDB.selectHero(index);
        view.updateHeroInfo(hero.toString());
    }

    public void selectButtonClicked(int index) {
        Hero hero;

        try {
            hero = HeroDB.selectHero(index);
            hero.validate();
        } catch (HeroValidationException he) {
            view.displayErrorMessages(he.getMessage());
            return;
        }

        game.initGame(hero);
        view.startGame();
    }

    public void createButtonClicked() {
        view.createHero();
    }
}
