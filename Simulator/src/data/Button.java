package data;

import static helpers.Artist.*;

import org.newdawn.slick.opengl.Texture;
public class Button {

	Texture texture = null;
	String action;
	int x, y;
	public Button(ButtonType type,int x,int y){
		
		this.action = type.action;
		this.x = x;
		this.y = y;
		switch(action){
		case "road":
			texture = LoadTex("road");
			break;
		default:
			break;
		}
	}
	
	public void draw(){
		drawQuadTex(texture, x, y, 64, 64);
	}
}
