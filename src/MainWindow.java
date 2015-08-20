import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
//test test test git

//Experimental
public class MainWindow {
	
	static String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	static String sizes[] = new String[51];
	static String overlaySizes[] = new String [51];
	
	static String fontSelected;
	static String sizeSelected;
	static String overlaySizeSelected;
	final JFileChooser fileSelect = new JFileChooser();
	String steamFolder;
	private JFrame frmSteamFontChanger;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Fill sizes and overlaySizes arrays
		for(int i=1;i<=50;i++){
			sizes[i] = Integer.toString(i);
			overlaySizes[i] = Integer.toString(i);
		}
		
		//Set window look and feel
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmSteamFontChanger.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmSteamFontChanger = new JFrame();
		frmSteamFontChanger.setFont(new Font("Verdana", Font.PLAIN, 16));
		frmSteamFontChanger.setTitle("Steam Font Changer");
		frmSteamFontChanger.setResizable(false);
		frmSteamFontChanger.setBackground(SystemColor.menu);
		frmSteamFontChanger.setBounds(100, 100, 740, 523);
		frmSteamFontChanger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSteamFontChanger.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 14));
		tabbedPane.setBounds(0, 0, 734, 651);
		frmSteamFontChanger.getContentPane().add(tabbedPane);

		JPanel tab1 = new JPanel();
		tabbedPane.addTab("Main", null, tab1, null);
		tab1.setBackground(UIManager.getColor("Button.background"));
		tab1.setLayout(null);
		
		JLabel lblSpecifySteamFolder = new JLabel("Specify Steam folder location");
		lblSpecifySteamFolder.setToolTipText("Show SFC where Steam is installed");
		lblSpecifySteamFolder.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSpecifySteamFolder.setBounds(12, 11, 396, 26);
		tab1.add(lblSpecifySteamFolder);
		
		textField = new JTextField(steamFolder);
		textField.setEditable(false);
		textField.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField.setBounds(12, 39, 489, 33);
		tab1.add(textField);
		textField.setColumns(10);
		
		JButton browseButton = new JButton("Browse...");
		browseButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				fileSelect.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if(fileSelect.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					steamFolder = fileSelect.getSelectedFile().toString();
					textField.setText(steamFolder);
				    } 
				
			}
		});
		browseButton.setBounds(510, 39, 207, 33);
		tab1.add(browseButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 83, 705, 2);
		tab1.add(separator);
		
		JLabel lblFontFamily = new JLabel("Basefont:");
		lblFontFamily.setToolTipText("Default: Arial");
		lblFontFamily.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblFontFamily.setBounds(12, 96, 396, 21);
		tab1.add(lblFontFamily);
		
		JLabel lblFontSize = new JLabel("Font Size:");
		lblFontSize.setToolTipText("Default: 14 (usually)");
		lblFontSize.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblFontSize.setBounds(420, 96, 105, 21);
		tab1.add(lblFontSize);
		
		JLabel lblOverlayFontSize = new JLabel("Overlay Font Size:");
		lblOverlayFontSize.setToolTipText("Default: 28");
		lblOverlayFontSize.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblOverlayFontSize.setBounds(537, 96, 180, 21);
		tab1.add(lblOverlayFontSize);
		
		JList fontList = new JList(fonts);
		fontList.setSelectedIndex(0);
		fontList.setBackground(Color.WHITE);
		fontList.setFont(new Font("Verdana", Font.PLAIN, 16));
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontList.setBounds(1, 42, 446, 5939);
		tab1.add(fontList);
		
		JScrollPane scrollFont = new JScrollPane(fontList);
		lblFontFamily.setLabelFor(scrollFont);
		scrollFont.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollFont.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollFont.setBounds(12, 121, 396, 281);
		tab1.add(scrollFont);
		
		JList sizeList = new JList(sizes);
		sizeList.setFont(new Font("Verdana", Font.PLAIN, 16));
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sizeList.setSelectedIndex(14);
		sizeList.setBounds(487, 41, 113, 281);
		tab1.add(sizeList);
		
		JScrollPane scrollSize = new JScrollPane(sizeList);
		lblFontSize.setLabelFor(scrollSize);
		scrollSize.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSize.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSize.setBounds(420, 121, 105, 281);
		tab1.add(scrollSize);
		
		JList overlaySizeList = new JList(overlaySizes);
		overlaySizeList.setSelectedIndex(28);
		overlaySizeList.setFont(new Font("Verdana", Font.PLAIN, 16));
		overlaySizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		overlaySizeList.setBounds(535, 41, 182, 281);
		tab1.add(overlaySizeList);
		
		JScrollPane scrollOverlaySize = new JScrollPane(overlaySizeList);
		scrollOverlaySize.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollOverlaySize.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollOverlaySize.setBounds(537, 121, 182, 281);
		tab1.add(scrollOverlaySize);
		
		JButton fontButton = new JButton("Confirm Selection");
		fontButton.setBounds(10, 413, 707, 41);
		tab1.add(fontButton);
		fontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fontSelected = fontList.getSelectedValue().toString();
				sizeSelected = sizeList.getSelectedValue().toString();
				overlaySizeSelected = overlaySizeList.getSelectedValue().toString();
				doit();
			}
		});
		fontButton.setFont(new Font("Verdana", Font.PLAIN, 18));
		
		
		JPanel tab2 = new JPanel();
		tabbedPane.addTab("About", null, tab2, null);
		tab2.setLayout(null);
		
		JTextPane aboutText = new JTextPane();
		aboutText.setEnabled(false);
		aboutText.setEditable(false);
		aboutText.setFont(new Font("Verdana", Font.PLAIN, 16));
		aboutText.setText("-----------------------------------\r\nSteam Font Changer\r\n-----------------------------------\r\nApp created by kiiraklis94\r\n\r\nThis application allows you to change how your steam client looks by changing the default font to whatever you like.\r\n\r\nIt may help you make steam more readable or whatever, I don't know.\r\n\r\nMore features (may be) coming soon...\r\n\r\n-----------------------------------\r\nHow to use SFC\r\n-----------------------------------\r\n1. Specify where Steam is installed by selecting the Steam folder.\r\n\r\n2. Choose the font, font size and Steam overlay font size you want from the lists. \r\n(The listed fonts are those that are available in your system, so if you have installed a font, it will appear in the list (probably... at least it's supposed to).)\r\n\r\n2. Click on the button \"Confirm Selection\".\r\n\r\n3. SFC will create a new theme/skin for you (called SFC_Font), and will place it in the correct folder automatically. \r\n\r\n4. After that, restart Steam, and the new theme will be available for you to apply.");
		aboutText.setBounds(1, -155, 710, 623);
		tab2.add(aboutText);
		
		JScrollPane scrollPane_2 = new JScrollPane(aboutText);
		scrollPane_2.setBounds(0, 0, 729, 467);
		tab2.add(scrollPane_2);
	}

	
	private void doit(){
		
		BufferedReader br = null;
	    BufferedWriter bw = null;
	    
		BufferedReader br2 = null;
	    BufferedWriter bw2 = null;
	    
	    //Client
    	File steamDefaultFile = new File(steamFolder + "/resource/styles/steam.styles");
	    File steamFile = new File(steamFolder + "/skins/SFC_Font/resource/styles/steam.styles");
	    //Overlay
    	File overlayDefaultFile = new File(steamFolder + "/resource/styles/gameoverlay.styles");
    	File overlayFile = new File(steamFolder + "/skins/SFC_Font/resource/styles/gameoverlay.styles");
    	
	    try{
	    	
	    	steamFile.getParentFile().mkdirs();
	    	overlayFile.getParentFile().mkdirs();
	    	
	        br = new BufferedReader(new FileReader(steamDefaultFile));
	        bw = new BufferedWriter(new FileWriter(steamFile));
	        
	        br2 = new BufferedReader(new FileReader(overlayDefaultFile));
	        bw2 = new BufferedWriter(new FileWriter(overlayFile));
	        
	         
	        // Do it for steam.styles
	        
	        String line = br.readLine();
	        
	        for( int i = 1; i <= 35 && line != null; i++)
	        {
	            bw.write(line);
	            bw.write("\n");
	            line = br.readLine();
	        }
	        for(int i = 36;i<=36 && line != null; i++){
	        	bw.write("    basefont=\"");
	        	bw.write(fontSelected);
	        	bw.write("\"");
	        	bw.write("\n");
	        	line = br.readLine();
	        	
	        }
	        for(int i = 37;i<=37 && line != null; i++){
	        	bw.write("    basefont=\"");
	        	bw.write(fontSelected);
	        	bw.write("\"");
	        	bw.write(" [$OSX]");
	        	bw.write("\n");
	        	line = br.readLine();
	        	
	        }
	        for (int i = 37; i<=3482 && line != null; i++ ){
		         
		        if(line.contains("font-size=")){

			        bw.write(line.replace(line, "		font-size=" + sizeSelected));
			        bw.write("\n");
			        line = br.readLine();
		        } 
		        else{
			        bw.write(line);
			        bw.write("\n");
			        line = br.readLine();
		        }
	        }
	        
	        //Do it for gameoverlay.styles
	        
	        line = br2.readLine();
	        
	        for (int i=1; i<=24 && line != null; i++){
	        	
	            bw2.write(line);
	            bw2.write("\n");
	            line = br2.readLine();
	        }
	        for (int i=25; i<=25 && line != null; i++){
	        	
		        bw2.write("			font-size=");
	        	bw2.write(overlaySizeSelected);
	        	bw2.write("\n");
	        	line = br2.readLine();
	        }
	        for(int i=26; i<=31 && line != null; i++){
	        	
	            bw2.write(line);
	            bw2.write("\n");
	            line = br2.readLine();
	        }
	         
	        
	        br.close();
	        bw.close();
	        
	        br2.close();
	        bw2.close();
	        
	        JOptionPane.showMessageDialog(frmSteamFontChanger, "You can now change your Steam skin to \"SFC_Font\".", "Operation Completed Successfuly", JOptionPane.INFORMATION_MESSAGE);
	    }
	    catch(Exception e){
	    	JOptionPane.showMessageDialog(frmSteamFontChanger, "You didn't specify the correct Steam location, or didn't specify a location at all.", "Error - File not found", JOptionPane.ERROR_MESSAGE);
	    }

		
	}
}
