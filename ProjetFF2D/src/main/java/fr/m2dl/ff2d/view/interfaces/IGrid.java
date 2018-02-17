package fr.m2dl.ff2d.view.interfaces;

import javafx.scene.image.ImageView;

public interface IGrid {
	
	/**
	 * Ajoute un element a afficher dans la grille
	 * @param image l'image de l'element a ajouter dans la grille
	 * @param x la colonne a laquelle placer l'element
	 * @param y la ligne a laquelle placer l'element
	 */
	public void addGraphicElement(ImageView image, int x, int y);
	/**
	 * Supprime les elements de la grille
	 */
	public void clearGrid();
	/**
	 * Permet d'avoir le nombre de ligne de la grille
	 * @return le nombre de ligne
	 */
	public int getGridRows();
	
	/**
	 * Permet d'avoir le nombre de colonne de la grille
	 * @return le nombre de colonne
	 */
	public int getGridCols();
	
	/**
	 * Supprime un element de la grille
	 * @param row la ligne ou se trouve l'element
	 * @param col la colonne ou se trouve l'element
	 */
	public void removeCell(int row, int col);
	
}
