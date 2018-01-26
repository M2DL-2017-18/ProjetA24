package controller;

import java.awt.Color;

import java.awt.TextField;
import java.util.logging.Logger;

import org.w3c.dom.Node;

import fr.m2dl.aco.domain.Ant;
import fr.m2dl.ff2d.Main;
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
		
		gridPane = new GridPane();
		gridPane.setGridLinesVisible(true);
		for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints(32);
            //colConst.setPercentWidth(100.0 / numCols);
            gridPane.getColumnConstraints().add(colConst);       
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints(32);
           // rowConst.setPercentHeight(100.0 / numRows);         
            gridPane.getRowConstraints().add(rowConst);   
        }
		
        gridPanel.getChildren().add(gridPane);
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
		Label antUI = new Label("F");
		antUI.setStyle("-fx-border-color:black; -fx-background-color: black;");
		this.gridPane.add(antUI, x, y);

	}

	public void launchSimulation() {
		this.launchAnt(numCols/2, numRows/2);
	}
	
	public void stopSimulation() {

		gridPane.getChildren().clear();
		initialize();
	}
	
	
	
}
