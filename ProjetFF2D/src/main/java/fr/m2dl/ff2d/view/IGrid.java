package fr.m2dl.ff2d.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public interface IGrid {
	
	
	public void addElement(ImageView image, int x, int y);
	public GridPane getGrid();
	public void addElement(Pane p, int x, int y);
	public void clearGrid();
	public int getGridRows();
	public int getGridCols();
	
}
