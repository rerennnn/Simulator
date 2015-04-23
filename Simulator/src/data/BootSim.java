package data;

import static helpers.Artist.BeginSession;
import helpers.InputController;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
public class BootSim {
	
	public BootSim() throws LWJGLException{			//All throws LWJGL Exeptions because of keyboard.create() in inputcontroller
		System.out.println("1");
		BeginSession();
		System.out.println("2");
		//drawQuad(100, 100, 50, 50);
		Toolbar tb = new Toolbar();
		Sidebar sb = new Sidebar();
		TileGrid grid = new TileGrid();
		InputController ic = new InputController();
		ic.init();
		while(!Display.isCloseRequested()){
			//System.out.println("update");
			grid.draw();
			sb.draw();
			tb.draw();
			ic.update();
			Display.update();
			Display.sync(30);
		}
		
	}
	


	public static void main(String[] args) throws LWJGLException {
		// TODO Auto-generated method stub
		new BootSim();
	}

}
