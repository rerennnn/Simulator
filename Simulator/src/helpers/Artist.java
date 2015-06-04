package helpers;

import static helpers.Constants.BLOCKSIZE;
import static helpers.Constants.HEIGHT;
import static helpers.Constants.SIDEBAR;
import static helpers.Constants.TOOLBAR;
import static helpers.Constants.WIDTH;
import static helpers.Constants.mapHeight;
import static helpers.Constants.mapWidth;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Artist {
	
	//TrueTypeFont font;

	
	static Font awtFont;
	static TrueTypeFont font;
	public static void BeginSession() {
		Display.setTitle("SimCity Basic");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));		
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		calcSize();	// game field size
		
		
		//text fonts
		awtFont = new Font("Arial", Font.PLAIN, 14);
		font = new TrueTypeFont(awtFont, false);
		
		
		//set GL params
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void drawQuad(int x, int y, int width, int height) {		//draw rectangle
		glBegin(GL_QUADS);
		glVertex2f(x, y); // top left
		glVertex2f(x + width, y); // top right
		glVertex2f(x + width, y + height); // bottom right
		glVertex2f(x, y + height); // bottom left
		glEnd();
	}


	public static void drawQuadTex(Texture tex, int x, int y, int width,				//draw rectangle with texture
			int height) {
		tex.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); // top left
		glVertex2f(0, 0);
		glTexCoord2f(1, 0); // top right
		glVertex2f(width, 0);
		glTexCoord2f(1, 1); // bottom right
		glVertex2f(width, height);
		glTexCoord2f(0, 1); // bottom left
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}

	/*
	 * Name of texture in folder res
	 */
	public static Texture LoadTex(String path) {
		Texture tex = null;
		String fileType = "PNG";
		InputStream in = ResourceLoader.getResourceAsStream("res/" + path			// only input is file name without extension, only png
				+ ".png");
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}

	public static void drawText(int x, int y, String text){							//draw text on location
		font.drawString(x, y, text, Color.white);
	}
	
	public static int toLoc(int n) {												//coordinates to field location
		if (n < 0) {
			return -1;
		} else {
			return n / BLOCKSIZE;
		}
	}

	public static int toCoord(int n) {												//field block location to screen coordinates
		return n * BLOCKSIZE;
	}

	public static void calcSize() {
		mapHeight = (int) Math.floor((HEIGHT - TOOLBAR) / BLOCKSIZE) + 1;			//amount of vertical blocks
		mapWidth = (int) Math.floor((WIDTH - SIDEBAR) / BLOCKSIZE);					//amount of horizontal blocks
	}
	


}
