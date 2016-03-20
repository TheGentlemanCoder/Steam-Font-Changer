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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

	public static String fontSelected;
	public static String sizeSelected;
	public static String overlaySizeSelected;

	public static String steamFolder;

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
		frmSteamFontChanger.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmSteamFontChanger.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		gbl_tab1.rowWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_tab1.columnWeights = new double[] { 1.0 };
		tab1.setLayout(gbl_tab1);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Specify Steam Folder Location:",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		tab1.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 218, 107, 0 };
		gbl_panel.rowHeights = new int[] { 45, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		textField = new JTextField(steamFolder);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(12, 39, 489, 33);
		textField.setColumns(10);

		JButton browseButton = new JButton("Browse...");
		GridBagConstraints gbc_browseButton = new GridBagConstraints();
		gbc_browseButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_browseButton.gridx = 1;
		gbc_browseButton.gridy = 0;
		panel.add(browseButton, gbc_browseButton);
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

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Preferences:",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		tab1.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 175, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JList fontList = new JList(fonts);
		fontList.setBorder(null);
		fontList.setSelectedIndex(0);
		fontList.setBackground(Color.WHITE);
		fontList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontList.setBounds(1, 42, 446, 5939);
		// tab1.add(fontList);

		JScrollPane scrollFont = new JScrollPane(fontList);
		GridBagConstraints gbc_scrollFont = new GridBagConstraints();
		gbc_scrollFont.fill = GridBagConstraints.BOTH;
		gbc_scrollFont.insets = new Insets(0, 0, 0, 5);
		gbc_scrollFont.gridx = 0;
		gbc_scrollFont.gridy = 0;
		panel_1.add(scrollFont, gbc_scrollFont);
		scrollFont.setBorder(new TitledBorder(null, "Basefont:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollFont.setBounds(12, 121, 396, 281);

		JList sizeList = new JList(sizes);
		sizeList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sizeList.setSelectedIndex(14);
		sizeList.setBounds(487, 41, 113, 281);
		// tab1.add(sizeList);

		JScrollPane scrollSize = new JScrollPane(sizeList);
		GridBagConstraints gbc_scrollSize = new GridBagConstraints();
		gbc_scrollSize.fill = GridBagConstraints.BOTH;
		gbc_scrollSize.insets = new Insets(0, 0, 0, 5);
		gbc_scrollSize.gridx = 1;
		gbc_scrollSize.gridy = 0;
		panel_1.add(scrollSize, gbc_scrollSize);
		scrollSize.setBorder(new TitledBorder(null, "Font Size:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollSize.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSize.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSize.setBounds(420, 121, 105, 281);

		JList overlaySizeList = new JList(overlaySizes);
		overlaySizeList.setSelectedIndex(28);
		overlaySizeList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		overlaySizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		overlaySizeList.setBounds(535, 41, 182, 281);
		// tab1.add(overlaySizeList);

		JScrollPane scrollOverlaySize = new JScrollPane(overlaySizeList);
		GridBagConstraints gbc_scrollOverlaySize = new GridBagConstraints();
		gbc_scrollOverlaySize.anchor = GridBagConstraints.NORTH;
		gbc_scrollOverlaySize.fill = GridBagConstraints.BOTH;
		gbc_scrollOverlaySize.gridx = 2;
		gbc_scrollOverlaySize.gridy = 0;
		panel_1.add(scrollOverlaySize, gbc_scrollOverlaySize);
		scrollOverlaySize.setBorder(
				new TitledBorder(null, "Overlay Font Size:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollOverlaySize.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollOverlaySize.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollOverlaySize.setBounds(537, 121, 182, 281);

		JButton confirmButton = new JButton("Confirm Selection");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String steamFolder = textField.getText();
				String fontSelected = fontList.getSelectedValue().toString();
				String sizeSelected = sizeList.getSelectedValue().toString();
				String overlaySizeSelected = overlaySizeList.getSelectedValue().toString();
				edit.doIt(steamFolder, fontSelected, sizeSelected, overlaySizeSelected);
				
			}
		});
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.fill = GridBagConstraints.BOTH;
		gbc_confirmButton.gridx = 0;
		gbc_confirmButton.gridy = 2;
		tab1.add(confirmButton, gbc_confirmButton);

		JPanel tab2 = new JPanel();
		tabbedPane.addTab("About", null, tab2, null);
		GridBagLayout gbl_tab2 = new GridBagLayout();
		gbl_tab2.columnWidths = new int[] { 719, 0 };
		gbl_tab2.rowHeights = new int[] { 453, 0 };
		gbl_tab2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_tab2.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		tab2.setLayout(gbl_tab2);

		JTextPane aboutText = new JTextPane();
		aboutText.setEnabled(false);
		aboutText.setEditable(false);
		aboutText.setFont(new Font("Verdana", Font.PLAIN, 16));
		aboutText.setText(
				"-----------------------------------\r\nSteam Font Changer\r\n-----------------------------------\r\nApp created by kiiraklis94\r\n\r\nThis application allows you to change how your steam client looks by changing the default font to whatever you like.\r\n\r\nIt may help you make steam more readable or whatever, I don't know.\r\n\r\nMore features (may be) coming soon...\r\n\r\n-----------------------------------\r\nHow to use SFC\r\n-----------------------------------\r\n1. Specify where Steam is installed by selecting the Steam folder.\r\n\r\n2. Choose the font, font size and Steam overlay font size you want from the lists. \r\n(The listed fonts are those that are available in your system, so if you have installed a font, it will appear in the list (probably... at least it's supposed to).)\r\n\r\n2. Click on the button \"Confirm Selection\".\r\n\r\n3. SFC will create a new theme/skin for you (called SFC_Font), and will place it in the correct folder automatically. \r\n\r\n4. After that, restart Steam, and the new theme will be available for you to apply.");
		aboutText.setBounds(1, -155, 710, 623);
		// tab2.add(aboutText);

		JScrollPane scrollPane_2 = new JScrollPane(aboutText);
		scrollPane_2.setViewportBorder(null);
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 0;
		tab2.add(scrollPane_2, gbc_scrollPane_2);
	}

}
