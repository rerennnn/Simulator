package data;

public class Player {

	public static int population, happiness, houseDemand, workDemand,
			workDemandCount, shopDemand, headCount, happyCount, houseCount,
			houses, shopCap, indusCap, shopCount, indusCount, workPopulation, popDemand, working, division, totCap;
	public static double money = 5000, weekly, weeklyDisplay;
	public Player() {

	}

	public static void demandUpdate(){
		division = (workPopulation - totCap);
		//System.out.println(division);

	}
	public static void updatePop() {

		workPopulation = headCount;
		population = headCount;
		happiness = happyCount;
		houses = houseCount;
		if(division > 4){
			happiness -= (division/houses) * 2;
		}
		///System.out.println(happiness);
	}
	
	public static void resetCounts(){
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
