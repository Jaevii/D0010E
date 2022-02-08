
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

	public LevelGUI(Level level, String name) {

		this.lv = level;

		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Call
		get_size();

		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0, 0);
		frame.setVisible(true);

		lv.addObserver(this);
	}

	// repaint all draw components after a keypress
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

			// Draws the outline over the currentroom
			for (Room r : lv.rooms) {
				if (r == lv.currentRoom) {
					g.setColor(Color.WHITE);
					g.drawRect(r.cx, r.cy, r.dx, r.dy);
					g.setColor(Color.BLACK);
					g.drawOval(r.cx + (r.dx / 2) - 5, r.cy + (r.dy / 2) - 5, 10, 10);
					g.fillOval(r.cx + (r.dx / 2) - 5, r.cy + (r.dy / 2) - 5, 10, 10);
				}
			}

		}

		private class Listener implements KeyListener {

			public void keyPressed(KeyEvent arg0) {
				// controll if the input from arg0 matches with the vitrual keyboard number
				if (arg0.getKeyCode() == KeyEvent.VK_W) {
					// call move* for depending on input
					lv.move(KeyEvent.VK_W);
				}

				if (arg0.getKeyCode() == KeyEvent.VK_S) {
					lv.move(KeyEvent.VK_S);
				}

				if (arg0.getKeyCode() == KeyEvent.VK_A) {
					lv.move(KeyEvent.VK_A);
				}

				if (arg0.getKeyCode() == KeyEvent.VK_D) {
					lv.move(KeyEvent.VK_D);
				}
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent event) {
			}
		}
	}

	// Method for changing widowsize depending on rooms coordinates
	private void get_size() {
		int resX = 0;
		int resY = 0;

		for (Room r : lv.rooms) {
			if ((r.cx + r.dx) > resX) {
				resX = (r.cx + r.dx);
			}

			if ((r.cy + r.dy) > resY) {
				resY = (r.cy + r.dy);
			}
		}
		// Resolution depends on level size
		d = new Display(lv, resX + 10, resY + 10);
	}

	// Method which draws the connections
	private void drawConnection(Graphics g, Room r) {
		// check for connection and draws a line between
		g.setColor(Color.white);
		if (r.n != null) {
			g.drawLine(r.cx + (r.dx / 2), r.cy, r.n.cx + (r.n.dx / 2), r.n.cy + (r.n.dy / 2));
		}

		if (r.s != null) {
			g.drawLine(r.cx + (r.dx / 2), r.cy + r.dy, r.s.cx + (r.s.dx / 2), r.s.cy + (r.s.dy / 2));
		}

		if (r.e != null) {
			g.drawLine(r.cx + r.dx, r.cy + (r.dy / 2), r.e.cx + (r.e.dx / 2), r.e.cy + (r.e.dy / 2));
		}

		if (r.w != null) {
			g.drawLine(r.cx, r.cy + (r.dy / 2), r.w.cx + (r.w.dx / 2), r.w.cy + (r.w.dy / 2));
		}
	}
}
