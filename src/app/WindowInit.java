package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class WindowInit {

	
	static String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	static String sizes[] = new String[51];
	static String overlaySizes[] = new String [51];
	
	public String fontSelected;
	public String sizeSelected;
	public String overlaySizeSelected;
	
	public String steamFolder;
	
	public JTextField textField;

	public JFrame frmSteamFontChanger;
	final JFileChooser fileSelect = new JFileChooser();
	
	
	//Constructor that runs init() method on call
	public WindowInit(){
		init();
	}

	
	//Initialise contents of frame
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void init() {
		
		EditSteamStyle edit = new EditSteamStyle();
		
		frmSteamFontChanger = new JFrame();
		frmSteamFontChanger.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kiiraklis94\\Documents\\[] Dev\\EclipseWorkspace\\Steam-Font-Changer\\bin\\steam.ico"));
		frmSteamFontChanger.setFont(new Font("Verdana", Font.PLAIN, 16));
		frmSteamFontChanger.setTitle("Steam Font Changer");
		frmSteamFontChanger.setResizable(false);
		frmSteamFontChanger.setBackground(SystemColor.menu);
		frmSteamFontChanger.setBounds(100, 100, 740, 523);
		frmSteamFontChanger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSteamFontChanger.getContentPane().setLayout(null);
		
		
		//Fill sizes and overlaySizes arrays
		for(int i=1;i<=50;i++){
			sizes[i] = Integer.toString(i);
			overlaySizes[i] = Integer.toString(i);
		}
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 14));
		tabbedPane.setBounds(0, 0, 734, 494);
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
				edit.doit();
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
	
	
	
	
}
