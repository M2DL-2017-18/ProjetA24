package fr.m2dl.aco.util;

import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.services.IAcoEnvironment;

public class Util {

    /**
     * Normalise les coordonnées pour qui'il reste dans la grille
     *
     * @param  env l'environnement
     * @param coordinates les coordonnées à normaliser
     * @return les coordonnées normalisés
     */
    public static Coordinates stayInGrid(IAcoEnvironment env, Coordinates coordinates){
        int row = env.getGrid().length;
        int col = env.getGrid()[0].length;
        int x = coordinates.getX() % row;
        if(x < 0) x += row;
        int y = coordinates.getY() % col;
        if(y < 0) y += col;
        return new Coordinates(x,y);
    }
}
