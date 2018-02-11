package fr.m2dl.ff2d.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphicElement {

	private ImageView imageView;
	
	
	public GraphicElement (final String imagePath, int w, int h) {
		Image image = createImage(imagePath);
		imageView = createImageView(image, w, h);
	}
	
	
	private Image createImage(String imagePath) {
		
		FileInputStream is = null;
		Image image = null;
		try {
			is = new FileInputStream(imagePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = new Image(is);	
		
		return image;
		
	}
	
	private ImageView createImageView(Image image, int width, int height) {
		
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);	
		return imageView;	
	}
	
	public ImageView getImageView() {
		return this.imageView;
	}
	
	
}
