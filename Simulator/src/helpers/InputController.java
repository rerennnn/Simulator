package helpers;

import static helpers.Artist.*;
import static data.Toolbar.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import data.TileGrid;
import data.Toolbar;

public class InputController {

	int xLoc, yLoc, xC, yC;
	boolean inField;
	public InputController() 
	{
		
	}
	public void init(){
		try {
			Keyboard.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(){
		while(Keyboard.next()){
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				Display.destroy();
			}
		}
		
		while(Mouse.next()){
			if(Mouse.isButtonDown(0)){
				//System.out.println(Mouse.getX() + " " + Mouse.getY());	//Mouse in-frame coords
				xLoc = toLoc((Mouse.getX() - SIDEBAR));						//calc block x location of click
				yLoc = toLoc(HEIGHT - (Mouse.getY()));						//calc block y location of click
				xC = Mouse.getX();
				yC = Mouse.getY();											//save mouse coords
				if(xLoc>=0 && yLoc < mapHeight && yLoc >= 0){				// if block locations are withing array
					TileGrid.click(xLoc, yLoc);								//execute click on block x,y
				}
				else {
					System.out.println("Not in field");
					if(yLoc >= mapHeight){
						Toolbar.click(xC, yC);
					}
				}
			}
		}
	}
}
