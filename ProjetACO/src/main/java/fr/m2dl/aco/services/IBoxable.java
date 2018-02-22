package fr.m2dl.aco.services;

import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.visitors.IBoxableVisitor;

/**
 * Created by hichem on 02/02/2018.
 */
public interface IBoxable {
    Coordinates getCoordinates();
    void setCoordinates(Coordinates coordinates);
    
    boolean acceptVisitor(IBoxableVisitor visitor);
}
