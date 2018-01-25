package controller;

import java.awt.Color;
import java.awt.TextField;

import org.w3c.dom.Node;

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
	
	private void lancerFourmi(int x, int y) {
		// lancer une fourmi sur l'interface graphique
		//Image image = new Image("/ProjetFF2D/src/main/java/controller/images.png");
		ImageView iv = new ImageView(getClass().getResource("images.png").toExternalForm());
		this.gridPane.getChildren().get(0);
		Label ant = new Label("         ");
		ant.setStyle("-fx-border-color:black; -fx-background-color: black;-fx-foreground-color: black;");
		this.gridPane.add(ant, x, y);
	}

	public void launchSimulation() {
		System.out.println("launch");
		this.lancerFourmi(numCols/2, numRows/2);
	}
	
	public void stopSimulation() {
		System.out.println("stop");
		
	}
	
	
	
}
