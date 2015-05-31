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
		ic.init();
		grid.draw();
		while(!Display.isCloseRequested()){
			//System.out.println("update");
			//grid.update();
			c.update();
			grid.draw();
			tb.draw();
			sb.draw();

			ic.update();
			Display.update();
			Display.sync(60);
		}
		
	}
	
	public static void updateClock(){
		grid.update();
	}

	public static void main(String[] args) throws LWJGLException {
		// TODO Auto-generated method stub
		new BootSim();
	}

}
