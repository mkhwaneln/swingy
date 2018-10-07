package swingy.controller;

import swingy.view.create.CreateView;

public class CreateController {
    private CreateView view;

    public CreateController(CreateView view) {
        this.view = view;
    }

    public void createHeroButtonClicked() {
        view.createHero();
    }

    public void selectHeroButtonClicked() {
        view.selectHero();
    }

    public void switchButtonClicked() {
        view.switchView();
    }
}
