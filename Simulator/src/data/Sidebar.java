package data;

import static helpers.Artist.*;

import  static org.lwjgl.opengl.GL11.*;

public class Sidebar {

	public Sidebar(){	
	}
	
	public void draw(){
		glColor3f(0.25f, 0.25f, 0.9f);
		drawQuad(0, 0, SIDEBAR, BLOCKSIZE * mapHeight);
		glColor3f(1f, 1f, 1f);
	}
	
}
