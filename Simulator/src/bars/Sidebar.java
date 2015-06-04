package bars;


import static helpers.Artist.*;

import java.text.DecimalFormat;

import data.Block;
import data.Player;
import helpers.Clock;
import static helpers.Constants.*;
import static org.lwjgl.opengl.GL11.*;
public class Sidebar {

	int fpsCount = 0 ;		
	static Block toDisplay = null; 
	String fps = "0";
	DecimalFormat f = new DecimalFormat("##.00");	//format money to 2 decimals
	
	public Sidebar(){	
	}
	
	public static void display(Block block){		//set the block to be displayed
		toDisplay = block;
	}
	public void fpsCount(){							//update fps every 3 loops, else it will be unreadable
		fpsCount++;
		if(fpsCount<3){
			
			drawText(32, 32, fps);
			
		}else{
			fpsCount = 0;
			fps = String.valueOf(Clock.fps);
			drawText(32, 32, fps);
		}
		//drawLine(0, 60, SIDEBAR);
	}

	public void blockInfo(){					//display info about selected block
		if(toDisplay != null){
		drawText(8, 64, toDisplay.myType.type);
		drawText(8, 96, "Level: "+String.valueOf(toDisplay.level));
		drawText(8, 128, "Occupants: " + String.valueOf(toDisplay.population));
		drawText(8, 160, "Days vacant: " + String.valueOf(toDisplay.daysUnoccupied));
		drawText(8, 184, "Happiness: " + String.valueOf(toDisplay.happiness));
		}
		
		//drawLine(0, 160, SIDEBAR);
	}
	
	public void popInfo(){						//display general info about population
		drawText(16, 256, "Population: " + Player.population);
		if(Player.houses > 0){		//avoid devide by 0
			drawText(16, 288, "Happiness: " + Math.round(Player.happiness / Player.houses));
		}else { drawText(16, 288, "Happiness: "+ Player.happiness );}

		drawText(16, 384, "House/Work");
		drawText(16, 400, "demand: " + String.valueOf(Player.division));
		drawText(16, 432, "Houses: " + String.valueOf(Player.houses));
		
	}
	
	public void money(){			//display info money related
		drawText(8, (HEIGHT - TOOLBAR - 96), "$" + String.valueOf(f.format(Player.money)));
		drawText(8, (HEIGHT - TOOLBAR - 48), "Weekly");
		drawText(8, (HEIGHT - TOOLBAR - 32), "income: $" + String.valueOf(f.format(Player.weeklyDisplay)));
		drawText(8, (HEIGHT - TOOLBAR - 0), "Week #" + Clock.weeks);
	}
	public void draw(){
		glColor4f(0.1f, 0.6f, 0.6f, 0.1f);
		drawQuad(0, 0, SIDEBAR, BLOCKSIZE * mapHeight);
		//glColor3f(1f, 1f, 1f);
		fpsCount();
		blockInfo();
		popInfo();
		money();
	}
	
}
