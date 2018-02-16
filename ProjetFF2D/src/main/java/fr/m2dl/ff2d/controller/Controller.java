package fr.m2dl.ff2d.controller;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Environment;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.domain.Nest;
import fr.m2dl.aco.domain.Obstacle;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.ff2d.model.Behavior;
import fr.m2dl.ff2d.view.GraphicAnt;
import fr.m2dl.ff2d.view.GraphicElement;
import fr.m2dl.ff2d.view.GraphicFloor;
import fr.m2dl.ff2d.view.GraphicFood;
import fr.m2dl.ff2d.view.GraphicNest;
import fr.m2dl.ff2d.view.GraphicObstacle;
import fr.m2dl.ff2d.view.GraphicPheromone;
import fr.m2dl.ff2d.view.Grid;
import fr.m2dl.ff2d.view.interfaces.IGrid;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Controller {

	private final static Logger logger = Logger.getLogger(Ant.class.getSimpleName());
	private int entityType = 0;
	private Environment env;
	private Timer timer;
	private IGrid grid;
	
	@FXML
	private AnchorPane gridPanel;

	
	@FXML
	private void initialize() {
		grid = new Grid(gridPanel);	

	}

	public void launchNest() {
		this.env.createNest(new Coordinates(0, 0));
	}

    /**
	 * Ajoute le terrain sur la grille et met un listeners sur la case
     *
     * @param colIndex indice de la colonne de la grille
     * @param rowIndex indice de la ligne de la grille
     */
	private void addFloor(final int colIndex, final int rowIndex) {
		GraphicElement g = new GraphicFloor();
		ImageView image = g.createImageView();
		image.setOnMousePressed(new EventHandler<Event>() {
			public void handle(Event event) {
				launchPassiveEntity(colIndex, rowIndex);
			}
		});
		
		grid.addGraphicElement(image, colIndex, rowIndex);
	}

	/**
	 * Demande la création de la fourmi 
	 * 
	 */
	private void launchAnt() {
		this.env.createAnts(1, new Behavior());
		logger.info("je suis une fourmi graphique.");
	}

	/**
	 * Permet de creer les entites passives (nourriture et obstacle)
	 * 
	 */
	private void launchPassiveEntity(int x, int y) {
		if (this.entityType != 0) {
			switch (this.entityType) {
			case 1:
				logger.info("Creation de l'entité Nourriture");
				this.env.createFood(new Coordinates(y, x), 1);
				break;
			case 2:
				logger.info("Creation de l'entité Obstacle");
				this.env.createObstacle(new Coordinates(y, x));
				break;
			}
		}
	}

	/**
	 * Listener sur le bouton pour lancer la simulation du fourragement 
	 * 
	 */
	public void launchSimulation() {
		this.env = new Environment(grid.getGridRows(), grid.getGridCols());
		launchNest();
		launchAnt();
		//this.env.run();
		
		for (int i = 0; i < grid.getGridCols(); i++) {
			for (int j = 0; j < grid.getGridRows(); j++) {
				addFloor(i, j);
			}
		}	
		refreshUI();
	}

	/**
	 * Listener sur le bouton d'arret de la simulation du fourragement 
	 * 
	 */
	public void stopSimulation() {
		this.timer.cancel();
		this.env = new Environment(grid.getGridRows(), grid.getGridCols());
		grid.clearGrid();
		initialize();
	}

	/**
	 * Listener sur le bouton de selection de la nourriture
	 */
	public void selectFood() {
		this.entityType = 1;
	}

	/**
	 * Listener sur le bouton de selection des obstacles
	 */
	public void selectObstacle() {
		this.entityType = 2;
	}
	
	
	/**
	 * Permet de mettre à jour l'interface graphique
	 */
	public void refreshUI() {

		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {
		GraphicElement graphicAnt = new GraphicAnt();
		GraphicElement graphicFood = new GraphicFood();
		GraphicElement graphicObstacle = new GraphicObstacle();
		GraphicElement graphicNest = new GraphicNest();
		GraphicElement graphicPheromone = new GraphicPheromone();
		
		@Override
		public void run() {
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					
					Box[][] box = env.getGrid();
					for (int i = 0; i < grid.getGridRows(); i++) {
						for (int j = 0; j < grid.getGridCols(); j++) {
							
							List<IBoxable> boxables = box[i][j].getBoxables();
							if(boxables.isEmpty()) {						
								addFloor(j,i);							
							}
							
							for(IBoxable b : boxables){
							
								if (b instanceof Ant) {										
									grid.addGraphicElement(graphicAnt.createImageView(), b.getCoordinates().getY(), b.getCoordinates().getX());								
								} else if (b instanceof Food) {									
									grid.addGraphicElement(graphicFood.createImageView(), b.getCoordinates().getY(), b.getCoordinates().getX());								
								} else if (b instanceof Obstacle) {									
									grid.addGraphicElement(graphicObstacle.createImageView(), b.getCoordinates().getY(), b.getCoordinates().getX());						
								} else if (b instanceof Nest) {																
									grid.addGraphicElement(graphicNest.createImageView(), b.getCoordinates().getY(), b.getCoordinates().getX());							
								} else if (b instanceof Pheromone) {																
									grid.addGraphicElement(graphicPheromone.createImageView(), b.getCoordinates().getY(), b.getCoordinates().getX());							
								}
								
							}
						}

					}
				}
			});
		}
	}, 0, 500);

	}

}
