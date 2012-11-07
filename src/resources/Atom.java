package resources;

public class Atom {
	private String name;
	private String resName;
	private String chainID;
	private int resSeq;
	private String iCode;
	private double x;
	private double y;
	private double z;

	public Atom(String name, String resName, String chainID, int resSeq,
			String iCode, double x, double y, double z) {
		super();
		this.name = name;
		this.resName = resName;
		this.chainID = chainID;
		this.resSeq = resSeq;
		this.iCode = iCode;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getChainID() {
		return chainID;
	}

	public void setChainID(String chainID) {
		this.chainID = chainID;
	}

	public int getResSeq() {
		return resSeq;
	}

	public void setResSeq(int resSeq) {
		this.resSeq = resSeq;
	}

	public String getiCode() {
		return iCode;
	}

	public void setiCode(String iCode) {
		this.iCode = iCode;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

}
