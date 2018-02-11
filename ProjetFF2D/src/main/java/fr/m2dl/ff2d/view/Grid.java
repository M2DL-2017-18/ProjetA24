package fr.m2dl.ff2d.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class Grid implements IGrid{
	
	public final int gridCols = 32;
	public final int gridRows = 16;

	private GridPane gridPane;
	private AnchorPane gridPanel;
	
	public Grid(AnchorPane gp) {
		this.gridPanel = gp;
		initGridPane();
	}
	
	private void initGridPane() {
		
		this.gridPane = new GridPane();
		this.gridPane.setGridLinesVisible(true);
		
		for (int i = 0; i < gridCols; i++) {
			ColumnConstraints colConst = new ColumnConstraints(32);
			colConst.setPercentWidth(100.0 / gridCols);
			colConst.setMinWidth(0);
			gridPane.getColumnConstraints().add(colConst);
		}
		for (int i = 0; i < gridRows; i++) {
			RowConstraints rowConst = new RowConstraints(32);
			rowConst.setPercentHeight(100.0 / gridRows);
			rowConst.setMinHeight(0);
			gridPane.getRowConstraints().add(rowConst);

		}

		this.gridPanel.getChildren().add(gridPane);
		this.gridPane.setStyle("-fx-background-color: white;");
		this.gridPane.prefWidthProperty().bind(this.gridPanel.widthProperty());
		this.gridPane.prefHeightProperty().bind(this.gridPanel.heightProperty());
	
	}

	public GridPane getGrid() {
		return this.gridPane;
	}

	
	public void addGraphicElement(ImageView image, int x, int y) {
		this.gridPane.add(image, x, y);
	}
	
	public int getGridRows() {
		return this.gridRows;
	}
	
	public void clearGrid() {
		this.gridPane.getChildren().clear();
	}
	
	public int getGridCols() {
		return this.gridCols;
	}

	
	
	
	

}
