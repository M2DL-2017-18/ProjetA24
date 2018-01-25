package controller;

import fr.m2dl.ff2d.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Controller {
	
	@FXML
	private AnchorPane gridPanel;
	
	private Main app;
	GridPane gridPane;
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
	
	public void launchSimulation() {
		System.out.println("launch");
		
	}
	
	public void stopSimulation() {
		System.out.println("stop");
		
	}
	
	
	
}
