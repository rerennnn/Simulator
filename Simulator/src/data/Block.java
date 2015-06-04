package data;

import static helpers.Artist.*;
import static data.TileGrid.*;
import static helpers.BlockActions.*;

import org.newdawn.slick.opengl.Texture;

import static helpers.Constants.*;
import static helpers.ConstantLists.*;
public class Block {

	private Texture texture;
	
	private int xCoord, yCoord, width, height, x, y;
	public int level = 1, population = 0, capacity = levelCaps[0], happiness = 0, daysUnoccupied = 0;
	public BlockType myType;
	private boolean crossed = false;
	public double weeklyCost, weeklyIncome;
	private String texString;

	public Block(int locX, int locY, BlockType type) {					//constructor, initialize block
		this.width = BLOCKSIZE;
		this.height = BLOCKSIZE;
		this.xCoord = toCoord(locX);					
		this.yCoord = toCoord(locY);
		this.x = locX - toLoc(SIDEBAR);
		this.y = locY;
		this.myType = type;
		this.weeklyCost = type.weekly;
		texString = myType.texName;
		texture = LoadTex(myType.texName + String.valueOf(level));
	}

	public void draw() {

		drawQuadTex(texture, xCoord, yCoord, width, height);
		if(crossed){			// if unoccupied, draw red cross on top
			drawQuadTex(LoadTex("industrialCross"), xCoord, yCoord, width, height);
		}
	}
	public void update(){				// updates certain blocks should do, also weekly amounts money and working population capacity
			Player.weekly -= myType.weekly;
		switch(myType){
		case House:
			Player.weekly += houseTax[level];
			actHouse();
			break;
		case Shop:
			Player.weekly += shopTax[level];
			actShop();
			Player.totCap += capacity;
			break;
		case Industry:
			Player.weekly += industryTax[level];
			actIndustry();
			Player.totCap += capacity;
			break;
		default:
			break;
		}
		
	}
	
	public void upgradeHouse(){						//upgrading a house
		level++;
		capacity = levelCaps[level];
		texture = LoadTex(texString + String.valueOf(level));
		
	}
	
	public void upgradeShop(){						//upgrading a shop 
		level++;
		texture = LoadTex(texString + String.valueOf(level));
	}


	public boolean checkUpgradeHouse(){				//check wether should updgrade
		if(happiness >= houseHappiness[level] && level < 5){
			return true;
		}else{
			return false;
		}
	}
	public boolean checkUpgradeShop(){				//check wether should updgrade
		if(happiness >= shopUsage[level] && level < 3){
			return true;
		}else{
			return false;
		}
	}
	
	public void actShop(){							//actions for this specific block
		Player.shopCount += capacity;
		Player.shopCount++;
		if(daysUnoccupied>10){				//if empty for too long, disappear
			TileGrid.map[x][y] = new Block(x + toLoc(SIDEBAR), y, BlockType.Plot); 
		}
	}
	public void actIndustry(){						//actions for this specific block
		Player.indusCount++;	
		if(daysUnoccupied>10){				//if empty for too long, disappear
			TileGrid.map[x][y] = new Block(x + toLoc(SIDEBAR), y, BlockType.Plot); 
		}
	}
	
	public void actHouse(){							//actions for this specific block

		if(!getRoad(x, y)){
			TileGrid.map[x][y] = new Block(x + toLoc(SIDEBAR), y, BlockType.Plot); //	if not near road disapear
		}
		happiness = getHappiness(x, y);				//happiness 5x5 around this block
		//increase counts
		Player.headCount += capacity;
		Player.houseCount++;
		Player.happyCount += happiness;
		if(checkUpgradeHouse()){
			upgradeHouse();
		}
		
	}
	public void populate(){									//do some calculations with population and working amounts, for unoccupied days
		if(myType == BlockType.Industry || myType == BlockType.Shop){
			int wpop = Player.workPopulation;
			int work = Player.working;
			if(work<wpop){
				if((wpop-work)>capacity){
					Player.working += capacity;
					population = capacity;
				}else{
					Player.working += (wpop - work);
					population = (wpop - work);
				}
				daysUnoccupied = 0;
				crossed = false;
			}else{
				daysUnoccupied++;
				if(daysUnoccupied == 1){
					Player.popDemand =+ 1;
				}
				if(daysUnoccupied>5){
					crossed = true;
				}
				
			}
		}

	}
}
