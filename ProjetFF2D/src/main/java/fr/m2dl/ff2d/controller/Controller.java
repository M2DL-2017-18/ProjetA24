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

	private void addCellListener(final int colIndex, final int rowIndex) {
		GraphicElement g = new GraphicFloor();
		ImageView image = g.getImageView();
		image.setOnMousePressed(new EventHandler<Event>() {
			public void handle(Event event) {
				launchPassiveEntity(colIndex, rowIndex);
			}
		});
		
		grid.addGraphicElement(image, colIndex, rowIndex);
	}

	private void launchAnt() {
		this.env.createAnts(1, new Behavior());
		logger.info("je suis une fourmi graphique.");
	}

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

	public void launchSimulation() {
		this.env = new Environment(grid.getGridRows(), grid.getGridCols());
		launchNest();
		launchAnt();
		//this.env.run();
		
		for (int i = 0; i < grid.getGridCols(); i++) {
			for (int j = 0; j < grid.getGridRows(); j++) {
				addCellListener(i, j);
			}
		}	
		refreshUI();
	}

	public void stopSimulation() {
		this.timer.cancel();
		this.env = new Environment(grid.getGridRows(), grid.getGridCols());
		grid.clearGrid();
		initialize();
	}

	public void selectFood() {
		this.entityType = 1;
	}

	public void selectRock() {
		this.entityType = 2;
	}

	public void refreshUI() {

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
							if(boxables.isEmpty()) {
								addCellListener(i,j);
							}
							
							for(IBoxable b : boxables){
								GraphicElement element;
								if (b instanceof Ant) {	
									element = new GraphicAnt();
									grid.addGraphicElement(element.getImageView(), b.getCoordinates().getY(), b.getCoordinates().getX());
								} else if (b instanceof Food) {
									element = new GraphicFood();
									grid.addGraphicElement(element.getImageView(), b.getCoordinates().getY(), b.getCoordinates().getX());
								} else if (b instanceof Obstacle) {
									element = new GraphicObstacle();
									grid.addGraphicElement(element.getImageView(), b.getCoordinates().getY(), b.getCoordinates().getX());
								} else if (b instanceof Nest) {
									element = new GraphicNest();
									grid.addGraphicElement(element.getImageView(), b.getCoordinates().getY(), b.getCoordinates().getX());
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
