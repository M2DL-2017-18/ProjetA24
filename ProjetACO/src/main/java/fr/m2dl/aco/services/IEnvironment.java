package fr.m2dl.aco.services;

import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.infra.LocalEnv;

/**
 * Created by hichem on 02/02/2018.
 */
public interface IEnvironment extends LocalEnv{

    /**
     * Création de x fourmis avec le même comportement
     * Ajout de ces fourmis dans la grille à l'emplacement du nid
     * Si le nid n'existe pas on crée un par défaut aux coordonnées (0,0)
     *
     * @param number   nombre de fourmis à créer
     * @param behavior comportement des fourmis
     */
    void createAnts(int number, IBehavior behavior);

    /**
     * Création d'une nourriture à un emplacement donné
     *
     * @param coordinates Coordonnées de l'emplacement
     * @param quantity La quantité de la nourriture
     */
    void createFood(Coordinates coordinates, int quantity);

    /**
     * Création d'un obstacle à un emplacement donné (que sur une case)
     *
     * @param coordinates Coordonnées de l'emplacement
     */
    void createObstacle(Coordinates coordinates);

    /**
     *  Création d'un nid à un emplacement donné (que sur une case)
     * @param coordinates Coordonnées de l'emplacement
     */
    void createNest(Coordinates coordinates);

    /**
     * Getter de la grille
     *
     * @return la grille
     */
    Box[][] getGrid();


}
