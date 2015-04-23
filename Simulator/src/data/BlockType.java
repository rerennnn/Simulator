package data;

public enum BlockType {

	House("blue1", "house", 1), Plot("plot", "plot", 0), Road("road", "road", 0);
	String texName;
	String type;
	int capacity, level, population;
	
	
	BlockType(String texName, String type, int lvl){
		this.texName = texName;
		this.type = type;
		this.level = lvl;
		this.capacity = 1;
		this.population = 0;
	}
}
