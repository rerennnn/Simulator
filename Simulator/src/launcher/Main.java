package launcher;

import static launcher.FileHandling.downloadLatest;
import static launcher.FileHandling.getLatestVersion;
import static launcher.FileHandling.getVersion;
import static launcher.FileHandling.isDownloaded;
import static launcher.FileHandling.updateLocal;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;


public class Main {

	static JFrame frame;

	static JButton launchButton, updateCheck, updateButton;
	static JLabel welcome, current, update;

	public static void main(String[] args) throws IOException {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e){
			e.printStackTrace();
		}
		create();
		addButtons();
		addListeners();

		if (isDownloaded()) {								// if game present on systenm
			String str = getVersion();						// get current version
			current.setText(" Current version: " + str);	
			launchButton.setEnabled(true);					// if downloaded, enable launch

		} else {
			current.setText("No game has been found, please (re)download.");
		}
		
	}

	static void create() {										//create frame and layout
		frame = new JFrame("Simulator Launcher");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		GridLayout grid = new GridLayout();
		grid.setColumns(2);
		grid.setRows(3);
		// grid.
		frame.setLayout(grid);
		// JLabel emptyLabel = new JLabel("Hai");

		// frame.pack();
		frame.setVisible(true);
	}

	static void addButtons() {								//create buttons and layout, disable necessary buttons
		launchButton = new JButton("Launch");
		launchButton.setEnabled(false);
		updateCheck = new JButton("Check for updates");
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		welcome = new JLabel(" Welcome to Simulator");
		current = new JLabel(" Current version: ");
		update = new JLabel(" Available version: ");

		// launchButton.setSize(800, 100);
		// updateCheck.setSize(300, 100);
		// updateButton.setSize(300, 100);
		
		frame.getContentPane().add(launchButton, BorderLayout.NORTH);
		frame.getContentPane().add(welcome, BorderLayout.NORTH);
		frame.getContentPane().add(updateCheck, BorderLayout.SOUTH);
		frame.getContentPane().add(current, BorderLayout.NORTH);
		frame.getContentPane().add(updateButton, BorderLayout.SOUTH);
		frame.getContentPane().add(update, BorderLayout.SOUTH);
	}

	static void addListeners() {
		launchButton.addActionListener(new ActionListener() {	//actrionlistener launch game
			public void actionPerformed(ActionEvent e) {

				if (launcher.FileHandling.runGame()) {			// if + run game
					frame.dispose();							// if ran, close this launcher
				}
			}
		});
		
		updateCheck.addActionListener(new ActionListener(){		//actionlistener to check for updates for the game

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String ver = "Failed to get Version";
				try {
					ver = getLatestVersion();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update.setText(" Available version: "  + ver);	//update latest version text
				updateButton.setEnabled(true);
			}
			
		});
		
		updateButton.addActionListener(new ActionListener(){	//action listener for updating the game button

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				current.setText(" Downloading... ");
				String ver = "Failed to download";				// if downloading fails, this will be return string
				try {
					ver = downloadLatest();						// download latest + return version
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(ver == "Failed to download"){
					update.setText(ver);
				}else{
					current.setText(" Current version: " + ver);
					try {
						updateLocal(ver);
					} catch (IOException e) {
						e.printStackTrace();
					}
					launchButton.setEnabled(true);			//enable launch button if it wasnt yet, because of no version available
				}
			}
			
		});

	}

}
