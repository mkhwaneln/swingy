package swingy.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class Character {
    @NotNull(message = "Character needs to have a name")
    @Size(min = 2, max = 20, message = "The length of the name should be between 2 and 20 characters")
    protected String name;

    @Min(value = 0, message = "Attack level should not be less than 0")
    protected int attack;

    @Min(value = 0, message = "Defence level should not be less than 0")
    protected int defence;

    @Min(value = 1, message = "Level of hit points should not be less than 1")
    protected int hitPoints;

    public Character(String name, int attack, int defence, int hitPoints) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    private void attack(Character opponent) {
        if (this.attack > opponent.defence) {
            opponent.setHitPoints(opponent.getHitPoints() - (this.attack - opponent.defence));
        }
    }

    public boolean fight(Character opponent) {
        while (opponent.getHitPoints() > 0 && getHitPoints() > 0) {
            this.attack(opponent);
            opponent.attack(this);
        }
        return this.getHitPoints() > 0;
    }
}
