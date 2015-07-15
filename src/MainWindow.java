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

public class MainWindow {
	
	static String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	static String sizes[] = new String[51];
	static String fontSelected;
	static String sizeSelected;
	final JFileChooser fileSelect = new JFileChooser();
	String steamFolder;
	private JFrame frmSteamAppearenceCustomizer;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmSteamAppearenceCustomizer.setVisible(true);
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
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmSteamAppearenceCustomizer = new JFrame();
		frmSteamAppearenceCustomizer.setTitle("Steam Font Changer");
		frmSteamAppearenceCustomizer.setResizable(false);
		frmSteamAppearenceCustomizer.setBackground(SystemColor.menu);
		frmSteamAppearenceCustomizer.setBounds(100, 100, 621, 434);
		frmSteamAppearenceCustomizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSteamAppearenceCustomizer.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 13));
		tabbedPane.setBounds(0, 0, 615, 405);
		frmSteamAppearenceCustomizer.getContentPane().add(tabbedPane);
		
		JPanel tab0 = new JPanel();
		tabbedPane.addTab("Steam Location", null, tab0, null);
		tab0.setLayout(null);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setBounds(423, 168, 175, 33);
		tab0.add(btnNewButton);
		
		textField = new JTextField(steamFolder);
		textField.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField.setBounds(12, 168, 399, 33);
		tab0.add(textField);
		textField.setColumns(10);
		
		JLabel lblSpecifySteamFolder = new JLabel("Specify Steam folder location");
		lblSpecifySteamFolder.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblSpecifySteamFolder.setBounds(12, 137, 399, 33);
		tab0.add(lblSpecifySteamFolder);
		
		JPanel tab1 = new JPanel();
		tabbedPane.addTab("Basefont", null, tab1, null);
		tab1.setBackground(UIManager.getColor("Button.background"));
		tab1.setLayout(null);
		
		JLabel lblFontFamily = new JLabel("Basefont");
		lblFontFamily.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFontFamily.setBounds(10, 11, 439, 30);
		tab1.add(lblFontFamily);
		
		JList fontList = new JList(fonts);
		fontList.setSelectedIndex(0);
		fontList.setBackground(Color.WHITE);
		fontList.setFont(new Font("Verdana", Font.PLAIN, 16));
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontList.setBounds(1, 41, 571, 6009);
		tab1.add(fontList);
		
		JScrollPane scrollPane = new JScrollPane(fontList);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 41, 590, 281);
		tab1.add(scrollPane);
		
		JButton fontButton = new JButton("Confirm Selection");
		fontButton.setBounds(10, 333, 590, 33);
		tab1.add(fontButton);
		fontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fontSelected = fontList.getSelectedValue().toString();
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
		aboutText.setText("-----------------------------------\r\nSteam Font Changer\r\n-----------------------------------\r\nApp created by kiiraklis94\r\n\r\nThis application allows you to change how your steam client looks by changing the default font to whatever you like.\r\n\r\nIt may help you make steam more readable or whatever, I don't know.\r\n\r\nMore features (may be) coming soon...\r\n\r\n-----------------------------------\r\nHow to use SFC\r\n-----------------------------------\r\n1. Specify where Steam is installed by selecting the Steam folder in the first tab.\r\n\r\n2. Choose the font you want from the list. The listed fonts are those that are available in your system, so if you have installed a font, it will appear in the list (probably... at least it's supposed to).\r\n\r\n2. Click on the button \"Confirm Selection\".\r\n\r\n3. SFC will create a new theme/skin for you (Called SFC_Font), and will place it in the correct folder automatically. \r\n\r\n4. After that, restart Steam, and the new theme will be available for you to apply.");
		aboutText.setBounds(10, 11, 590, 355);
		tab2.add(aboutText);
		
		JScrollPane scrollPane_2 = new JScrollPane(aboutText);
		scrollPane_2.setBounds(0, 0, 610, 374);
		tab2.add(scrollPane_2);
	}

	
	private void doit(){
		
		BufferedReader br = null;
	    BufferedWriter bw = null;
	     
	    try{
	    	File steamFile = new File(steamFolder + "/skins/SFC_Font/resource/styles/steam.styles");
	    	File steamDefaultFile = new File(steamFolder + "/resource/styles/steam.styles");
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
	        
//	        System.out.println("Lines are Successfully copied!");
	        
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
//	        System.out.println("Font Selected: " + fontSelected);
	        
	        for (int i = 37; i<=3482 && line != null; i++ ){
	        	 bw.write(line);
		         bw.write("\n");
		         line = br.readLine();
	        }
	        
//	        System.out.println("The rest of the lines are Successfully copied!");
	         
	        br.close();
	        bw.close();
	        JOptionPane.showMessageDialog(frmSteamAppearenceCustomizer, "Done");
	    }
	    catch(Exception e){
	        System.out.println("Exception caught : " + e);
	    }
//		System.out.println("Done");
		
	}
}
