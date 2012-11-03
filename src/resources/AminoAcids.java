package resources;

public final class AminoAcids {

	// three letter code
	public static int ala = 0;
	public static int cys = 1;
	public static int asp = 2;
	public static int glu = 3;
	public static int phe = 4;
	public static int gly = 5;
	public static int his = 6;
	public static int ile = 7;
	public static int lys = 8;
	public static int leu = 9;
	public static int met = 10;
	public static int asn = 11;
	public static int pro = 12;
	public static int gln = 13;
	public static int arg = 14;
	public static int ser = 15;
	public static int thr = 16;
	public static int val = 17;
	public static int trp = 18;
	public static int tyr = 19;

	// one letter code
	public static int A = 0;
	public static int C = 1;
	public static int D = 2;
	public static int E = 3;
	public static int F = 4;
	public static int G = 5;
	public static int H = 6;
	public static int I = 7;
	public static int K = 8;
	public static int L = 9;
	public static int M = 10;
	public static int N = 11;
	public static int P = 12;
	public static int Q = 13;
	public static int R = 14;
	public static int S = 15;
	public static int T = 16;
	public static int V = 17;
	public static int W = 18;
	public static int Y = 19;

	/**
	 * kinda bad function to make string representation of aminoacid to int
	 * 
	 * @param s
	 *            string representation of aminoacid
	 * @return -1 if input is somehow wrong, the int code of the aminoacid in
	 *         the normal scenario
	 */
	public static int getIntRepresentation(String s) {
		if (s.equals("A")) {
			return A;
		} else if (s.equals("C")) {
			return C;
		} else if (s.equals("D")) {
			return D;
		} else if (s.equals("E")) {
			return E;
		} else if (s.equals("F")) {
			return F;
		} else if (s.equals("G")) {
			return G;
		} else if (s.equals("H")) {
			return H;
		} else if (s.equals("I")) {
			return I;
		} else if (s.equals("K")) {
			return K;
		} else if (s.equals("L")) {
			return L;
		} else if (s.equals("M")) {
			return M;
		} else if (s.equals("N")) {
			return N;
		} else if (s.equals("P")) {
			return P;
		} else if (s.equals("Q")) {
			return Q;
		} else if (s.equals("R")) {
			return R;
		} else if (s.equals("S")) {
			return S;
		} else if (s.equals("T")) {
			return T;
		} else if (s.equals("V")) {
			return V;
		} else if (s.equals("W")) {
			return W;
		} else if (s.equals("Y")) {
			return Y;
		} else if (s.equals("ala")) {
			return A;
		} else if (s.equals("cys")) {
			return C;
		} else if (s.equals("asp")) {
			return D;
		} else if (s.equals("glu")) {
			return E;
		} else if (s.equals("phe")) {
			return F;
		} else if (s.equals("gly")) {
			return G;
		} else if (s.equals("his")) {
			return H;
		} else if (s.equals("ile")) {
			return I;
		} else if (s.equals("lys")) {
			return K;
		} else if (s.equals("leu")) {
			return L;
		} else if (s.equals("met")) {
			return M;
		} else if (s.equals("asn")) {
			return N;
		} else if (s.equals("pro")) {
			return P;
		} else if (s.equals("gln")) {
			return Q;
		} else if (s.equals("arg")) {
			return R;
		} else if (s.equals("ser")) {
			return S;
		} else if (s.equals("thr")) {
			return T;
		} else if (s.equals("val")) {
			return V;
		} else if (s.equals("trp")) {
			return W;
		} else if (s.equals("tyr")) {
			return Y;
		} else
			return -1;
	}
}
