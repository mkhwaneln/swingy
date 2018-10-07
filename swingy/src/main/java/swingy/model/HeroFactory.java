package swingy.model;

public abstract class HeroFactory {
    public static Hero newHero(String name, String heroClass) {
        switch (heroClass.toUpperCase()) {
            case "KNIGHT":
                return CreateHeroes.createKnight(name);
            case "GLADIATOR":
                return CreateHeroes.createGladiator(name);
            case "SORCERER":
                return CreateHeroes.createSorcerer(name);
            case "WIZARD":
                return CreateHeroes.createWizard(name);
            case "ASSASSIN":
                return CreateHeroes.createAssassin(name);
            case "NINJA":
                return CreateHeroes.createNinja(name);
            case "HEALER":
                return CreateHeroes.createHealer(name);
            default:
                throw new IllegalArgumentException("Hero class '" + heroClass + "' does not exist");
        }
    }
}
