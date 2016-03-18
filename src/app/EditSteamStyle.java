package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EditSteamStyle {

	WindowInit initClass = new WindowInit();

	String steamFolder = initClass.steamFolder;
	String fontSelected = initClass.fontSelected;
	String sizeSelected = initClass.sizeSelected;
	String overlaySizeSelected = initClass.overlaySizeSelected;
	JFrame frmSteamFontChanger = initClass.frmSteamFontChanger;

	public void doit() {

		BufferedReader br = null;
		BufferedWriter bw = null;

		BufferedReader br2 = null;
		BufferedWriter bw2 = null;

		// Client
		File steamDefaultFile = new File(steamFolder + "/resource/styles/steam.styles");
		File steamFile = new File(steamFolder + "/skins/SFC_Font/resource/styles/steam.styles");
		// Overlay
		File overlayDefaultFile = new File(steamFolder + "/resource/styles/gameoverlay.styles");
		File overlayFile = new File(steamFolder + "/skins/SFC_Font/resource/styles/gameoverlay.styles");

		try {

			steamFile.getParentFile().mkdirs();
			overlayFile.getParentFile().mkdirs();

			br = new BufferedReader(new FileReader(steamDefaultFile));
			bw = new BufferedWriter(new FileWriter(steamFile));

			br2 = new BufferedReader(new FileReader(overlayDefaultFile));
			bw2 = new BufferedWriter(new FileWriter(overlayFile));

			// Do it for steam.styles

			String line = br.readLine();

			for (int i = 1; i <= 35 && line != null; i++) {
				bw.write(line);
				bw.write("\n");
				line = br.readLine();
			}
			for (int i = 36; i <= 36 && line != null; i++) {
				bw.write("    basefont=\"");
				bw.write(fontSelected);
				bw.write("\"");
				bw.write("\n");
				line = br.readLine();

			}
			for (int i = 37; i <= 37 && line != null; i++) {
				bw.write("    basefont=\"");
				bw.write(fontSelected);
				bw.write("\"");
				bw.write(" [$OSX]");
				bw.write("\n");
				line = br.readLine();

			}
			for (int i = 37; i <= 3482 && line != null; i++) {

				if (line.contains("font-size=")) {

					bw.write(line.replace(line, "		font-size=" + sizeSelected));
					bw.write("\n");
					line = br.readLine();
				} else {
					bw.write(line);
					bw.write("\n");
					line = br.readLine();
				}
			}

			// Do it for gameoverlay.styles

			line = br2.readLine();

			for (int i = 1; i <= 24 && line != null; i++) {

				bw2.write(line);
				bw2.write("\n");
				line = br2.readLine();
			}
			for (int i = 25; i <= 25 && line != null; i++) {

				bw2.write("			font-size=");
				bw2.write(overlaySizeSelected);
				bw2.write("\n");
				line = br2.readLine();
			}
			for (int i = 26; i <= 31 && line != null; i++) {

				bw2.write(line);
				bw2.write("\n");
				line = br2.readLine();
			}

			br.close();
			bw.close();

			br2.close();
			bw2.close();

			JOptionPane.showMessageDialog(frmSteamFontChanger, "You can now change your Steam skin to \"SFC_Font\".",
					"Font Change Completed Successfuly", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmSteamFontChanger,
					"You didn't specify the correct Steam location, " + "or didn't specify a location at all.",
					"Error - File not found", JOptionPane.ERROR_MESSAGE);
		}

	}

}
