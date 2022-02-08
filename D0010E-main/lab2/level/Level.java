package lab2.level;

import java.util.Observable;
import java.util.ArrayList;

public class Level extends Observable {

	// Package access as to be called in LevelGUI
	ArrayList<Room> rooms;

	Room currentRoom;

	public boolean place(Room r, int x, int y) {

		// Checks if room is outside the map
		if (x < 0 || y < 0) {
			return false;
		}
		// Checks if the array rooms exist
		if (rooms == null) {
			rooms = new ArrayList<Room>();
		}

		// Sets the coordinates for the placed room (MUST DO BEFORE CHECK OVERLAP)
		r.cx = x;
		r.cy = y;

		// Check for overlap
		for (Room room : rooms) {
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

		return true;
	}

	// Sets the spawnpoint
	public void firstLocation(Room r) {
		this.currentRoom = r;
	}

	// Change the current room depending on keypresses
	void move(int key) {
		switch (key) {
			case 87:
				if (currentRoom.n != null) {
					currentRoom = currentRoom.n;
					observer_change();
				}
				break;
			case 83:
				if (currentRoom.s != null) {
					currentRoom = currentRoom.s;
					observer_change();
				}
				break;
			case 65:
				if (currentRoom.w != null) {
					currentRoom = currentRoom.w;
					observer_change();
				}
				break;
			case 68:
				if (currentRoom.e != null) {
					currentRoom = currentRoom.e;
					observer_change();
				}
				break;
		}
	}

	void observer_change() {
		setChanged();
		notifyObservers();
	}

}
