package bars;

import static helpers.Artist.drawQuad;
import static helpers.Artist.drawText;
import static helpers.Constants.BLOCKSIZE;
import static helpers.Constants.HEIGHT;
import static helpers.Constants.SIDEBAR;
import static helpers.Constants.WIDTH;
import static helpers.Constants.mapHeight;
import static org.lwjgl.opengl.GL11.glColor3f;

import java.text.DecimalFormat;

import data.BlockType;

public class Toolbar {

	public static BlockType selected = null;
	public static BlockType lastSelected = null;
	public int buttonSize = 64;
	static Button[] buttons = new Button[10];
	DecimalFormat f = new DecimalFormat("##.00");
	public Toolbar() {
	
		buttons[0] = new Button(ButtonType.Road, 96, 16);
		buttons[1] = new Button(ButtonType.House, 176, 16);
		buttons[2] = new Button(ButtonType.Destroy, 16, 16);
		buttons[3] = new Button(ButtonType.Industry, 256, 16);
		buttons[4] = new Button(ButtonType.Commercial, 336, 16);
		buttons[5] = new Button(ButtonType.Park, 416, 16);
		buttons[6] = new Button(ButtonType.School, 496, 16);

	}

	public void draw() {
		glColor3f(0.24f, 0.6f, 0.36f);
		drawQuad(0, BLOCKSIZE * mapHeight, WIDTH, HEIGHT
				- (BLOCKSIZE * mapHeight));
		glColor3f(1f, 1f, 1f);
		if(selected != null && lastSelected != selected){
		drawText(8, HEIGHT - 32, "Cost: $" + String.valueOf(f.format(selected.cost)));
		drawText(8, HEIGHT - 16, "Weekly cost: $" + String.valueOf(selected.weekly));}
		for (int i = 0; i < buttons.length; i++) {
			// System.out.println(buttons[i]);
			if (buttons[i] != null) {
				buttons[i].draw(SIDEBAR, mapHeight * BLOCKSIZE - 16);
			}
		}
	}

	public static void click(int x, int y) { // Loop through buttons to find if
												// one is clicked

		for (int i = 0; i < buttons.length; i++) {

			if (buttons[i] != null) {

				int bx = buttons[i].x + SIDEBAR;
				int by = buttons[i].y + BLOCKSIZE;

				if (clickedIn(bx, by, x, y)) {

					selected = buttons[i].bType; // set selected button to
													// clicked button type

				}
			}
		}
	}

	private static boolean clickedIn(int bx, int by, int mx, int my) { // Button and mouse x and y

		boolean bool = false;
		// System.out.println(bx + " " + by + " " + (bx + 64) + " " + (by - 64)
		// + " " + mx + " " + my);
		if (bx <= mx && (bx + 64) > mx && by > my // If within button range
				&& ((by - 64) < my)) {
			bool = true;
		} else {
			bool = false;
		}

		return bool;
	}
}
