
public class Snake extends Body {
	private int dir;
	public Snake(int xPos, int yPos) {
		super(xPos, yPos);
		dir=0;
		loadImg("src/resources/head.png");
		addBody();
		addBody();
		addBody();
	}

	public void act(boolean leftDirection, boolean rightDirection, boolean upDirection, boolean downDirection) {
		if(!getLast())
			getNext().act();
		if(leftDirection)
			setX(getX()-1);
		else if(rightDirection)
			setX(getX()+1);
		else if(upDirection)
			setY(getY()-1);
		else if(downDirection){
			setY(getY()+1);
		}
	}



	/*public void act(Apple goal) {
		int diffX=goal.getX()-getX();
		int diffY=goal.getY()-getY();
		if(!getLast())
			getNext().act();
		if(diffX>0)
			setX(getX()+1);
		else if(diffX<0)
			setX(getX()-1);
		else if(diffY>0)
			setY(getY()+1);
		else
			setY(getY()-1);

	}*/
	public void act(Apple goal) {
		int diffX=goal.getX()-getX();
		int diffY=goal.getY()-getY();
		int tmpX=getX();
		int tmpY=getY();
		int breaker=0;
		if(!getLast())
			getNext().act();
		if(diffX>0) 
			tmpX=getX()+1;
		else if(diffX<0)
			tmpX=getX()-1;
		else if(diffY>0)
			tmpY=getY()+1;
		else
			tmpY=getY()-1;
		Body tmp=new Body(tmpX,tmpY);
		while(checkBody(tmp)) {
			diffX=tmp.getX()-getX();
			diffY=tmp.getY()-getY();
			if(diffX>0) {
				tmpY=getY()-1;
				tmpX=getX();
			}
			else if(diffX<0) {
				tmpY=getY()+1;
				tmpX=getX();
			}
			else if(diffY>0) {
				tmpY=getY();
				tmpX=getX()+1;
			}
			else {
				tmpY=getY();
				tmpX=getX()-1;
			}
			tmp=new Body(tmpX,tmpY);
			if(breaker>5) {
				break;
			}
			breaker++;
		}
		setX(tmp.getX());
		setY(tmp.getY());

	}

	public boolean collide(Body tmp1, Body tmp2) {
		if(tmp1.getX()==tmp2.getX()&&tmp1.getY()==tmp2.getY())
			return true;
		return false;

	}
	public boolean collide(Body tmp) {
		return collide(this,tmp);
	}

	public boolean stopHittingYourself() {
		return checkBody(this);
	}
	public boolean checkBody(Body bod) {
		// TODO Auto-generated method stub
		Body check=getNext();
		boolean tmp=check.getLast();
		do {
			if(collide(bod,check))
				return true;
			check=check.getNext();
			tmp=check.getLast();
		} while(!tmp);
		return false;
	}
}
