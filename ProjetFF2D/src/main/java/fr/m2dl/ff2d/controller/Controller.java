package fr.m2dl.ff2d.controller;

import java.awt.Color;

import java.awt.TextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.w3c.dom.Node;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.aco.domain.Box;
import fr.m2dl.aco.domain.Coordinates;
import fr.m2dl.aco.domain.Environment;
import fr.m2dl.aco.domain.Food;
import fr.m2dl.aco.domain.Nest;
import fr.m2dl.aco.domain.Obstacle;
import fr.m2dl.aco.services.IBehavior;
import fr.m2dl.aco.services.IBoxable;
import fr.m2dl.ff2d.application.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.application.Platform;

public class Controller {

	@FXML
	private AnchorPane gridPanel;

	private Main app;
	private GridPane gridPane;
	final int numCols = 32;
	final int numRows = 16;
	private final static Logger logger = Logger.getLogger(Ant.class.getSimpleName());
	private int entityType = 2;

	private Environment env;

	private Thread refresh;

	private Image imageNid;

	private ImageView imageFood;

	private Image imageObstacle;

	private Image imageFourmi;

	private Image imageNourr;

	@FXML
	private void initialize() {
		// on cree l'environnement
		this.env = new Environment(numRows, numCols);

		// on lance la grille et on créé ses colonnes et lignes
		this.gridPane = new GridPane();
		this.gridPane.setGridLinesVisible(true);
		for (int i = 0; i < numCols; i++) {
			ColumnConstraints colConst = new ColumnConstraints(32);
			colConst.setPercentWidth(100.0 / numCols);
			colConst.setMinWidth(0);
			gridPane.getColumnConstraints().add(colConst);
		}
		for (int i = 0; i < numRows; i++) {
			RowConstraints rowConst = new RowConstraints(32);
			rowConst.setPercentHeight(100.0 / numRows);
			rowConst.setMinHeight(0);
			gridPane.getRowConstraints().add(rowConst);

		}

		// on ajoute au panel la grille
		this.gridPanel.getChildren().add(gridPane);

		// on redimensionne la grille pour qu'elle s'adapte a la bonne taille
		this.gridPane.setStyle("-fx-background-color: white;");
		this.gridPane.prefWidthProperty().bind(this.gridPanel.widthProperty());
		this.gridPane.prefHeightProperty().bind(this.gridPanel.heightProperty());

		// on ajoute le listener aux cellules de la grille
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				addPane(i, j);
			}
		}

		// on recupere l'ensemble des images au lancement du projet
		FileInputStream imageStream = null;
		try {
			imageStream = new FileInputStream("src/main/java/fr/m2dl/ff2d/view/nid.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		imageNid = new Image(imageStream);

		FileInputStream foodStream = null;
		FileInputStream obstacleStream = null;
		try {
			foodStream = new FileInputStream("src/main/java/fr/m2dl/ff2d/view/food.jpeg");
			obstacleStream = new FileInputStream("src/main/java/fr/m2dl/ff2d/view/obstacle.jpg");
		} catch (FileNotFoundException ev) {
			// TODO Auto-generated catch block
			ev.printStackTrace();
		}
		imageNourr = new Image(foodStream);

		imageObstacle = new Image(obstacleStream);

		FileInputStream imageStream2 = null;
		try {
			imageStream2 = new FileInputStream("src/main/java/fr/m2dl/ff2d/view/ant.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		imageFourmi = new Image(imageStream2);

	}

	public void launchNest() {
		// on met à jour env
		this.env.createNest(new Coordinates(1, 1));
	}

	private void addPane(final int colIndex, final int rowIndex) {
		Pane pane = new Pane();
		pane.setOnMousePressed(new EventHandler<Event>() {
			public void handle(Event event) {
				launchPassiveEntity(colIndex, rowIndex);
			}
		});
		gridPane.add(pane, colIndex, rowIndex);

	}

	public void setApp(Main main) {
		this.app = main;
	}

	private void launchAnt(int x, int y) {
		// creation de la fourmi dans ACO
		this.env.createAnts(1, new Behavior());

		// lancer une fourmi sur l'interface graphique
		logger.info("je suis une fourmi graphique.");
	}

	private void launchPassiveEntity(int x, int y) {
		if (this.entityType != 0) {
			// lancer une entite sur l'interface graphique
			switch (this.entityType) {
			case 1:
				logger.info("Creation de l'entité Nourriture");
				// affichage de l'entite
				this.env.createFood(new Coordinates(y, x), 1);
				break;
			case 2:
				logger.info("Creation de l'entité Obstacle");
				// affichage de l'entite
				this.env.createObstacle(new Coordinates(y, x));
				break;
			}
		}
	}

	public void launchSimulation() {
		// on lance le nid
		launchNest();
		// on lance une fourmie
		launchAnt(numCols / 2, numRows / 2);

		// on lance l'interface graphique
		refreshUI();

	}

	public void stopSimulation() {
		gridPane.getChildren().clear();
		initialize();
	}

	public void selectFood() {
		this.entityType = 1;
	}

	public void selectRock() {
		this.entityType = 2;
	}

	public void refreshUI() {

		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Box[][] box = env.getGrid();
						for (int i = 0; i < numRows; i++) {
							for (int j = 0; j < numCols; j++) {
								List<IBoxable> boxables = box[i][j].getBoxables();
								for(IBoxable b : boxables){
								//if (boxables.size() > 0) {
									//IBoxable b = boxables.get(0);
									
									if (b instanceof Ant) {
										// afficher ant
										ImageView imageAnt = new ImageView(imageFourmi);
										imageAnt.setFitWidth(40);
										imageAnt.setFitHeight(60);
										gridPane.add(imageAnt, b.getCoordinates().getY(), b.getCoordinates().getX());
									} else if (b instanceof Food) {
										// afficher food
										ImageView imageAnt = new ImageView(imageNourr);
										imageAnt.setFitWidth(40);
										imageAnt.setFitHeight(60);
										gridPane.add(imageAnt, b.getCoordinates().getY(), b.getCoordinates().getX());
									} else if (b instanceof Obstacle) {
										// afficher obstacle
										ImageView imageAnt = new ImageView(imageObstacle);
										imageAnt.setFitWidth(40);
										imageAnt.setFitHeight(60);
										gridPane.add(imageAnt, b.getCoordinates().getY(), b.getCoordinates().getX());
									} else if (b instanceof Nest) {
										// afficher nest
										ImageView imageAnt = new ImageView(imageNid);
										imageAnt.setFitWidth(40);
										imageAnt.setFitHeight(60);
										//gridPane.add(imageAnt, b.getCoordinates().getX(), b.getCoordinates().getY());
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
