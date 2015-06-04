package data;

public class Player {

	/*
	 * Player is a class for keeping all variables relevant to calculating sidebar and population information
	 */
	public static int population, happiness, houseDemand, workDemand,
			workDemandCount, shopDemand, headCount, happyCount, houseCount,
			houses, shopCap, indusCap, shopCount, indusCount, workPopulation, popDemand, working, division, totCap;
	
	public static double money = 5000, weekly, weeklyDisplay;// money values
	

	public static void demandUpdate(){
		division = (workPopulation - totCap);		//amount of over/under population/working
		//System.out.println(division);

	}
	public static void updatePop() {

		workPopulation = headCount;					//set values to variables that arent accessed from outside
		population = headCount;						//now we can do calculations with these/can be accessed by sidebar
		happiness = happyCount;
		houses = houseCount;
		if(division > 4){
			happiness -= (division/houses) * 2;
		}
		///System.out.println(happiness);
	}
	
	public static void resetCounts(){				//reset all counting values to 0, update money
		money += weekly/7;
		weeklyDisplay = weekly;
		weekly = 0;
		shopCount = 0;
		houseCount = 0;
		happyCount = 0;
		headCount = 0;
		working = 0;
		totCap = 0;
	}
}
