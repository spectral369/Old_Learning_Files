package gameTest2013;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Car {
	double x;
	int y ;
	Point location;
	float speed;
	Color color;
	Ellipse2D oval = null;
	public boolean end = false;
	
	public Car(Point location,float speed,Color color){
		this.location =location;
		this.speed=speed;
		this.color=color;
		
	}
	
	public void moveForward(Point point, int way){
		 x = location.getX();
		 if(x<point.x)
			location.setLocation(x+=speed, point.getY()+20);
			if(x>point.x-10 && way == 0 )
				end  = true;
			if(x>point.x-10 && way== 1)
				end  = false;
	}
	
	public void moveBackwards(Point point, int way){
		 x= location.getX();
		 if(x>point.x+25)
			location.setLocation(x-=speed, point.y+20);	
			
			if(x<point.x+20 && way == 0)
				end=false;
			if(x<point.x+30 && way == 1)
				end=true;
	}
	
	public void paint(Graphics2D g){
		g.setColor(color);
		g.fill(shape(g));
	}
	
	public Ellipse2D shape(Graphics2D g){
		return oval =  new Ellipse2D.Double(location.getX(),location.getY(),20,20);
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	

}
