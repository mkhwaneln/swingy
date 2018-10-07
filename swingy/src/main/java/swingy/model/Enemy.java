package swingy.model;

import swingy.model.artefacts.Artefact;

public class Enemy extends Character {
    private Artefact artefact;

    public Enemy(String name, int attack, int defence, int hitPoints, Artefact artefact) {
        super(name, attack, defence, hitPoints);
        this.artefact = artefact;
    }

    public Artefact getArtefact() {
        return artefact;
    }
}
