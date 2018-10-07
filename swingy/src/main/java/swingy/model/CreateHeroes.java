package swingy.model;

public class CreateHeroes {
    private static SetHero createNewHero(String name) {
        SetHero hero = new SetHero();
        hero.setHeroName(name);
        hero.setLevel(0);
        hero.setExp(0);
        return hero;
    }

    public static Hero createKnight(String name) {
        SetHero knight = createNewHero(name);
        knight.setHeroClass("Knight");
        knight.setHp(90);
        knight.setAttack(40);
        knight.setDefence(50);
        return knight.getHero();
    }

    public static Hero createGladiator(String name) {
        SetHero gladiator = createNewHero(name);
        gladiator.setHeroClass("Gladiator");
        gladiator.setHp(90);
        gladiator.setAttack(60);
        gladiator.setDefence(40);
        return gladiator.getHero();
    }

    public static Hero createSorcerer(String name) {
        SetHero sorcerer = createNewHero(name);
        sorcerer.setHeroClass("Sorcerer");
        sorcerer.setHp(110);
        sorcerer.setAttack(80);
        sorcerer.setDefence(60);
        return sorcerer.getHero();
    }

    public static Hero createWizard(String name) {
        SetHero wizard = createNewHero(name);
        wizard.setHeroClass("Wizard");
        wizard.setHp(80);
        wizard.setAttack(40);
        wizard.setDefence(30);
        return wizard.getHero();
    }

    public static Hero createAssassin(String name) {
        SetHero assassin = createNewHero(name);
        assassin.setHeroClass("Assassin");
        assassin.setHp(90);
        assassin.setAttack(50);
        assassin.setDefence(15);
        return assassin.getHero();
    }

    public static Hero createNinja(String name) {
        SetHero ninja = createNewHero(name);
        ninja.setHeroClass("Ninja");
        ninja.setHp(100);
        ninja.setAttack(60);
        ninja.setDefence(25);
        return ninja.getHero();
    }

    public static Hero createHealer(String name) {
        SetHero healer = createNewHero(name);
        healer.setHeroClass("Healer");
        healer.setHp(80);
        healer.setAttack(10);
        healer.setDefence(20);
        return healer.getHero();
    }
}
