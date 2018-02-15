package fr.m2dl.ff2d.view.interfaces;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public interface IGrid {
	
	public void addGraphicElement(ImageView image, int x, int y);
	public void clearGrid();
	public int getGridRows();
	public int getGridCols();
	
}
