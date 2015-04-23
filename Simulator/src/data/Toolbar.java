package data;
import java.util.*;
import static helpers.Artist.*;
import static org.lwjgl.opengl.GL11.glColor3f;
public class Toolbar {
	
	public int buttonSize = 64;
	
	public Toolbar(){
	Button[] buttons = new Button[10];
	
	}
	
	public void draw(){
		glColor3f(0.24f, 0.27f, 0.36f);
		drawQuad(0, BLOCKSIZE * mapHeight, WIDTH, HEIGHT - (BLOCKSIZE * mapHeight));
		glColor3f(1f, 1f, 1f);
		//for (int i = 0; i < buttons.size(); i++) {
		//	System.out.println(buttons.get(i));
		//}/
	}
	
	public static void click(int x, int y){
		
	}
	
	
}
