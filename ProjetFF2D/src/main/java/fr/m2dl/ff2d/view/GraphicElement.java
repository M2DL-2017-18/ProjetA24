package fr.m2dl.ff2d.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphicElement {

	private Image imageNest;
	private Image imageObstacle;
	private Image imageAnt;
	private Image imageFood;
	
	private final String antImagePath = "src/main/java/fr/m2dl/ff2d/view/ant.png";
	private final String nestImagePath = "src/main/java/fr/m2dl/ff2d/view/nid.png";
	private final String foodImagePath = "src/main/java/fr/m2dl/ff2d/view/food.jpeg";
	private final String obstacleImagePath = "src/main/java/fr/m2dl/ff2d/view/obstacle.jpg";
	
	
	public GraphicElement () {
		initElementImages();
	}
	
	private void initElementImages() {
		imageNest = createImage(nestImagePath);
		imageFood = createImage(foodImagePath);
		imageObstacle = createImage(obstacleImagePath);
		imageAnt = createImage(antImagePath);
	}
	
	private ImageView createImageView(Image image, int width, int height) {
		
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);	
		return imageView;	
	}
	
	public ImageView createAntImageView() {
		
		return createImageView(imageAnt, 40, 40);
	}
	
	public ImageView createObstacleImageView() {
		
		return createImageView(imageObstacle, 40, 40);
	}
	
	public ImageView createFoodImageView() {
		
		return createImageView(imageFood, 40, 40);
	}
	
	public ImageView createNestImageView() {
		
		return createImageView(imageNest, 40, 40);
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

	public Image getImageNest() {
		return imageNest;
	}

	public Image getImageObstacle() {
		return imageObstacle;
	}

	public Image getImageAnt() {
		return imageAnt;
	}

	public Image getImageFood() {
		return imageFood;
	}
	
	
	

	
	
}
