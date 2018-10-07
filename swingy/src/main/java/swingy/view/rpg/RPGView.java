package swingy.view.rpg;

import swingy.model.Game;
import swingy.utils.Coordinates;

public interface RPGView {
    void start();
    void printMap(boolean[][] map, Coordinates heroCoord);
    void update(Game game);
    void gameOver();
    void displayMessage(String message);
    void getEnemyCollisionInput();
    boolean replaceArtefact(String replaceMessage);
    void changeView();
}
