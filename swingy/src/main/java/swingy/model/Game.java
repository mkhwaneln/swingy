package swingy.model;

import swingy.model.artefacts.Armor;
import swingy.model.artefacts.Artefact;
import swingy.model.artefacts.Helm;
import swingy.model.artefacts.Weapon;
import swingy.utils.Coordinates;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private static Game gameplay = null;
    private Hero hero;
    private Coordinates heroCoords;
    private int mapSize;
    private boolean[][] map;

    private Game() {

    }

    public int getMapSize() {
        return mapSize;
    }

    public boolean[][] getMap() {
        return map;
    }

    public Hero getHero() {
        return hero;
    }

    public Coordinates getHeroCoords() {
        return heroCoords;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public void setMap(boolean[][] map) {
        this.map = map;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setHeroCoords(Coordinates heroCoords) {
        this.heroCoords = heroCoords;
    }

    public static Game loadGame() {
        if (gameplay == null) {
            gameplay = new Game();
        }
        return gameplay;
    }

    public void initGame(Hero hero) {
        this.hero = hero;
        createMap();
        createEnemies();
        placeHero();
    }

    private void createMap() {
        int level = hero.getLevel();
        mapSize = (level - 1) * 5 + 10 - (level % 2);
        map = new boolean[mapSize][mapSize];
    }

    private void createEnemies() {
        int randomNum;
        int level = hero.getLevel();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
               randomNum = ThreadLocalRandom.current().nextInt(0, 101);
               if ((level + 1) * 10 >= randomNum)
                   map[i][j] = true;
            }
        }
    }

    public Enemy createEnemy() {
        int attack = ThreadLocalRandom.current().nextInt(hero.getAttack() - 20, hero.getAttack() + 2 + hero.getLevel());
        int defence = ThreadLocalRandom.current().nextInt(hero.getDefence() - 20, hero.getDefence() + 2 + hero.getLevel());
        int hitPoints = ThreadLocalRandom.current().nextInt(hero.getHitPoints() - 10, hero.getHitPoints() + 20 + hero.getLevel());
        Artefact artefact = createArtefact();

        if (attack < 0)
            attack = -attack;
        if (defence < 0)
            defence = -defence;
        if (hitPoints < 0)
            hitPoints = -hitPoints;

        return new Enemy("Villain", attack, defence, hitPoints, artefact);
    }

    private void placeHero() {
        heroCoords = new Coordinates(mapSize / 2, mapSize / 2);
        map[heroCoords.getY()][heroCoords.getX()] = false;
    }

    private Artefact createArtefact() {
        int rand = ThreadLocalRandom.current().nextInt(0, 11);
        Artefact artefact = null;

        if (rand == 0)
            artefact = new Helm("Royal Knight Helmet", ThreadLocalRandom.current().nextInt(1, 7 * (hero.getLevel() + 1)));
        else if (rand == 1)
            artefact = new Helm("Crown", ThreadLocalRandom.current().nextInt(1, 6 * (hero.getLevel() + 1)));
        else if (rand == 2)
            artefact = new Helm("Hat", ThreadLocalRandom.current().nextInt(1, 9 * (hero.getLevel() + 1)));
        else if (rand == 3)
            artefact = new Weapon("Dagger", ThreadLocalRandom.current().nextInt(1, 6 * (hero.getLevel() + 1)));
        else if (rand == 4)
            artefact = new Weapon("Sword", ThreadLocalRandom.current().nextInt(1, 8 * (hero.getLevel() + 1)));
        else if (rand == 5)
            artefact = new Weapon("Axe", ThreadLocalRandom.current().nextInt(1, 10 * (hero.getLevel() + 1)));
        else if (rand == 6)
            artefact = new Weapon("Flail", ThreadLocalRandom.current().nextInt(1, 3 * (hero.getLevel() + 1)));
        else if (rand == 7)
            artefact = new Armor("Cape", ThreadLocalRandom.current().nextInt(1, 2 * (hero.getLevel() + 1)));
        else if (rand == 8)
            artefact = new Armor("Shield", ThreadLocalRandom.current().nextInt(1, 5 * (hero.getLevel() + 1)));
        else if (rand == 9)
            artefact = new Armor("Chainmail", ThreadLocalRandom.current().nextInt(1, 7 * (hero.getLevel() + 1)));
        return artefact;
    }

    public int resultOfFight(Character enemy) {
        int exp = enemy.getAttack() + enemy.getDefence() + enemy.getHitPoints();
        int rand = ThreadLocalRandom.current().nextInt(0, 101);

        if (rand < 3)
            return exp;
        else if (rand > 48)
            return -1;

        if (hero.fight(enemy))
            return exp;
        else
            return -1;
    }
}
