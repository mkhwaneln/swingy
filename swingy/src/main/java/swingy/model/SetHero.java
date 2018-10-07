package swingy.model;

import swingy.model.artefacts.*;

public class SetHero {
    private int heroID;
    private String heroName;
    private String heroClass;
    private int level;
    private int exp;
    private int attack;
    private int defence;
    private int hp;
    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    public void setHeroID(int heroID) {
        this.heroID = heroID;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
    }

    public Hero getHero() {
        return new Hero(heroID, heroName, heroClass, level, exp, attack, defence, hp, weapon, armor, helm);
    }
}
