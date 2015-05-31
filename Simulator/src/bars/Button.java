package bars;

import static helpers.Artist.*;

import org.newdawn.slick.opengl.Texture;

import data.BlockType;
public class Button {

	Texture texture = null;
	String action;
	public int x;
	public int y;
	ButtonType type;
	public BlockType bType;
	public Button(ButtonType type,int x,int y){
		this.type = type;
		this.action = type.action;
		this.x = x;
		this.y = y;
		texture = LoadTex(action);
		this.bType = type.bType;
		
	}
	
	public void draw(int dx, int dy){							//Toolbar offset
		drawQuadTex(texture, x + dx, y + dy, 64, 64);
	}
}
