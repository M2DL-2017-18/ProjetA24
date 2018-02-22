package fr.m2dl.aco.domain;

import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.aco.visitors.IBoxableVisitor;
import fr.m2dl.infra.PassiveEntity;

public class Food extends PassiveEntity implements IBoxable {

    /**
     * Quantité de nourriture restante
     */
    private int quantity;

    /**
     * Coordonnées de la pile de nourriture
     */
    private Coordinates coordinates;

    /**
     * Constructeur avec la position initiale et la quantité de nourriture
     *
     * @param coordinates
     * @param quantity    Quantité de nourriture restante
     */
    public Food(Coordinates coordinates, int quantity) {
        this.coordinates = coordinates;
        this.quantity = quantity;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
	public boolean acceptVisitor(IBoxableVisitor visitor) {
		return visitor.verify(this);
	}

}
