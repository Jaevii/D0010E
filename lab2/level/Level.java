package lab2.level;

import java.util.Observable;
import java.util.ArrayList;

public class Level extends Observable {

	// Package access as to be called in LevelGUI
	ArrayList<Room> rooms;

	private Room spawn;
	Room currentRoom;
	private Boolean condition = true;

	public boolean place(Room r, int x, int y) {

		// Checks if room is outside the map
		if (x < 0 || y < 0) {
			return false;
		}

		if (rooms == null) {
			rooms = new ArrayList<Room>();
		}

		// Sets the coordinates for the placed room (MUST DO BEFORE CHECK OVERLAP)
		r.cx = x;
		r.cy = y;

		// Check for overlap
		for (Room room : rooms) {
			System.out.println("r: " + r.cx + ";" + r.cy + ";" + r.dx + ";" + r.dy);
			System.out.println("room: " + room.cx + ";" + room.cy + ";" + room.dx + ";" + room.dy);

			if (r.cx >= (room.cx - r.dx) && r.cx <= (room.cx + room.dx) && r.cy >= (room.cy - r.dy)
					&& r.cy <= (room.cy + room.dy)) {
				System.out.println("FALSE");
				return false;
			}

		}

		// Sets room as placed
		r.isPlaced();

		// Adds the placed room to ArrayList rooms as to be used in next check
		rooms.add(r);

		System.out.println("TRUE");
		return true;

	}

	public void firstLocation(Room r) {
		this.spawn = r;
		this.currentRoom = r;
	}

	public void moveN() {
		if (currentRoom.n != null) {
			currentRoom = currentRoom.n;
			setChanged();
			notifyObservers();
		}
	}

	public void moveS() {
		if (currentRoom.s != null) {
			currentRoom = currentRoom.s;
			setChanged();
			notifyObservers();
		}
	}

	public void moveE() {
		if (currentRoom.e != null) {
			currentRoom = currentRoom.e;
			setChanged();
			notifyObservers();
		}
	}

	public void moveW() {
		if (currentRoom.w != null) {
			currentRoom = currentRoom.w;
			setChanged();
			notifyObservers();
		}
	}

}
