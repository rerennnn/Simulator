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
		boolean bool = false; // if fails return false
		String current = System.getProperty("user.dir"); // current path, folder
															// of launcher
		File f = new File(current + "\\Simulator.jar");

		if (f.exists() && !f.isDirectory()) { // if file is present on system
												// location
			bool = true;
		}
		return bool;
	}

	public static boolean runGame() {

		try {
			@SuppressWarnings("unused")
			Process proc = Runtime.getRuntime().exec("java -jar Simulator.jar");	//execute command for jar file

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	@SuppressWarnings("finally")
	public static String getVersion() throws IOException {		//io exception for reader
		String str = null;
		BufferedReader reader = null;
		String current = System.getProperty("user.dir");
		try {

			File f = new File(current + "\\version.txt");		//local version file

			if (f.exists() && f.isFile()) {
				reader = new BufferedReader(new FileReader(f));
				str = reader.readLine();						//version on first line
				reader.close();
			} else {
				str = "No version info available";				//fail string
			}
			return str;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			reader.close();
			return str;
		}
	}

	public static String getLatestVersion() throws IOException {
		String str = "http://pastebin.com/raw.php?i=CV35ng6D";					//location of latest version number + download location
		try {
			URL url = new URL(str);
			BufferedReader in = new BufferedReader(new InputStreamReader(		//open url
					url.openStream()));
			return in.readLine();												// read version number in file
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to request latest version";								//fail string

	}

	public static String downloadLatest() throws IOException {
		String str = "http://pastebin.com/raw.php?i=CV35ng6D";				//location of latest version number + download location
		String version = null;
		try {
			URL url = new URL(str);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));										//open url
			version = in.readLine();
			String dlUrlStr = in.readLine();								// line of download url
			URL dlUrl = new URL(dlUrlStr);
			String targetstr = System.getProperty("user.dir");				//local directory, launcher folder
			Path target = Paths.get(targetstr + "\\Simulator.jar");			//whereto to download

			Files.copy(dlUrl.openStream(), target,							//copy from online to local
					StandardCopyOption.REPLACE_EXISTING);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return version;

	}

	static void updateLocal(String ver) throws IOException {	
		String str = null;
		BufferedWriter writer = null;
		String current = System.getProperty("user.dir");
		try {

			File f = new File(current + "\\version.txt");

			if (f.exists() && f.isFile()) {
				f.delete();												//remove current version file
			}
			File f2 = new File(current + "\\version.txt");				//create new version file
			writer = new BufferedWriter(new FileWriter(f2));
			writer.write(ver);											//write new version number
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			writer.close();

		}
	}
}