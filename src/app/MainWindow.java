package app;

import java.awt.EventQueue;
import javax.swing.UIManager;


//Experimental
public class MainWindow {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Set window look and feel
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					WindowInit window = new WindowInit();
					window.frmSteamFontChanger.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	

	
	
}