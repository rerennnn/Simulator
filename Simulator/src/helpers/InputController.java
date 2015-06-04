package helpers;

import static helpers.Artist.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import bars.Toolbar;
import data.TileGrid;
import static helpers.Constants.*;
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
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){ 	//quit game on [ESC]
				Display.destroy();
			}
		}
		
		while(Mouse.next()){
			if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1) ){
				int button = Mouse.isButtonDown(1) ? 1 : 0;
				//System.out.println(Mouse.getX() + " " + Mouse.getY());	//Mouse in-frame coords
				xLoc = toLoc((Mouse.getX() - SIDEBAR));						//calc block x location of click
				yLoc = toLoc(HEIGHT - (Mouse.getY()));						//calc block y location of click
				xC = Mouse.getX();
				yC = Mouse.getY();											//save mouse coords
				if(xLoc>=0 && yLoc < mapHeight && yLoc >= 0){				// if block locations are within array
					if(button == 0){										//left mouse button
						TileGrid.click(xLoc, yLoc, Toolbar.selected);		//execute click on block x,y
					}else if(button == 1){									//right =mouse button
						TileGrid.rClick(xLoc, yLoc);						//excecute right click on block
					}
				}
				else {
					
					if(yLoc >= mapHeight){
						Toolbar.click(xC, yC);								//if not on playing field, try to click on toolbar
																			//sidebar has no buttons
					}
				}
			}
		}
	}
}
