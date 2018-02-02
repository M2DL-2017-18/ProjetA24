package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBoxable;

import java.util.List;

/**
 * Définition d'une case de la grille
 */
public class Box {
    private List<IBoxable> boxables;

    public Box(){}

    /**
     * Ajout d'un boxable(agent,entitée passive, entitée active) dans la liste des boxables d'une case
     * @param boxable boxable à ajouter (agent,entitée passive, entitée active)
     */
    public void addBoxable(IBoxable boxable){
        boxables.add(boxable);
    }

    /**
     *
     * @param boxable
     */
    public void removeBoxable(IBoxable boxable){
        boxables.remove(boxable);
    }
    public List<IBoxable> getBoxables() {
        return boxables;
    }

    public void setBoxables(List<IBoxable> boxables) {
        this.boxables = boxables;
    }
}
