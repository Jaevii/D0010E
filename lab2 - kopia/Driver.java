package lab2;

import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

public class Driver {

	public void run() {

		// Creates rooms
		Room room1 = new Room(10, 10, Color.red);
		Room room2 = new Room(20, 20, Color.green);
		Room room3 = new Room(30, 30, Color.blue);

		// Creates a new level called "map"
		Level map = new Level();

		// Checks for overlaps and if none found places the rooms at given coordinates,
		// otherwise the room is not placed
		map.place(room1, 0, 0);
		map.place(room2, 200, 200);
		map.place(room3, 210, 210);

		// Sets spawn location
		map.firstLocation(room1);

		room1.connectNorthTo(room2);
		room2.connectSouthTo(room3);

		LevelGUI gui = new LevelGUI(map, "yo mama");

	}

}