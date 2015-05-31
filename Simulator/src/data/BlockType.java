package data;

public enum BlockType {

	House("house", "housing", 1, 100, 2), Plot("plot", "plot", 0, 25, 0), Road(
			"road", "road", 0, 10, 0.2), Shop("commercial", "shops", 1, 100, 2), Industry(
			"industrial", "industry", 1, 100, 2), Park("park", "park", 0, 500,
			50), School("school", "School", 0, 1000, 100);
	String texName;
	public String type;
	public int capacity, level, population, cost;
	public double weekly;

	BlockType(String texName, String type, int lvl, int cost, double weekly) {
		this.texName = texName;
		this.type = type;
		this.level = lvl;
		this.capacity = 1;
		this.population = 0;
		this.cost = cost;
		this.weekly = weekly;
	}
}
