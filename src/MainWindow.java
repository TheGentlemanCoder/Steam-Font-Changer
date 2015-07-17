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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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


//Experimental
public class MainWindow {
	
	static String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	static String sizes[] = new String[51];
	static String fontSelected;
	static String sizeSelected;
	final JFileChooser fileSelect = new JFileChooser();
	String steamFolder;
	private JFrame frmSteamFontChanger;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		for(int i=1;i<=50;i++){
			sizes[i] = Integer.toString(i);
		}
		
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
		
		for(int i=1;i<=50;i++){
			sizes[i]= Integer.toString(i);
			}
		
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
		frmSteamFontChanger.setTitle("Steam Font Changer");
		frmSteamFontChanger.setResizable(false);
		frmSteamFontChanger.setBackground(SystemColor.menu);
		frmSteamFontChanger.setBounds(100, 100, 621, 437);
		frmSteamFontChanger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSteamFontChanger.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 14));
		tabbedPane.setBounds(0, 0, 615, 408);
		frmSteamFontChanger.getContentPane().add(tabbedPane);
		
		JPanel tab0 = new JPanel();
		tabbedPane.addTab("Steam Location", null, tab0, null);
		tab0.setLayout(null);
		
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				fileSelect.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if(fileSelect.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					steamFolder = fileSelect.getSelectedFile().toString();
					textField.setText(steamFolder);
//					System.out.println(steamFolder);
					tabbedPane.setSelectedIndex(1);
				    } 
				
			}
		});
		browseButton.setBounds(423, 168, 175, 33);
		tab0.add(browseButton);
		
		textField = new JTextField(steamFolder);
		textField.setEditable(false);
		textField.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField.setBounds(12, 168, 399, 33);
		tab0.add(textField);
		textField.setColumns(10);
		
		JLabel lblSpecifySteamFolder = new JLabel("Specify Steam folder location");
		lblSpecifySteamFolder.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblSpecifySteamFolder.setBounds(12, 137, 399, 33);
		tab0.add(lblSpecifySteamFolder);
		
		JPanel tab1 = new JPanel();
		tabbedPane.addTab("Basefont and Size", null, tab1, null);
		tab1.setBackground(UIManager.getColor("Button.background"));
		tab1.setLayout(null);
		
		JLabel lblFontFamily = new JLabel("Basefont:");
		lblFontFamily.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblFontFamily.setBounds(10, 11, 465, 30);
		tab1.add(lblFontFamily);
		
		JLabel lblFontSize = new JLabel("Font Size:");
		lblFontSize.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblFontSize.setBounds(487, 11, 113, 30);
		tab1.add(lblFontSize);
		
		JList fontList = new JList(fonts);
		fontList.setSelectedIndex(0);
		fontList.setBackground(Color.WHITE);
		fontList.setFont(new Font("Verdana", Font.PLAIN, 16));
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontList.setBounds(1, 42, 446, 5939);
		tab1.add(fontList);
		
		JScrollPane scrollPane = new JScrollPane(fontList);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 41, 465, 281);
		tab1.add(scrollPane);
		
		JList sizeList = new JList(sizes);
		sizeList.setFont(new Font("Verdana", Font.PLAIN, 16));
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sizeList.setSelectedIndex(1);
		sizeList.setBounds(487, 41, 113, 281);
		tab1.add(sizeList);
		
		JScrollPane fontSize = new JScrollPane(sizeList);
		fontSize.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fontSize.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		fontSize.setBounds(487, 41, 113, 281);
		tab1.add(fontSize);
		
		JButton fontButton = new JButton("Confirm Selection");
		fontButton.setBounds(10, 333, 590, 33);
		tab1.add(fontButton);
		fontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fontSelected = fontList.getSelectedValue().toString();
				sizeSelected = sizeList.getSelectedValue().toString();
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
		aboutText.setText("-----------------------------------\r\nSteam Font Changer\r\n-----------------------------------\r\nApp created by kiiraklis94\r\n\r\nThis application allows you to change how your steam client looks by changing the default font to whatever you like.\r\n\r\nIt may help you make steam more readable or whatever, I don't know.\r\n\r\nMore features (may be) coming soon...\r\n\r\n-----------------------------------\r\nHow to use SFC\r\n-----------------------------------\r\n1. Specify where Steam is installed by selecting the Steam folder in the first tab.\r\n\r\n2. Choose the font and font size you want from the lists. \r\n(The listed fonts are those that are available in your system, so if you have installed a font, it will appear in the list (probably... at least it's supposed to).)\r\n\r\n2. Click on the button \"Confirm Selection\".\r\n\r\n3. SFC will create a new theme/skin for you (called SFC_Font), and will place it in the correct folder automatically. \r\n\r\n4. After that, restart Steam, and the new theme will be available for you to apply.");
		aboutText.setBounds(10, 11, 590, 355);
		tab2.add(aboutText);
		
		JScrollPane scrollPane_2 = new JScrollPane(aboutText);
		scrollPane_2.setBounds(0, 0, 610, 374);
		tab2.add(scrollPane_2);
	}

	
	private void doit(){
		
		BufferedReader br = null;
	    BufferedWriter bw = null;
	    
    	File steamDefaultFile = new File(steamFolder + "/resource/styles/steam.styles");
	    File steamFile = new File(steamFolder + "/skins/SFC_Font/resource/styles/steam.styles");
    	
	    try{
	    	
	    	steamFile.getParentFile().mkdirs();
	    	
	        br = new BufferedReader(new FileReader(steamDefaultFile));
	        bw = new BufferedWriter(new FileWriter(steamFile));
	        
	         
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
	        
//	        System.out.println("The rest of the lines are Successfully copied!");
	         
	        br.close();
	        bw.close();
	        JOptionPane.showMessageDialog(frmSteamFontChanger, "Done");
	    }
	    catch(Exception e){
	        System.out.println("Exception caught : " + e);
	    }

		
	}
}
