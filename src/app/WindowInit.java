package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.SystemColor;
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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;

public class WindowInit {

	static String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	static String sizes[] = new String[51];
	static String overlaySizes[] = new String[51];

	public String fontSelected;
	public String sizeSelected;
	public String overlaySizeSelected;

	public String steamFolder;

	public JTextField textField;

	public JFrame frmSteamFontChanger;
	final JFileChooser fileSelect = new JFileChooser();

	// Initialise contents of frame
	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void init() {

		EditSteamStyle edit = new EditSteamStyle();

		frmSteamFontChanger = new JFrame();
		frmSteamFontChanger.setFont(new Font("Verdana", Font.PLAIN, 16));
		frmSteamFontChanger.setTitle("Steam Font Changer");
		frmSteamFontChanger.setResizable(true);
		frmSteamFontChanger.setBackground(SystemColor.menu);
		frmSteamFontChanger.setBounds(100, 100, 740, 523);
		frmSteamFontChanger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Fill sizes and overlaySizes arrays
		for (int i = 1; i <= 50; i++) {

			// I use .toString() here because the values will be written in a
			// file, so it doesn't matter
			sizes[i] = Integer.toString(i);
			overlaySizes[i] = Integer.toString(i);
		}

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 734 };
		gridBagLayout.rowHeights = new int[] { 491 };
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		frmSteamFontChanger.getContentPane().setLayout(gridBagLayout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frmSteamFontChanger.getContentPane().add(tabbedPane, gbc_tabbedPane);

		JPanel tab1 = new JPanel();
		tab1.setBorder(null);
		tabbedPane.addTab("Main", null, tab1, null);
		tabbedPane.setEnabledAt(0, true);
		tab1.setBackground(UIManager.getColor("Button.background"));
		GridBagLayout gbl_tab1 = new GridBagLayout();
		gbl_tab1.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0 };
		gbl_tab1.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		tab1.setLayout(gbl_tab1);

		JLabel lblSpecifySteamFolder = new JLabel("Specify Steam folder location");
		lblSpecifySteamFolder.setToolTipText("Show SFC where Steam is installed");
		lblSpecifySteamFolder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSpecifySteamFolder.setBounds(12, 11, 396, 26);
		GridBagConstraints gbc_lblSpecifySteamFolder = new GridBagConstraints();
		gbc_lblSpecifySteamFolder.fill = GridBagConstraints.BOTH;
		gbc_lblSpecifySteamFolder.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpecifySteamFolder.gridx = 0;
		gbc_lblSpecifySteamFolder.gridy = 0;
		tab1.add(lblSpecifySteamFolder, gbc_lblSpecifySteamFolder);

		textField = new JTextField(steamFolder);
		textField.setEditable(false);
		textField.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField.setBounds(12, 39, 489, 33);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		tab1.add(textField, gbc_textField);
		textField.setColumns(10);

