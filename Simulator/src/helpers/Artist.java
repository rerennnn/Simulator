package helpers;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Artist {

	public static final int WIDTH = 1900, HEIGHT = 1000, BLOCKSIZE = 64,
			SIDEBAR = 128, TOOLBAR = 128;
	public static int mapHeight, mapWidth;

	public static void BeginSession() {
		Display.setTitle("Game");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		calcSize();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void drawQuad(int x, int y, int width, int height) {
		glBegin(GL_QUADS);
		glVertex2f(x, y); // top left
		glVertex2f(x + width, y); // top right
		glVertex2f(x + width, y + height); // bottom right
		glVertex2f(x, y + height); // bottom left
		glEnd();
	}

	public static void drawQuadTex(Texture tex, int x, int y, int width,
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
		InputStream in = ResourceLoader.getResourceAsStream("res/" + path
				+ ".png");
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}

	public static int toLoc(int n) {
		if (n < 0) {
			return -1;
		} else {
			return n / BLOCKSIZE;
		}
	}

	public static int toCoord(int n) {
		return n * BLOCKSIZE;
	}

	public static void calcSize() {
		mapHeight = (int) Math.floor((HEIGHT - TOOLBAR) / BLOCKSIZE) + 1;
		mapWidth = (int) Math.floor((WIDTH - SIDEBAR) / BLOCKSIZE);
	}

}
