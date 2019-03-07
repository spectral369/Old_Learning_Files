package gameTest2013;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main extends JPanel implements MouseListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PREF_W = 800;
	private static final int PREF_H = 600;
	private static final Color BACKGROUND = Color.white;
	private Car car = null;
	private Car p2 = null;
	private Road road = null;
	private Graphics2D g2d = null;
	private Color color = Color.black;
	private Color p2Color = Color.red;
	private Point point = null;

	private boolean changeLane = false;
	private final static int DELAY = 1000 / 30;
	private int laps = 0;
	private int sw = 0;

	private boolean p2ChangeLane;
	private int p2sw = 0;
	private int p2Laps = 0;

	public Main() {
		setBorder(BorderFactory.createTitledBorder("800 x 600 Panel"));
		setBackground(BACKGROUND);
		point = new Point(40, 40);
		car = new Car(point, 1, color);
		p2 = new Car(new Point(40, 40), 1, p2Color);
		car.speed = 8;
		p2.speed = 8;
		road = new Road(point, getPreferredSize());
		addMouseListener(this);
		addKeyListener(this);

		requestFocusInWindow(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		road.paint(g2d);
		car.setColor(color);
		p2.setColor(p2Color);

		car.paint(g2d);
		p2.paint(g2d);

		if (car != null) {
			// car.paint(g2d);
			if (car.end) {
				// contor++;

				changeLane = true;
			}

		}
		if (p2 != null) {
			// p2.paint(g2d);
			if (p2.end) {

				p2ChangeLane = true;
			}

		}

		print_score(g2d);
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		if (isPreferredSizeSet()) {
			return super.getPreferredSize();
		}
		return new Dimension(PREF_W, PREF_H);
	}

	protected void changeColor(Color color) {
		if (this.color != color)
			this.color = color;
		else
			this.color = Color.black;
	}

	protected void setRandomColor1() {
		Random rand = new Random();
		int r = rand.nextInt(256);
		int g = rand.nextInt(256);
		int b = rand.nextInt(256);
		this.color = new Color(r, g, b);
	}

	protected void setRandomColor2() {
		Random rand = new Random();
		int r = rand.nextInt(256);
		int g = rand.nextInt(256);
		int b = rand.nextInt(256);
		this.p2Color = new Color(r, g, b);
	}

	protected int getRandomSpeedBadWay(int low, int high, float bias) {

		Random rand = new Random();
		float r = rand.nextFloat(); // random between 0 and 1
		r = (float) Math.pow(r, bias);
		return (int) Math.abs(low + (high - low) * r);

	}

	public void print_score(Graphics2D g) {
		Graphics2D g2 = g;
		g2.setColor(Color.BLACK);
		g2.drawString("P2Lap", getWidth() / 2 - 120, getHeight() / 2);
		g2.drawString(String.valueOf(p2Laps), getWidth() / 2 - 80, getHeight() / 2);
		g2.drawString("P1Lap ", getWidth() / 2 - 60, getHeight() / 2);
		g2.drawString(String.valueOf(laps), getWidth() / 2 - 10, getHeight() / 2);
		g2.drawString("speed ", getWidth() / 2 + 10, getHeight() / 2);
		g2.drawString(String.valueOf(car.speed), getWidth() / 2 + 50, getHeight() / 2);

	}

	/*
	 * @description for automation
	 */

	public void move_car_top(Car car1) {

		car1.moveForward(road.A_end, 0);

	}

	/*
	 * @description for automation
	 */

	public void move_car_Bot(Car car1) {

		car1.moveBackwards(road.B_start, 1);

	}

	public void move_car_forward1(Car car) {
		if (sw == 0) {
			car.moveForward(road.A_end, 0);
		}
		if (sw == 1) {
			car.moveBackwards(road.B_start, 1);

		}
	}

	public void move_car_forward2(Car car) {
		if (p2sw == 0) {
			car.moveForward(road.A_end, 0);
		}
		if (p2sw == 1) {
			car.moveBackwards(road.B_start, 1);

		}
	}

	public void move_car_backwards1(Car car) {
		if (sw == 0) {
			car.moveBackwards(road.A_start, 0);
		}
		if (sw == 1) {
			car.moveForward(road.B_end, 1);

		}
	}

	public void move_car_backwards2(Car car) {
		if (p2sw == 0) {
			car.moveBackwards(road.A_start, 0);
		}
		if (p2sw == 1) {
			car.moveForward(road.B_end, 1);

		}
	}

	private static void createAndShowGui() {
		Main m = new Main();
		JFrame frame = new JFrame("Game Test 2010");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridBagLayout());
		frame.getContentPane().add(m);
		frame.pack();
		frame.addKeyListener(m);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		Timer timer = new Timer(DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if (m.changeLane == true && m.sw == 0) {

					m.car = null;

					Point spawn = m.road.B_end;
					spawn.setLocation(spawn.x + 20, spawn.y + 20);
					m.setRandomColor1();
					m.car = new Car(spawn, m.getRandomSpeedBadWay(1, 10, 0.7f), m.color);
					m.changeLane = false;
					m.sw = 1;
				}

				/*
				 * if (m.sw == 0) {
				 * 
				 * m.move_car_top(m.car); } else { m.move_car_Bot(m.car); }
				 */

				if (m.changeLane == true && m.sw == 1) {
					m.car = null;
					m.setRandomColor1();
					m.car = new Car(new Point(40, 40), m.getRandomSpeedBadWay(1, 10, 0.7f), m.color);
					m.changeLane = false;

					m.laps++;
					m.sw = 0;
				}
				// p2

				if (m.p2ChangeLane == true && m.p2sw == 0) {

					m.p2 = null;

					Point spawn = m.road.B_end;
					spawn.setLocation(spawn.x + 20, spawn.y + 20);
					m.setRandomColor2();
					m.p2 = new Car(spawn, m.getRandomSpeedBadWay(1, 10, 0.7f), m.p2Color);
					m.p2ChangeLane = false;
					m.p2sw = 1;
				}

				if (m.p2sw == 0) {

					m.move_car_top(m.p2);
				} else {
					m.move_car_Bot(m.p2);
				}

				if (m.p2ChangeLane == true && m.p2sw == 1) {
					m.p2 = null;
					m.setRandomColor2();
					m.p2 = new Car(new Point(40, 40), m.getRandomSpeedBadWay(1, 10, 0.7f), m.p2Color);
					m.p2ChangeLane = false;

					m.p2Laps++;
					m.p2sw = 0;
				}
				if (m.laps == 2) {

					JOptionPane.showMessageDialog(null, "P1 is the Winner");

					m.laps = 0;

					m.p2Laps = 0;

					m.car = new Car(new Point(40, 40), 8, m.color);
					m.p2 = new Car(new Point(40, 40), 8, m.p2Color);

				} else if (m.p2Laps == 2) {

					JOptionPane.showMessageDialog(null, "P2 is the Winner");

					m.laps = 0;

					m.p2Laps = 0;

					m.p2 = new Car(new Point(40, 40), 8, m.p2Color);

					m.car = new Car(new Point(40, 40), 8, m.color);
					m.repaint();

				}

				m.repaint();
			}
		});
		timer.setRepeats(true);
		timer.start();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGui();
			}
		});

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		if ((arg0.getButton() == 1) && car.oval.contains(arg0.getX(), arg0.getY())) {
			car.color = Color.GREEN;
			System.out.println("in oval");
			changeColor(Color.green);

		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// System.out.println("mouse Pressed!");

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (sw == 0) {
				move_car_forward1(car);
			} else {
				move_car_backwards1(car);
			}

		} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if (sw == 0) {
				move_car_backwards1(car);
			} else {
				move_car_forward1(car);
			}
		}
		repaint();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("Key Released" +arg0.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("Key Typed" +arg0.getKeyCode());
	}
}
