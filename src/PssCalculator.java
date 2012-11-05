import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * this class should undertake all necessary operations to calculate the
 * secondary structure preference of aminoacids in a PDB file directory.
 * 
 * @author papadopoulos
 * 
 */
public class PssCalculator {
	public static short ALPHA = 0;
	public static short BETA = 1;
	public static short COIL = 2;

	public short[] loadDSSPFile(String dsspfile) {
		try {
			FileInputStream fstream = new FileInputStream(dsspfile);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			short counter = 0;
			short[] structure = new short[1];
			int length = 0;
			short aminoIndex = 0;
			while ((strLine = br.readLine()) != null) {
				if (counter == 2) {
					StringTokenizer tk = new StringTokenizer(strLine);
					length = Integer.parseInt(tk.nextToken());
					structure = new short[length];
				}

				if (counter > 23) {
					// 135 133 A L 0 0 140 37,-0.1 -1,-0.0 38,-0.0 34,-0.0
					// -0.066 360.0 360.0 36.1 360.0 34.9 16.3 28.5
					StringTokenizer tk = new StringTokenizer(strLine);
					tk.nextToken();
					if (tk.nextToken().equals("!")) {
						continue;
					}
					tk.nextToken();
					tk.nextToken();
					String struct = tk.nextToken();
					if (struct.equals("H") || struct.equals("G")
							|| struct.equals("I")) {
						structure[aminoIndex] = ALPHA;
					} else if (struct.equals("B") || struct.equals("E")) {
						structure[aminoIndex] = BETA;
					} else {
						structure[aminoIndex] = COIL;
					}
					aminoIndex++;
				}

				counter++;
			}
			in.close();
			return structure;
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: The pdb file has this to say: "
					+ e.getMessage());
		}
		return null;
	}

	public AminoAcid3d[] loadPDBFile(String pdbfile) {
		try {
			FileInputStream fstream = new FileInputStream(pdbfile);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			ArrayList<AminoAcid3d> aminoList = new ArrayList<AminoAcid3d>();
			String strLine;
			int aminoIndex = 0;
			while ((strLine = br.readLine()) != null) {
				if (strLine.startsWith("ATOM")) {
					// int serial = findInt(strLine.substring(6, 11));
					String atomName = strLine.substring(12, 16).trim();
					String resName = strLine.substring(17, 20).trim();
					if (resName.equals("GLY")) {
						// System.err.println("glycine here!");
						if (atomName.contains("CA")) {
							double x = findDouble(strLine.substring(30, 38));
							double y = findDouble(strLine.substring(38, 46));
							double z = findDouble(strLine.substring(46, 54));
							AminoAcid3d aa3d = new AminoAcid3d(resName, x, y, z);
							aminoList.add(aa3d);
						}
					} else {
						if (atomName.contains("CB")) {
							double x = findDouble(strLine.substring(30, 38));
							double y = findDouble(strLine.substring(38, 46));
							double z = findDouble(strLine.substring(46, 54));
							AminoAcid3d aa3d = new AminoAcid3d(resName, x, y, z);
							aminoList.add(aa3d);
						}

					}

					// String altLoc = strLine.substring(16, 17).trim();
					// String resName = strLine.substring(17, 20).trim();
					// String chainID = strLine.substring(21, 22).trim();
					// int resSeq = findInt(strLine.substring(22, 26));
					// String iCode = strLine.substring(26, 27).trim();

					// double occupancy = findDouble(strLine.substring(54, 60));
					// double tempFactor = findDouble(strLine.substring(60,
					// 66));
					// String element = strLine.substring(76, 78).trim();
					// String charge = strLine.substring(78, 80).trim();

				}
			}
			in.close();

			AminoAcid3d[] aminoArray = new AminoAcid3d[aminoList.size()];
			for (int i = 0; i < aminoList.size(); i++) {
				aminoArray[i] = aminoList.get(i);
			}
			return aminoArray;
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: The pdb file has this to say: "
					+ e.getMessage());
		}
		return null;
	}

	public double findDouble(String s) {
		return Double.parseDouble(s.trim());
	}

	public void calculateContacts(AminoAcid3d[] aminoArray) {
		for (int i = 0; i < aminoArray.length - 1; i++) {
			for (int j = i + 1; j < aminoArray.length; j++) {
				double distance = aminoArray[i]
						.calculateDistance(aminoArray[j]);
				if (distance < 6.00) { // we got a contact
					if (j - i < 6) { // a local contact!
						aminoArray[i].addLocalContact();
						aminoArray[j].addLocalContact();
					} else { // a global contact
						aminoArray[i].addGlobalContact();
						aminoArray[j].addGlobalContact();
					}
				}
			}
		}
	}
}
