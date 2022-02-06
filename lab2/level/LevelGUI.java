
package lab2.level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.security.auth.kerberos.KerberosKey;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LevelGUI implements Observer {

	private Level lv;
	private Display d;

	private int resX = 0;
	private int resY = 0;

	public LevelGUI(Level level, String name) {

		this.lv = level;

		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Changes widowsize depending on rooms coordinates
		for (Room r : lv.rooms) {
			if ((r.cx + r.dx) > resX) {
				resX = (r.cx + r.dx);
			}

			if ((r.cy + r.dy) > resY) {
				resY = (r.cy + r.dy);
			}
		}


		// Resolution depends on level size
		d = new Display(lv, resX, resY);

		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0, 0);
		frame.setVisible(true);

		lv.addObserver(this);
	}

	public void update(Observable arg0, Object arg1) {
		d.repaint();
	}

	private class Display extends JPanel {

		public Display(Level fp, int x, int y) {

			addKeyListener(new Listener());

			// Window background color
			setBackground(Color.BLACK);
			// Window size with padding
			setPreferredSize(new Dimension(x, y));
			setFocusable(true);
		}

		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);

			for (Room r : lv.rooms) {
				// Draws the connection first so it becomes hidden underneath the drawn rooms
				drawConnection(g, r);
			}

			// Draws the rooms from the Rooms array in level
			for (Room r : lv.rooms) {
				g.setColor(r.color);
				g.drawRect(r.cx, r.cy, r.dx, r.dy);
				g.fillRect(r.cx, r.cy, r.dx, r.dy);
			}

			for(Room r : lv.rooms) {
				if (r == lv.currentRoom) {
					g.setColor(Color.ORANGE);
					g.drawRect(r.cx, r.cy, r.dx, r.dy);
				}
			}

		}

		private class Listener implements KeyListener {

			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_W) {
					lv.moveN();
				}

				if (arg0.getKeyCode() == KeyEvent.VK_S) {
					lv.moveS();
				}

				if (arg0.getKeyCode() == KeyEvent.VK_A) {
					lv.moveE();
				}

				if (arg0.getKeyCode() == KeyEvent.VK_D) {
					lv.moveW();
				}
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
			g.drawLine(r.cx + (r.dx/2), r.cy, (r.n.cx + (r.n.dx/2)), (r.n.cy + (r.n.dy/2)));
		}

		if (r.s != null) {
			g.setColor(Color.white);
			g.drawLine(r.cx + (r.dx/2), (r.cy - r.dy), (r.s.cx + (r.s.dx/2)), (r.s.cy + (r.s.dy/2)));
		}

		if (r.e != null) {
			g.setColor(Color.white);
			g.drawLine(r.cx + r.dx, (r.cy + (r.dy/2)), (r.e.cx + (r.e.dx/2)), (r.e.cy + (r.e.dy/2)));
		}

		if (r.w != null) {
			g.setColor(Color.white);
			g.drawLine(r.cx, (r.cy + (r.dy/2)), (r.w.cx + (r.w.dx/2)), (r.w.cy + (r.w.dy/2)));
		}
	}
}
