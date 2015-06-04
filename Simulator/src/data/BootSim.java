package data;

import static helpers.Artist.BeginSession;
import helpers.Clock;
import helpers.InputController;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import bars.Sidebar;
import bars.Toolbar;
public class BootSim {
	static TileGrid grid;
	public BootSim() throws LWJGLException{			//All throws LWJGL Exeptions because of keyboard.create() in inputcontroller
		Clock c = new Clock(1);
		BeginSession();


		Toolbar tb = new Toolbar();
		Sidebar sb = new Sidebar();
		grid = new TileGrid();

		InputController ic = new InputController();
		ic.init();							//initialize inputcontroller
		grid.draw();						//draw grid for first time
		while(!Display.isCloseRequested()){	//while game is running

			c.update();						//update clock	
			grid.draw();					//draw gamegrid
			tb.draw();						//draw toolbar
			sb.draw();						//draw sidebar

			ic.update();					//update inputcontroller, poll for input changes
			
			Display.update();				//refresh display
			Display.sync(60);				//sync to this fps
		}
		
	}
	
	public static void updateClock(){
		grid.update();						//called from Clock.java, will update grid every n seconds
	}

	public static void main(String[] args) throws LWJGLException {
		// TODO Auto-generated method stub
		new BootSim();
	}

}