		JButton browseButton = new JButton("Browse...");
		browseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				fileSelect.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (fileSelect.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					steamFolder = fileSelect.getSelectedFile().toString();
					textField.setText(steamFolder);
				}

			}
		});
		browseButton.setBounds(510, 39, 207, 33);
		GridBagConstraints gbc_browseButton = new GridBagConstraints();
		gbc_browseButton.fill = GridBagConstraints.BOTH;
		gbc_browseButton.insets = new Insets(0, 0, 5, 0);
		gbc_browseButton.gridx = 2;
		gbc_browseButton.gridy = 1;
		tab1.add(browseButton, gbc_browseButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 83, 705, 2);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 3;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		tab1.add(separator, gbc_separator);

		JList fontList = new JList(fonts);
		fontList.setBorder(null);
		fontList.setSelectedIndex(0);
		fontList.setBackground(Color.WHITE);
		fontList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontList.setBounds(1, 42, 446, 5939);
		// tab1.add(fontList);

		JScrollPane scrollFont = new JScrollPane(fontList);
		scrollFont.setBorder(new TitledBorder(null, "Basefont:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollFont.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollFont.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollFont.setBounds(12, 121, 396, 281);
		GridBagConstraints gbc_scrollFont = new GridBagConstraints();
		gbc_scrollFont.fill = GridBagConstraints.BOTH;
		gbc_scrollFont.insets = new Insets(0, 0, 5, 5);
		gbc_scrollFont.gridx = 0;
		gbc_scrollFont.gridy = 3;
		tab1.add(scrollFont, gbc_scrollFont);

		JList sizeList = new JList(sizes);
		sizeList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sizeList.setSelectedIndex(14);
		sizeList.setBounds(487, 41, 113, 281);
		// tab1.add(sizeList);

		JScrollPane scrollSize = new JScrollPane(sizeList);
		scrollSize.setBorder(new TitledBorder(null, "Font Size:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollSize.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSize.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSize.setBounds(420, 121, 105, 281);
		GridBagConstraints gbc_scrollSize = new GridBagConstraints();
		gbc_scrollSize.fill = GridBagConstraints.BOTH;
		gbc_scrollSize.insets = new Insets(0, 0, 5, 5);
		gbc_scrollSize.gridx = 1;
		gbc_scrollSize.gridy = 3;
		tab1.add(scrollSize, gbc_scrollSize);

		JList overlaySizeList = new JList(overlaySizes);
		overlaySizeList.setSelectedIndex(28);
		overlaySizeList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		overlaySizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		overlaySizeList.setBounds(535, 41, 182, 281);
		// tab1.add(overlaySizeList);

		JScrollPane scrollOverlaySize = new JScrollPane(overlaySizeList);
		scrollOverlaySize.setBorder(new TitledBorder(null, "Overlay Font Size:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollOverlaySize.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollOverlaySize.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollOverlaySize.setBounds(537, 121, 182, 281);
		GridBagConstraints gbc_scrollOverlaySize = new GridBagConstraints();
		gbc_scrollOverlaySize.insets = new Insets(0, 0, 5, 0);
		gbc_scrollOverlaySize.fill = GridBagConstraints.BOTH;
		gbc_scrollOverlaySize.gridx = 2;
		gbc_scrollOverlaySize.gridy = 3;
		tab1.add(scrollOverlaySize, gbc_scrollOverlaySize);

		JButton fontButton = new JButton("Confirm Selection");
		fontButton.setBounds(10, 413, 707, 41);
		GridBagConstraints gbc_fontButton = new GridBagConstraints();
		gbc_fontButton.gridwidth = 3;
		gbc_fontButton.fill = GridBagConstraints.BOTH;
		gbc_fontButton.gridx = 0;
		gbc_fontButton.gridy = 4;
		tab1.add(fontButton, gbc_fontButton);
		fontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fontSelected = fontList.getSelectedValue().toString();
				sizeSelected = sizeList.getSelectedValue().toString();
				overlaySizeSelected = overlaySizeList.getSelectedValue().toString();
				edit.doit();
			}
		});
		fontButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JPanel tab2 = new JPanel();
		tabbedPane.addTab("About", null, tab2, null);
		GridBagLayout gbl_tab2 = new GridBagLayout();
		gbl_tab2.columnWidths = new int[]{719, 0};
		gbl_tab2.rowHeights = new int[]{453, 0};
		gbl_tab2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_tab2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		tab2.setLayout(gbl_tab2);
		
				JTextPane aboutText = new JTextPane();
				aboutText.setEnabled(false);
				aboutText.setEditable(false);
				aboutText.setFont(new Font("Verdana", Font.PLAIN, 16));
				aboutText.setText(
						"-----------------------------------\r\nSteam Font Changer\r\n-----------------------------------\r\nApp created by kiiraklis94\r\n\r\nThis application allows you to change how your steam client looks by changing the default font to whatever you like.\r\n\r\nIt may help you make steam more readable or whatever, I don't know.\r\n\r\nMore features (may be) coming soon...\r\n\r\n-----------------------------------\r\nHow to use SFC\r\n-----------------------------------\r\n1. Specify where Steam is installed by selecting the Steam folder.\r\n\r\n2. Choose the font, font size and Steam overlay font size you want from the lists. \r\n(The listed fonts are those that are available in your system, so if you have installed a font, it will appear in the list (probably... at least it's supposed to).)\r\n\r\n2. Click on the button \"Confirm Selection\".\r\n\r\n3. SFC will create a new theme/skin for you (called SFC_Font), and will place it in the correct folder automatically. \r\n\r\n4. After that, restart Steam, and the new theme will be available for you to apply.");
				aboutText.setBounds(1, -155, 710, 623);
				//		tab2.add(aboutText);
				
						JScrollPane scrollPane_2 = new JScrollPane(aboutText);
						scrollPane_2.setViewportBorder(null);
						GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
						gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
						gbc_scrollPane_2.gridx = 0;
						gbc_scrollPane_2.gridy = 0;
						tab2.add(scrollPane_2, gbc_scrollPane_2);
	}

}
