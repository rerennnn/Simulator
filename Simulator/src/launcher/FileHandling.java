package launcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileHandling {

	public static boolean isDownloaded() {
		boolean bool = false;
		String current = System.getProperty("user.dir");
		System.out.println(current);
		File f = new File(current + "\\Simulator.jar");
		System.out.println("Checking");
		if (f.exists() && !f.isDirectory()) {
			bool = true;
			System.out.println("Downloaded");
		}
		return bool;
	}

	public static boolean runGame(){

			try {
				Process proc = Runtime.getRuntime().exec("java -jar Simulator.jar");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		return true;
		
	}

	@SuppressWarnings("finally")
	public static String getVersion() throws IOException {
		String str = null;
		BufferedReader reader = null;
		String current = System.getProperty("user.dir");
		try {

			File f = new File(current + "\\version.txt");

			if (f.exists() && f.isFile()) {
				reader = new BufferedReader(new FileReader(f));
				str = reader.readLine();
				reader.close();
			} else {
				str = "No version info available";
			}
			return str;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			reader.close();
			return str;
		}
	}


	public static String getLatestVersion() throws IOException{
		String str = "http://pastebin.com/raw.php?i=CV35ng6D";
		try {
			URL url = new URL(str);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			return in.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to request latest version";
		
	}

	public static String downloadLatest() throws IOException{
		String str = "http://pastebin.com/raw.php?i=CV35ng6D";
		String version = null;
		try {
			URL url = new URL(str);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			version = in.readLine();
			String dlUrlStr = in.readLine();
			URL dlUrl = new URL(dlUrlStr);
			String targetstr = System.getProperty("user.dir");
			Path target = Paths.get(targetstr + "\\Simulator.jar");
			//target.
			Files.copy(dlUrl.openStream(), target,  StandardCopyOption.REPLACE_EXISTING);
			System.out.println("downloaded");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return version;
		

		
	}
	static void updateLocal(String ver) throws IOException{
		String str = null;
		BufferedWriter writer = null;
		String current = System.getProperty("user.dir");
		try {

			File f = new File(current + "\\version.txt");

			if (f.exists() && f.isFile()) {
				f.delete();}
			File f2 = new File(current + "\\version.txt");
				writer = new BufferedWriter(new FileWriter(f2));
				writer.write(ver);
				writer.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			writer.close();

		}
	}
}