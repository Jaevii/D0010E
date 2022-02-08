package lab2;

import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

public class Driver {

	public void run() {

		// Creates rooms
		Room room1 = new Room(30, 30, Color.red);
		Room room2 = new Room(30, 30, Color.green);
		Room room3 = new Room(30, 30, Color.yellow);
		Room room4 = new Room(30, 30, Color.pink);
		Room room5 = new Room(30, 30, Color.blue);

		// Creates a new level called "map"
		Level map = new Level();

		// Checks for overlaps and if none found places the rooms at given coordinates,
		// otherwise the room is not placed
		map.place(room1, 500, 400);
		map.place(room2, 500, 200);
		map.place(room3, 500, 600);
		map.place(room4, 250, 400);
		map.place(room5, 750, 400);

		// Sets spawn location
		map.firstLocation(room1);

		// Sets connection between rooms
		room1.connectNorthTo(room2);
		room1.connectSouthTo(room3);
		room1.connectWestTo(room4);
		room1.connectEastTo(room5);

		room2.connectSouthTo(room1);
		room3.connectNorthTo(room1);
		room4.connectEastTo(room1);
		room5.connectWestTo(room1);

		// Create the gui for the level
		LevelGUI gui = new LevelGUI(map, "yo mama");

	}

}