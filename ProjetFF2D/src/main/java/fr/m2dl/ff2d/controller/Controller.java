package fr.m2dl.ff2d.controller;

import java.awt.Color;

import java.awt.TextField;
import java.util.logging.Logger;

import org.w3c.dom.Node;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.ff2d.application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
	
	@FXML
	private AnchorPane gridPanel;
	
	private Main app;
	private GridPane gridPane;
	final int numCols = 32;
    final int numRows = 16;
    private final static Logger logger = Logger.getLogger(Ant.class.getSimpleName());

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

	}

	public void setApp(Main main) {
		this.app = main;
		
	}
	
	private void launchAnt(int x, int y) {
		// creation de la fourmie dans ACO
		Ant ant = new Ant();
		
		// lancer une fourmi sur l'interface graphique
        logger.info("je suis une fourmi graphique.");
		ImageView iv = new ImageView(getClass().getResource("images.png").toExternalForm());
		this.gridPane.getChildren().get(0);
		Label antUI = new Label("     ");
		antUI.setStyle("-fx-border-color:black; -fx-background-color: black;");
		this.gridPane.add(antUI, x, y);

	}

	public void launchSimulation() {
		// par defaut le nid est au millieux de la carte
		this.launchAnt(numCols/2, numRows/2);
	}
	
	public void stopSimulation() {

		gridPane.getChildren().clear();
		initialize();
	}
	
	
	
}
