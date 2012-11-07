package resources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SuppressWarnings("rawtypes")
public class AminoAcid implements Comparable {
	private double x, y, z;
	private int name;
	private int localContact, globalContact;

	private static final double sqrt2 = Math.sqrt(2);

	private List<cAtom> atoms = new ArrayList<cAtom>();

	public AminoAcid(List<cAtom> atoms) {
		this.atoms = atoms;
		this.name = AminoAcids.getIntRepresentation(atoms.get(0).resName);

//		if (name.equals("GLY")) {
////			calculate CB coordinates
//			
//		} else {

			boolean CA = false, CB = false, C = false, elseAt = false;
			for (cAtom a : atoms) {
				elseAt = true;
				if (a.name.equals("CB"))
					CB = true;
				if (a.name.equals("CA"))
					CA = true;
				if (a.name.contains("C"))
					C = true;
			}

			if (CB) {
				for (cAtom a : atoms) {
					if (a.name.equals("CB")) {
						x = a.getX();
						y = a.getY();
						z = a.getZ();
						break;
					}
				}
			} else if (CA) {
				for (cAtom a : atoms) {
					if (a.name.equals("CA")) {
						x = a.getX();
						y = a.getY();
						z = a.getZ();
						break;
					}
				}
			} else if (C) {
				for (cAtom a : atoms) {
					if (a.name.contains("C")) {
						x = a.getX();
						y = a.getY();
						z = a.getZ();
						break;
					}
				}
			} else if (elseAt) {
				cAtom a = atoms.get(0);
				x = a.getX();
				y = a.getY();
				z = a.getZ();
			} else {
				x = 0;
				y = 0;
				z = 0;
			}
//		}
	}

	public List<cAtom> getAtoms() {
		// ArrayList<Atom> atomList = new ArrayList<Atom>(atoms.values());
		return atoms;
	}

	public void setAtoms(ArrayList<cAtom> atoms) {
		this.atoms = atoms;
	}

	public int getName() {
		return this.name;
	}

	@Override
	public int compareTo(Object o) {
		if ((name) < ((AminoAcid) o).getName())
			return -1;
		else if ((name) > ((AminoAcid) o).getName())
			return 1;
		else
			return 0;
	}

	public double calculateDistance(AminoAcid other) {
		double xsq = this.x - other.x;
		xsq = xsq * xsq;

		double ysq = this.y - other.y;
		ysq = ysq * ysq;

		double zsq = this.z - other.z;
		zsq = zsq * zsq;

		return Math.sqrt(xsq + ysq + zsq);
	}

	public String getNameOneLetter() {
		return AminoAcids.reverse[name];
	}

	public void addLocalContact() {
		localContact++;
	}

	public void addGlobalContact() {
		globalContact++;
	}

	public int getLocalContacts() {
		return localContact;
	}

	public int getGlobalContacts() {
		return globalContact;
	}

}
