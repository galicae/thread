import resources.AminoAcids;

public class AminoAcid3d {
	private double x, y, z;
	private int name;
	private int localContact, globalContact;

	/**
	 * constructor with a string field, which must be interpreted to the
	 * corresponding int (see resources.AminoAcids). The coordinates are the
	 * coordinates of the CB atom, or the CA if the AA is a glycine
	 * 
	 * @param name
	 *            the aminoacid name
	 * @param x
	 *            self-explaining
	 * @param y
	 *            self-explaining
	 * @param z
	 *            self-explaining
	 */
	public AminoAcid3d(String name, double x, double y, double z) {
		this.name = AminoAcids.getIntRepresentation(name);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
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

	public double calculateDistance(AminoAcid3d other) {
		double xsq = this.x - other.x;
		xsq = xsq*xsq;
		
		double ysq = this.y - other.y;
		ysq = ysq*ysq;
		
		double zsq = this.z - other.z;
		zsq = zsq*zsq;
		
		return Math.sqrt(xsq + ysq + zsq);
	}
}
