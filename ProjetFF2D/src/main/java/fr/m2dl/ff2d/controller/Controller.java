package fr.m2dl.ff2d.controller;

import java.awt.Color;

import java.awt.TextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

import org.w3c.dom.Node;

import fr.m2dl.aco.domain.Ant;
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

public class Controller {

	@FXML
	private AnchorPane gridPanel;

	private Main app;
	private GridPane gridPane;
	final int numCols = 32;
	final int numRows = 16;
	private final static Logger logger = Logger.getLogger(Ant.class.getSimpleName());
	private int entityType = 2;

	@FXML
	private void initialize() {
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
		
		// creation de nid
		FileInputStream imageStream = null;
		try {
			imageStream = new FileInputStream("src/main/java/fr/m2dl/ff2d/view/nid.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image imageRouge = new Image(imageStream);
		ImageView iv = new ImageView(imageRouge);
		iv.setFitWidth(140);
		iv.setFitHeight(140);
		this.gridPane.add(iv, 0, 0);

		// on ajoute le listener aux cellules de la grille
		for (int i = 0 ; i < numCols ; i++) {
			for (int j = 0; j < numRows; j++) {
				addPane(i, j);
			}
		}
	}

	private void addPane(final int colIndex, final int rowIndex) {
		Pane pane = new Pane();
		pane.setOnMousePressed(new EventHandler<Event>() {
			public void handle(Event event) {
				launchPassiveEntity( colIndex, rowIndex);
			}
		});
		gridPane.add(pane, colIndex, rowIndex);


	}

	public void setApp(Main main) {
		this.app = main;
	}

	private void launchAnt(int x, int y) {
		// creation de la fourmi dans ACO
		Ant ant = new Ant();

		FileInputStream imageStream = null;
		try {
			imageStream = new FileInputStream("src/main/java/fr/m2dl/ff2d/view/ant.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image imageRouge = new Image(imageStream);
		ImageView i = new ImageView(imageRouge);
		i.setFitWidth(40);
		i.setFitHeight(60);

		// lancer une fourmi sur l'interface graphique
		logger.info("je suis une fourmi graphique.");
		this.gridPane.add(i, x, y);

	}

	private void launchPassiveEntity(int x, int y) {
		if(this.entityType != 0){

			FileInputStream foodStream = null;
			FileInputStream obstacleStream = null;
			try {
				foodStream = new FileInputStream("src/main/java/fr/m2dl/ff2d/view/food.jpeg");
				obstacleStream = new FileInputStream("src/main/java/fr/m2dl/ff2d/view/obstacle.jpg");
			} catch (FileNotFoundException ev) {
				// TODO Auto-generated catch block
				ev.printStackTrace();
			}
			Image food = new Image(foodStream);
			ImageView ivFood = new ImageView(food);
			ivFood.setFitWidth(40);
			ivFood.setFitHeight(40);


			Image obstacle = new Image(obstacleStream);
			ImageView ivObstacle = new ImageView(obstacle);
			ivObstacle.setFitWidth(40);
			ivObstacle.setFitHeight(40);

			// lancer une entite sur l'interface graphique
			switch(this.entityType){
			case 1:
				logger.info("Creation de l'entité Nourriture");

				// affichage de l'entite
				this.gridPane.add(ivFood, x, y);
				break;
			case 2:
				logger.info("Creation de l'entité Obstacle");
				// affichage de l'entite
				this.gridPane.add(ivObstacle, x, y);
				break;
			}

		}

	}

	public void launchSimulation() {
		// par defaut le nid est au millieux de la carte
		this.launchAnt(numCols/2, numRows/2);

	}

	public void stopSimulation() {

		gridPane.getChildren().clear();
		initialize();
	}

	public void selectFood(){
		this.entityType = 1;
	}

	public void selectRock(){
		this.entityType = 2;
	}



}
