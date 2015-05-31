package bars;

import data.BlockType;

public enum ButtonType {

	Road("road1", BlockType.Road), House("housing", BlockType.House), Commercial(
			"commercial", BlockType.Shop), Destroy("destroy", BlockType.Plot), Industry(
			"industrial", BlockType.Industry), Park("park1", BlockType.Park), School(
			"school1", BlockType.School);

	String action;
	BlockType bType;

	ButtonType(String action, BlockType bType) {
		this.action = action;
		this.bType = bType;
	}
}
