package swingy.model;

import swingy.exceptions.HeroValidationException;
import swingy.model.artefacts.*;

import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.logging.Level;

public class Hero extends Character {
    private Weapon weapon;
    private Armor armor;
    private Helm helm;
    private int heroID;

    @Min(value = 0, message = "Level can't be less than 0")
    private int level;

    @Min(value = 0, message = "Experience level can't be less than 0")
    private int exp;

    @NotNull(message = "Hero class must be defined")
    private String heroClass;

    public Hero(int heroID, String heroName, String heroClass, int level, int exp, int attack, int defence, int hp, Weapon weapon, Armor armor, Helm helm) {
        super(heroName, attack, defence, hp);
        this.heroID = heroID;
        this.level = level;
        this.exp = exp;
        this.heroClass = heroClass;
        this.weapon = weapon;
        this.armor = armor;
        this.helm = helm;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public int getHeroID() {
        return heroID;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Helm getHelm() {
        return helm;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public void setHeroID(int heroID) {
        this.heroID = heroID;
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

    public void validate() throws HeroValidationException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        Set<ConstraintViolation<Hero>> violations = validator.validate(this);
        if (violations.size() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Hero validation error(s): ");
            sb.append(violations.size());
            sb.append("\n");
            for (ConstraintViolation<Hero> violation : violations) {
                sb.append("Offending property: ");
                sb.append(violation.getPropertyPath());
                sb.append("\nValue: ");
                sb.append(violation.getInvalidValue());
                sb.append("\nMessage: ");
                sb.append(violation.getMessage());
                sb.append("\n");
            }
            throw new HeroValidationException(sb.toString());
        }
    }

    public void equipWithWeapon(Weapon weapon) {
        if (this.weapon != null) {
            this.attack = this.attack - this.weapon.getPoints();
        }
        this.attack = this.attack + weapon.getPoints();
        this.weapon = weapon;
    }

    public void equipWithArmor(Armor armor) {
        if (this.armor != null) {
            this.defence = this.defence - this.armor.getPoints();
        }
        this.defence = this.defence + armor.getPoints();
        this.armor = armor;
    }

    public void equipWithHelm(Helm helm) {
        if (this.helm != null) {
            this.hitPoints = this.hitPoints - this.helm.getPoints();
            if (this.hitPoints + helm.getPoints() <= 0) {
                this.hitPoints = this.hitPoints + this.helm.getPoints();
                return;
            }
        }
        this.hitPoints = this.hitPoints + helm.getPoints();
        this.helm = helm;
    }

    public void addExperience(int addXP) {
        int nextLevel = (level + 1) * 1000 + level * level * 450;

        if (exp + addXP >= nextLevel) {
            level++;
            hitPoints = hitPoints + 50 + level * 10;
            attack = attack + level * 3;
            defence = defence + level * 2;
        }
        exp = exp + addXP;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Hero class: ").append(heroClass).append("\n");
        sb.append("Level: ").append(level).append("\n");
        sb.append("Experience: ").append(exp).append("XP\n");
        sb.append("Attack: ").append(attack).append("\n");
        sb.append("Defence: ").append(defence).append("\n");
        sb.append("Hit points: ").append(hitPoints).append("\n");
        sb.append("Weapon: ");
        if (weapon != null) {
            sb.append(weapon.getName()).append(" (level of attack +").append(weapon.getPoints()).append(")\n");
        }
        else {
            sb.append("No weapon yet\n");
        }
        sb.append("Armor: ");
        if (armor != null) {
            sb.append(armor.getName()).append(" (level of defence +").append(armor.getPoints()).append(")\n");
        }
        else {
            sb.append("No armor yet\n");
        }
        sb.append("Helm: ");
        if (helm != null) {
            sb.append(helm.getName()).append(" (level of hit points +").append(helm.getPoints()).append(")\n");
        }
        else {
            sb.append("No helmet yet\n");
        }
        return sb.toString();
    }
}
