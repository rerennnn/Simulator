package data;

import static helpers.Artist.*;

import org.newdawn.slick.opengl.Texture;

public class Block {

	private Texture texture;

	private int xCoord, yCoord, width, height;
	private BlockType myType;

	public Block(int locX, int locY, BlockType type) {
		this.width = BLOCKSIZE;
		this.height = BLOCKSIZE;
		this.xCoord = toCoord(locX);
		this.yCoord = toCoord(locY);
		this.myType = type;
		texture = LoadTex(myType.texName);
	}

	public void draw() {
		// System.out.println("Draw block");
		drawQuadTex(texture, xCoord, yCoord, width, height);
	}
}
