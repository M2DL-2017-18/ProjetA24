package fr.m2dl.ff2d.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import fr.m2dl.aco.domain.AcoEnvironment;
import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.domain.Nest;
import fr.m2dl.aco.domain.Obstacle;
import fr.m2dl.aco.domain.Pheromone;
import fr.m2dl.aco.services.IAcoEnvironment;
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
	private IAcoEnvironment env;
	private Timer timer;
	private IGrid grid;
	private Map<IBoxable, GraphicElement> boxablesMap = new HashMap<>(); 
	
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
		ImageView image = g.getImageView();
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
		this.env.createAnts(3, new Behavior());
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
		this.env = new AcoEnvironment(grid.getGridRows(), grid.getGridCols());
		launchNest();
		launchAnt();
		refreshUI();

	}

	/**
	 * Listener sur le bouton d'arret de la simulation du fourragement 
	 * 
	 */
	public void stopSimulation() {
		this.timer.cancel();
		this.env = new AcoEnvironment(grid.getGridRows(), grid.getGridCols());
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
		
		for (int i = 0; i < grid.getGridRows(); i++) {
			for (int j = 0; j < grid.getGridCols(); j++) {
				addFloor(j,i);

			}
		}
		
		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						
						Box[][] box = env.getGrid();
						for (int i = 0; i < grid.getGridRows(); i++) {
							for (int j = 0; j < grid.getGridCols(); j++) {
								
								List<IBoxable> boxables = box[i][j].getBoxables();							
								for(IBoxable b : boxables){
									if(!boxablesMap.containsKey(b)) {
										if (b instanceof Ant) {										
											boxablesMap.put(b, new GraphicAnt());								
										} else if (b instanceof Food) {									
											boxablesMap.put(b, new GraphicFood());								
										} else if (b instanceof Obstacle) {									
											boxablesMap.put(b, new GraphicObstacle());					
										} else if (b instanceof Nest) {																
											boxablesMap.put(b, new GraphicNest());					
										} else if (b instanceof Pheromone) {																
											boxablesMap.put(b, new GraphicPheromone());					
										}
									}
									grid.addGraphicElement(boxablesMap.get(b).getImageView(), b.getCoordinates().getY(), b.getCoordinates().getX());								
								
								}
							}
	
						}
						env.run();
					}
				});
			}
		}, 0, 500);

	}
	

}