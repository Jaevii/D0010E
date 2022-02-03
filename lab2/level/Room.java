
package lab2.level;

import java.awt.Color;

public class Room {

	// Room dimensions
	int dx = 0;
	int dy = 0;

	// Floor color
	Color color;

	// Room coordinates
	int cx = 0;
	int cy = 0;

	// Room connections (north, south, west, east)
	Room n = null;
	Room s = null;
	Room w = null;
	Room e = null;

	// Bool for if the room is placed or not, default is false
	private Boolean placed = false;

	// Constructor
	public Room(int dx, int dy, Color color) {

		this.dx = dx;
		this.dy = dy;
		this.color = color;

		// System.out.println("dx: " + this.dx + ", dy: " + this.dy + ", color: " +
		// this.color);

	}

	// Connects north wall of room to another given room
	public void connectNorthTo(Room r) {
		// Checks that it does not connect to itself
		if (r == this) {
			return;
		}
		// Checks that both rooms are placed
		if (!r.placed || !this.placed) {
			return;
		}
		this.n = r;
	}

	// Connects east wall of room to another given room
	public void connectEastTo(Room r) {
		// Checks that it does not connect to itself
		if (r == this) {
			return;
		}
		// Checks that both rooms are placed
		if (!r.placed || !this.placed) {
			return;
		}
		this.e = r;
	}

	// Connects south wall of room to another given room
	public void connectSouthTo(Room r) {
		// Checks that it does not connect to itself
		if (r == this) {
			return;
		}
		// Checks that both rooms are placed
		if (!r.placed || !this.placed) {
			return;
		}
		this.s = r;
	}

	// Connects west wall of room to another given room
	public void connectWestTo(Room r) {
		// Checks that it does not connect to itself
		if (r == this) {
			return;
		}
		// Checks that both rooms are placed
		if (!r.placed || !this.placed) {
			return;
		}
		this.w = r;
	}

	// Changes placed to true when room is placed
	public void isPlaced() {
		this.placed = true;
	}
}
