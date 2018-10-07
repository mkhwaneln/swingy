package swingy.view.select;

public interface SelectHeroView {
    void select();

    void updateHeroInfo(String heroInfo);

    void displayErrorMessages(String message);

    void startGame();

    void createHero();
}
