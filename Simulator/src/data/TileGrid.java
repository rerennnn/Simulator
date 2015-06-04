package data;

import static helpers.Artist.*;
import static helpers.Constants.*;
import bars.Sidebar;

public class TileGrid {

	public static Block[][] map;

	public TileGrid() {
		System.out.println("Tilegrid");
		map = new Block[mapWidth][mapHeight];
		System.out.println("map");
		System.out.println(map.length + " " + map[1].length);
		for (int i = 0; i < map.length; i++) {				 // Loop x array
			
			for (int j = map[i].length - 1; j >= 0; j--) {	 // Loop y array

				map[i][j] = new Block(i + toLoc(SIDEBAR), j, BlockType.Plot);		//create empty grid, aka full of plots
				
			}
		}
	}

	public static void rClick(int x, int y) {		//right clicked on block
		Block block = map[x][y];
		Sidebar.display(block);		//set the to be displayed information to this block's info
		
	}

	public static void click(int x, int y, BlockType type) {

		if (type == null) { 							// if any blocktype selected
			System.out.println("Select a Block ");

		} else if (map[x][y].myType == BlockType.Plot) { 		// if block is buildable on
			if (Player.money > type.cost) { 					// and has enough money
				switch (type) {

				case Road:
					map[x][y] = new Block(x + toLoc(SIDEBAR), y, type);			//just place road
					break;

				default:
					if (getRoad(x, y)) {
						map[x][y] = new Block(x + toLoc(SIDEBAR), y, type);		//first check if next to road for all buildings
					}
					break;
				}
				Player.money -= type.cost;										//get money for building
			}
			
		} else if (map[x][y].myType != BlockType.Plot && type == BlockType.Plot) {	//if block is not buildable, and destroy function is selected
			if (Player.money >= type.cost) {										//enough money
				map[x][y] = new Block(x + toLoc(SIDEBAR), y, type);					//erase block
				Player.money -= type.cost;											//pay
			}
		}

	}

	public static boolean getRoad(int x, int y) {									// check for road around block, to be updated to method similar to gethappiness in helpers.BlockActions.java
		boolean bool = false;
		if (x - 1 >= 0) {
			if (map[x - 1][y] != null) {
				if (map[x - 1][y].myType == BlockType.Road) {
					bool = true;
				}
			}
		}
		if (x + 1 < mapWidth) {
			if (map[x + 1][y] != null) {

				if (map[x + 1][y].myType == BlockType.Road) {
					bool = true;
				}
			}
		}
		if (y - 1 >= 0) {
			if (map[x][y - 1] != null) {
				if (map[x][y - 1].myType == BlockType.Road) {
					bool = true;
				}
			}
		}
		if (y + 1 < mapHeight) {
			if (map[x][y + 1] != null) {
				if (map[x][y + 1].myType == BlockType.Road) {
					bool = true;
				}
			}
		}
		return bool;
	}

	public void update() {															//cycle through blocks each time for update() and populate(), in between update Player.java values
		for (int i = 0; i < map.length; i++) { // Loop x array
			for (int j = map[i].length - 1; j >= 0; j--) { // Loop y array

				if (map[i][j] != null && map[i][j].myType != BlockType.Plot) {
					map[i][j].update();
				}

			}
		}
		Player.updatePop();
		for (int i = 0; i < map.length; i++) { // Loop x array
			for (int j = map[i].length - 1; j >= 0; j--) { // Loop y array

				if (map[i][j] != null && map[i][j].myType != BlockType.Plot) {
					map[i][j].populate();
				}

			}
		}
		Player.demandUpdate();
		Player.resetCounts();
	}

	public void draw() {															//loop through blocks, draw function
		// System.out.println("draw");
		for (int i = 0; i < map.length; i++) { // Loop x array
			for (int j = map[i].length - 1; j >= 0; j--) { // Loop y array
				map[i][j].draw();

			}
		}
	}
}
