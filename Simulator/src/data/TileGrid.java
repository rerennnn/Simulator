package data;

import static helpers.Artist.mapHeight;
import static helpers.Artist.SIDEBAR;
import static helpers.Artist.mapWidth;
import static helpers.Artist.*;

public class TileGrid {

	public static Block[][] map;
	public TileGrid(){
		System.out.println("Tilegrid");
		map = new Block[mapWidth][mapHeight];
		System.out.println("map");
		System.out.println(map.length + " " + map[1].length);
		for(int i = 0; i < map.length; i++){				// Loop x array
			//System.out.println("I");	
			for(int j = map[i].length - 1; j >= 0; j--){	// Loop y array
				System.out.println("loop");
				map[i][j] = new Block(i + toLoc(SIDEBAR), j, BlockType.Plot);
				System.out.println(i + " " + j + " " + BlockType.Plot.type);
			}
		}
	}
	
	public static void click(int x, int y){
		System.out.println("you clicked: " + x + " " + y + " " + toCoord(x) + " " + toCoord(y));
		map[x][y] = new Block(x + toLoc(SIDEBAR), y, BlockType.Road);
	}
	

	public void draw(){
		//System.out.println("draw");
		for(int i = 0; i < map.length; i++){				// Loop x array
			for(int j = map[i].length - 1; j >= 0; j--){	// Loop y array
				map[i][j].draw();
				
			}
		}
	}
}
