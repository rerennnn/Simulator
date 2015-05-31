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

	public Block(int locX, int locY, BlockType type) {
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
		// System.out.println("Draw block");
		drawQuadTex(texture, xCoord, yCoord, width, height);
		if(crossed){
			drawQuadTex(LoadTex("industrialCross"), xCoord, yCoord, width, height);
		}
	}
	public void update(){
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
	
	public void upgradeHouse(){
		level++;
		capacity = levelCaps[level];
		texture = LoadTex(texString + String.valueOf(level));
		
	}
	
	public void upgradeShop(){
		level++;
		texture = LoadTex(texString + String.valueOf(level));
	}


	public boolean checkUpgradeHouse(){
		if(happiness >= houseHappiness[level] && level < 5){
			return true;
		}else{
			return false;
		}
	}
	public boolean checkUpgradeShop(){
		if(happiness >= shopUsage[level] && level < 3){
			return true;
		}else{
			return false;
		}
	}
	
	public void actShop(){
		Player.shopCount += capacity;
		Player.shopCount++;
		if(daysUnoccupied>10){
			TileGrid.map[x][y] = new Block(x + toLoc(SIDEBAR), y, BlockType.Plot); 
		}
	}
	public void actIndustry(){
		Player.indusCount++;
		if(daysUnoccupied>10){
			TileGrid.map[x][y] = new Block(x + toLoc(SIDEBAR), y, BlockType.Plot); 
		}
	}
	
	public void actHouse(){

		if(!getRoad(x, y)){
			TileGrid.map[x][y] = new Block(x + toLoc(SIDEBAR), y, BlockType.Plot); 
		}
		happiness = getHappiness(x, y);
		Player.headCount += capacity;
		Player.houseCount++;
		Player.happyCount += happiness;
		if(checkUpgradeHouse()){
			upgradeHouse();
		}
		
	}
	public void populate(){
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
