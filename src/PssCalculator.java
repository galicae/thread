import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * this class should undertake all necessary operations to calculate the
 * secondary structure preference of aminoacids in a PDB file directory.
 * 
 * @author papadopoulos
 * 
 */
public class PssCalculator {

	public void loadPDBFile(String pdbfile) {
		try {
			FileInputStream fstream = new FileInputStream(pdbfile);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			while ((strLine = br.readLine()) != null) {
				if (strLine.startsWith("ATOM")) {
					// int serial = findInt(strLine.substring(6, 11));
					String atomName = strLine.substring(12, 16).trim();
					if (atomName.contains("CA")) {
						String resName = strLine.substring(17, 20).trim();
						double x = findDouble(strLine.substring(30, 38));
						double y = findDouble(strLine.substring(38, 46));
						double z = findDouble(strLine.substring(46, 54));

						AminoAcid3d aa3d = new AminoAcid3d(resName, x, y, z);
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
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: The pdb file has this to say: "
					+ e.getMessage());
		}
	}

	public double findDouble(String s) {
		return Double.parseDouble(s.trim());
	}
}
