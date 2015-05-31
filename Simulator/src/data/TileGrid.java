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
		for (int i = 0; i < map.length; i++) { // Loop x array
			// System.out.println("I");
			for (int j = map[i].length - 1; j >= 0; j--) { // Loop y array

				map[i][j] = new Block(i + toLoc(SIDEBAR), j, BlockType.Plot);
				// System.out.println(i + " " + j + " " + BlockType.Plot.type);
			}
		}
	}

	public static void rClick(int x, int y) {
		Block block = map[x][y];
		Sidebar.display(block);
		// System.out.println("right clicked & loaded");
	}

	public static void click(int x, int y, BlockType type) {

		if (type == null) { // if blocktype selected
			System.out.println("Select a Block ");

		} else if (map[x][y].myType == BlockType.Plot) { // if block is
			if (Player.money > type.cost) { // buildable
				switch (type) {

				case Road:
					map[x][y] = new Block(x + toLoc(SIDEBAR), y, type);
					break;

				default:
					if (getRoad(x, y)) {
						map[x][y] = new Block(x + toLoc(SIDEBAR), y, type);
					}
					break;
				}
				Player.money -= type.cost;
			}
		} else if (map[x][y].myType != BlockType.Plot && type == BlockType.Plot) {
			if (Player.money >= type.cost) {
				map[x][y] = new Block(x + toLoc(SIDEBAR), y, type);
				Player.money -= type.cost;
			}
		}

	}

	public static boolean getRoad(int x, int y) {
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

	public void update() {
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

	public void draw() {
		// System.out.println("draw");
		for (int i = 0; i < map.length; i++) { // Loop x array
			for (int j = map[i].length - 1; j >= 0; j--) { // Loop y array
				map[i][j].draw();

			}
		}
	}
}
