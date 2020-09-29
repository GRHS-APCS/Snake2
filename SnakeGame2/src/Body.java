import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Body {
	private int x;
	private int y;
	private Image sprite;
	private Body next;
	private Body prev;
	private boolean last;
	public Body() {
		x=0;
		y=0;
		loadImg("src/resources/dot.png");
		next=null;
		prev=null;
		last=true;
	}

	public Body(int xPos, int yPos) {
		x=xPos;
		y=yPos;
		loadImg("src/resources/dot.png");
		prev=null;
		next=null;
		last=true;
	}
	public Body(int xPos, int yPos, Body parent) {
		x=xPos;
		y=yPos;
		loadImg("src/resources/dot.png");
		prev=parent;
		parent.setLast(false);
		next=null;
		last=true;
	}
	
	private void setLast(boolean b) {
		// TODO Auto-generated method stub
		last=b;
	}

	protected void loadImg(String loc) {
		// TODO Auto-generated method stub
		ImageIcon dot = new ImageIcon(loc);
		sprite = dot.getImage();
	}
	
	protected void addBody() {
		if(!last)
			next.addBody();
		else
			next=new Body(x,y,this);
	}
	
	public void draw(Graphics g, Board theBoard) {
		if(!last)
			next.draw(g, theBoard);
		g.drawImage(sprite,x*10,y*10,theBoard);
	}
	
	public void act() {
		if(!last)
			next.act();
		x=prev.getX();
		y=prev.getY();
	}

	protected int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	protected int getX() {
		// TODO Auto-generated method stub
		return x;
	}
	public void setX(int newX) {
		x=newX;
	}
	public void setY(int newY) {
		y=newY;
	}
	public boolean getLast() {
		// TODO Auto-generated method stub
		return last;
	}
	protected Body getNext() {
		// TODO Auto-generated method stub
		return next;
	}

}
