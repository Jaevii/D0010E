
package lab2.level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LevelGUI implements Observer {

	private Level lv;
	private Display d;

	public LevelGUI(Level level, String name) {

		this.lv = level;

		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TODO: You should change 200 to a value
		// depending on the size of the level
		d = new Display(lv, 1000, 800);

		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0, 0);
		frame.setVisible(true);
	}

	public void update(Observable arg0, Object arg1) {
		d.repaint();
	}

	private class Display extends JPanel {

		public Display(Level fp, int x, int y) {

			addKeyListener(new Listener());

			// Window background color
			setBackground(Color.BLACK);
			setPreferredSize(new Dimension(x + 20, y + 20));
			setFocusable(true);
		}

		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);

			// Draws the rooms from the Rooms array in level
			for (Room r : lv.rooms) {
				g.setColor(r.color);
				g.drawRect(r.cx, r.cy, r.dx, r.dy);
				g.fillRect(r.cx, r.cy, r.dx, r.dy);

				drawConnection(g, r);
			}

		}

		private class Listener implements KeyListener {

			public void keyPressed(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent event) {
			}
		}

	}

	void drawConnection(Graphics g, Room r){
		if (r.n != null) {
			g.setColor(Color.white);
			g.drawLine(r.cx + (r.dx/2), r.cy, r.n.cx, r.n.cy);
		}

		if (r.s != null) {
			
		}

		if (r.e != null) {
			
		}

		if (r.w != null) {
			
		}
	}
}
