import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import resources.AminoAcid;
import resources.AminoAcids;
import resources.cAtom;

/**
 * this class should undertake all necessary operations to calculate the
 * secondary structure preference of aminoacids in a PDB file directory.
 * 
 * @author papadopoulos
 * 
 */
public class GeneralReader {
	int cb = 0;
	int gly = 0;
	public static short ALPHA = 0;
	public static short BETA = 1;
	public static short COIL = 2;
	public static double sqrt2 = Math.sqrt(2);
	public static double distanceCutoff = Math.sqrt(42);

	public short[] loadDSSPFile(String dsspfile) {
		try {
			FileInputStream fstream = new FileInputStream(dsspfile);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			short counter = 0;
			ArrayList<Short> structureList = new ArrayList<Short>();
			int length = 0;
			while ((strLine = br.readLine()) != null) {

				if (counter > 23) {
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
						structureList.add(ALPHA);
					} else if (struct.equals("B") || struct.equals("E")) {
						structureList.add(BETA);
					} else {
						structureList.add(COIL);
					}
				}

				counter++;
			}
			in.close();
			short[] structure = new short[structureList.size()];
			for (int i = 0; i < structureList.size(); i++) {
				structure[i] = structureList.get(i);
			}
			return structure;
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: The pdb file has this to say: "
					+ e.getMessage());
		}
		return null;
	}

	// public AminoAcid3d[] loadPDBFile1(String pdbfile) {
	// try {
	// FileInputStream fstream = new FileInputStream(pdbfile);
	// DataInputStream in = new DataInputStream(fstream);
	// BufferedReader br = new BufferedReader(new InputStreamReader(in));
	// ArrayList<AminoAcid3d> aminoList = new ArrayList<AminoAcid3d>();
	// String strLine;
	// System.out.println(pdbfile);
	//
	// ArrayList<Atom> atomList = new ArrayList<Atom>();
	//
	// while ((strLine = br.readLine()) != null) {
	// if (strLine.startsWith("ATOM")) {
	// String name = strLine.substring(12, 16).trim();
	// String resName = strLine.substring(17, 20).trim();
	// String chainID = strLine.substring(21, 22).trim();
	// int resSeq = findInt(strLine.substring(22, 26));
	// String iCode = strLine.substring(26, 27).trim();
	// double x = findDouble(strLine.substring(30, 38));
	// double y = findDouble(strLine.substring(38, 46));
	// double z = findDouble(strLine.substring(46, 54));
	// Atom a = new Atom(name, resName, chainID, resSeq, iCode, x,
	// y, z);
	// atomList.add(a);
	// }
	// }
	// in.close();
	//
	// int curr = atomList.get(0).getResSeq();
	// AminoAcid3d am;
	// for (int i = 0; i < atomList.size(); i++) {
	// Atom currAt = atomList.get(i);
	// am = new AminoAcid3d(currAt.getResName(), currAt.getX(),
	// currAt.getY(), currAt.getZ());
	// while (atomList.get(i).getResSeq() == curr) {
	// if (currAt.getName().equals("CB")) {
	// am = new AminoAcid3d(currAt.getResName(),
	// currAt.getX(), currAt.getY(), currAt.getZ());
	// i++;
	// }
	// }
	// }
	// AminoAcid3d[] aminoArray = new AminoAcid3d[aminoList.size()];
	// for (int i = 0; i < aminoList.size(); i++) {
	// // System.out.println(aminoList.get(i).getNameOneLetter());
	// aminoArray[i] = aminoList.get(i);
	// }
	// return aminoArray;
	// } catch (Exception e) {// Catch exception if any
	// e.printStackTrace();
	// }
	// return null;
	// }

	public AminoAcid[] loadPDBFile(String pdbfile) {
		try {
			FileInputStream fstream = new FileInputStream(pdbfile);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			ArrayList<AminoAcid> aa = new ArrayList<AminoAcid>();
			String strLine;
			String curr = "";
			String prev = "empty";
			cAtom a = new cAtom();
			ArrayList<cAtom> atoms = new ArrayList<cAtom>();
			while ((strLine = br.readLine()) != null) {
				if (strLine.startsWith("ATOM")) {
					int serial = findInt(strLine.substring(6, 11));
					String name = strLine.substring(12, 16).trim();
					String altLoc = strLine.substring(16, 17).trim();
					String resName = strLine.substring(17, 20).trim();
					String chainID = strLine.substring(21, 22).trim();
					int resSeq = findInt(strLine.substring(22, 26));
					String iCode = strLine.substring(26, 27).trim();
					double x = findDouble(strLine.substring(30, 38));
					double y = findDouble(strLine.substring(38, 46));
					double z = findDouble(strLine.substring(46, 54));

					curr = resSeq + chainID;

					a = new cAtom(serial, name, altLoc, resName, chainID,
							resSeq, iCode, x, y, z);

					// if (chainID.equals("A"))
					// System.out.println("here");

					if (prev.equals("empty")) {
						atoms.add(a);
						prev = curr;
						continue;
					}
					if (prev.equals(curr)) {
						atoms.add(a);
					} else {
						AminoAcid naA = new AminoAcid(atoms);
						aa.add(naA);
						atoms = new ArrayList<cAtom>();
						// System.out.println("added " +
						// naA.getAtoms().get(0).getResSeq());
						atoms.add(a);
						prev = curr;
					}
				}
			}
			in.close();
			AminoAcid naA = new AminoAcid(atoms);
			aa.add(naA);
			// System.out.println("added " + naA.getAtoms().get(0).getResSeq());
			AminoAcid[] aminoArray = new AminoAcid[aa.size()];
			for (int i = 0; i < aa.size(); i++) {
				aminoArray[i] = aa.get(i);
			}
			return aminoArray;
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: The alignment has this to say: "
					+ e.getMessage());
		}
		return null;
	}

	public double findDouble(String s) {
		return Double.parseDouble(s.trim());
	}

	public int findInt(String s) {
		return Integer.parseInt(s.trim());
	}

	public void calculateContacts(AminoAcid[] aminoArray) {
		for (int i = 0; i < aminoArray.length - 1; i++) {
			for (int j = i + 1; j < aminoArray.length; j++) {
				double distance = aminoArray[i]
						.calculateDistance(aminoArray[j]);
				if (distance < distanceCutoff) { // we got a contact
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

//
// if (strLine.startsWith("ATOM")) {
// // int serial = findInt(strLine.substring(6, 11));
// String atomName = strLine.substring(12, 16).trim();
// String resName = strLine.substring(17, 20).trim();
// if (resName.equals("GLY")) {
// // calculate Glycine Cb position:
// // first read N coordinates
// double Nx = findDouble(strLine.substring(30, 38));
// double Ny = findDouble(strLine.substring(38, 46));
// double Nz = findDouble(strLine.substring(46, 54));
// // then read CA
// strLine = br.readLine();
// if (!strLine.contains("GLY")) {
// AminoAcid3d aa3d = new AminoAcid3d(resName, 0, 0, 0);
// aminoList.add(aa3d);
// gly++;
// continue;
// }
// double CAx = findDouble(strLine.substring(30, 38));
// double CAy = findDouble(strLine.substring(38, 46));
// double CAz = findDouble(strLine.substring(46, 54));
// // now read C
// strLine = br.readLine();
// if (!strLine.contains("GLY")) {
// AminoAcid3d aa3d = new AminoAcid3d(resName, 0, 0, 0);
// aminoList.add(aa3d);
// gly++;
// continue;
// }
// double Cx = findDouble(strLine.substring(30, 38));
// double Cy = findDouble(strLine.substring(38, 46));
// double Cz = findDouble(strLine.substring(46, 54));
// strLine = br.readLine();
//
// // now we got three vectors. The CB atom is presumed to
// // be on the same plane as CA, C and N, but like this:
// // >-
//
// double x = CAx + sqrt2 / 2 * (CAx - Nx) + sqrt2 / 2
// * (CAx - Cx);
// double y = CAy + sqrt2 / 2 * (CAy - Ny) + sqrt2 / 2
// * (CAy - Cy);
// double z = CAz + sqrt2 / 2 * (CAz - Nz) + sqrt2 / 2
// * (CAz - Cz);
// gly++;
// AminoAcid3d aa3d = new AminoAcid3d(resName, x, y, z);
// aminoList.add(aa3d);
// // if (resName.contains("GLY")) {
// // // if (!aminoList.isEmpty())
// // // if (aminoList.get(aminoList.size() - 1).getName()
// // == 5)
// // // continue;
// // if (atomName.contains("CA")) {
// // double x = findDouble(strLine.substring(30, 38));
// // double y = findDouble(strLine.substring(38, 46));
// // double z = findDouble(strLine.substring(46, 54));
// // AminoAcid3d aa3d = new AminoAcid3d(resName, x, y, z);
// // aminoList.add(aa3d);
// // }
// } else {
// if (atomName.contains("CB")) {
// double x = findDouble(strLine.substring(30, 38));
// double y = findDouble(strLine.substring(38, 46));
// double z = findDouble(strLine.substring(46, 54));
// AminoAcid3d aa3d = new AminoAcid3d(resName, x, y, z);
// aminoList.add(aa3d);
// cb++;
// }
//
// }
//
// // String altLoc = strLine.substring(16, 17).trim();
// // String resName = strLine.substring(17, 20).trim();
// // String chainID = strLine.substring(21, 22).trim();
// // int resSeq = findInt(strLine.substring(22, 26));
// // String iCode = strLine.substring(26, 27).trim();
//
// // double occupancy = findDouble(strLine.substring(54, 60));
// // double tempFactor = findDouble(strLine.substring(60,
// // 66));
// // String element = strLine.substring(76, 78).trim();
// // String charge = strLine.substring(78, 80).trim();
//
// }
