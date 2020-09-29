import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Apple extends Body{
	public Apple(int xRange, int yRange) {
		loadImg("src/resources/apple.png");
		locateApple(xRange,yRange);
	}
	public void locateApple(int xRange,int yRange) {
		setX((int) (Math.random() * xRange/10));
		setY((int) (Math.random() * yRange/10));
	}
}
