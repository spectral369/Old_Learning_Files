package gameTest2013;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Road {
	Point location;
	Point A_start;
	Point A_end;
	Point B_start;
	Point B_end;

	Dimension size;

	int x, y;
	BufferedImage road = null;

	private void getRoad() {
		try {
			road = ImageIO.read(new File("road.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Road(Point location, Dimension size) {
		this.location = location;
		this.size = size;
		getRoad();
	}

	public void paint(Graphics2D g) {
		startPoint1(g);
		endPoint1(g);
		startPoint2(g);
		endPoint2(g);

		pathA_B(g);
		pathC_D(g);

	}

	private void pathA_B(Graphics2D g) {
		// TODO Auto-generated method stub
		Float rec1 = new Rectangle2D.Float((int) (size.width - (size.getWidth() - 20)),
				(int) (size.width - (size.getWidth() - 40)), 150, 150);
		TexturePaint paint = new TexturePaint(road, rec1);
		((Graphics2D) g).setPaint(paint);
		((Graphics2D) g).setStroke(new BasicStroke(40));

		g.drawLine(A_start.x + 68, A_start.y + 29, A_end.x - 4, A_end.y + 29);
	}

	private void pathC_D(Graphics2D g) {
		// TODO Auto-generated method stub
		Float rec1 = new Rectangle2D.Float((int) (size.width - (size.getWidth() - 580)),
				(int) (size.width - (size.getWidth() - 40)), 150, 150);
		TexturePaint paint = new TexturePaint(road, rec1);
		((Graphics2D) g).setPaint(paint);
		((Graphics2D) g).setStroke(new BasicStroke(40));

		g.drawLine(B_start.x + 68, B_start.y + 29, B_end.x - 4, B_end.y + 29);
	}

	private void startPoint1(Graphics2D g) {// startpoint top
		// TODO Auto-generated method stub
		g.drawOval((int) (size.width - (size.getWidth()) + 20), (int) (size.height - (size.getHeight()) + 20), 60, 60);

		A_start = new Point((int) (size.width - (size.getWidth()) + 20), (int) (size.height - (size.getHeight()) + 20));
	}

	private void endPoint1(Graphics2D g) {// endpoint top
		// TODO Auto-generated method stub

		g.drawOval((int) (size.width - 70), (int) (size.height - (size.getHeight()) + 20), 60, 60);

		A_end = new Point((int) (size.width - 70), (int) (size.height - (size.getHeight()) + 20));
	}

	private void startPoint2(Graphics2D g) {// startpoint bot
		// TODO Auto-generated method stub
		g.drawOval((int) (size.width - (size.getWidth()) + 20), (int) (size.height) - 100, 60, 60);

		B_start = new Point((int) (size.width - (size.getWidth()) + 20), (int) (size.height) - 100);
	}

	private void endPoint2(Graphics2D g) { // endpoint bot
		// TODO Auto-generated method stub
		g.drawOval((int) (size.width - 70), (int) (size.height - 100), 60, 60);

		B_end = new Point((int) (size.width - 70), (int) (size.height - 100));//100
	}

}
