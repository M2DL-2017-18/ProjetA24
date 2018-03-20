package fr.m2dl.ff2d.view;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphicElement {

	private Image image;
	private ImageView imageView;
	private FileInputStream is;
	int height;
	int width;
	
	public GraphicElement (final String imagePath, int w, int h) {
		createImage(imagePath);
		height = h;
		width = w;
		imageView = createImageView();
	}
	
	
	private void createImage(final String imagePath) {
		
		try {
			is = new FileInputStream(imagePath);
			image = new Image(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Creer l'image view de l'element
	 * @param image l'image à partir de laquelle on creer l'image view
	 * @param width la largeur de l'image
	 * @param height la hauteur de l'image
	 * @return l'image view de l'element
	 */
	private ImageView createImageView() {
		
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);	
		return imageView;	
	}
	
	public ImageView getImageView() {
		
		return imageView;	
	}


	
	
	
}
