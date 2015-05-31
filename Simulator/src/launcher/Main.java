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
import javax.swing.UnsupportedLookAndFeelException;

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

		if (isDownloaded()) {
			String str = getVersion();
			current.setText(" Current version: " + str);
			launchButton.setEnabled(true);

		} else {
			current.setText("No game has been found, please (re)download.");
		}
		
	}

	static void create() {
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

	static void addButtons() {
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
		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (launcher.FileHandling.runGame()) {
					frame.dispose();
				}
			}
		});
		
		updateCheck.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String ver = "Failed to get Version";
				try {
					ver = getLatestVersion();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update.setText(" Available version: "  + ver);
				updateButton.setEnabled(true);
			}
			
		});
		
		updateButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				current.setText(" Downloading... ");
				String ver = "Failed to download";
				try {
					ver = downloadLatest();
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
					launchButton.setEnabled(true);
				}
			}
			
		});

	}

}
