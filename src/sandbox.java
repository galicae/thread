import java.text.*;

public class sandbox {
	public static void main(String[] args) {
		// String s =
		// "  136        !              0   0    0      0, 0.0     0, 0.0     0, 0.0     0, 0.0   0.000 360.0 360.0 360.0 360.0    0.0    0.0    0.0";
		// StringTokenizer tk = new StringTokenizer(s);
		// tk.nextToken();
		// if(tk.nextToken().equals("!"))
		// System.err.println("error");
		// System.out.println(tk.nextToken());

		PssCalculator pss = new PssCalculator();
		short[] f = pss.loadDSSPFile(args[0]);

		AminoAcid3d[] amAr = pss.loadPDBFile(args[1]);
		pss.calculateContacts(amAr);
		String[] structure = { "a", "b", "o" };

		int newStruct = 0;
		int i = 0;
		while (i < f.length) {
			if (f[i] != 2)
				newStruct++;
			while (f[i] != 2) {
				if (i >= f.length)
					break;
				String strNewStruct = String.format("%02d", newStruct);
				System.out.println(amAr[i].getNameOneLetter() + " "
						+ structure[f[i]] + strNewStruct + "  "
						+ amAr[i].getLocalContacts() + "  "
						+ amAr[i].getGlobalContacts());
				i++;
			}
			{
				System.out.println(amAr[i].getNameOneLetter() + " "
						+ structure[f[i]] + "    " + amAr[i].getGlobalContacts()
						+ "  " + amAr[i].getLocalContacts());
				i++;
			}
		}

		// int n= 1;
		// System.out.format("%02d%n", n); // --> "00461012"
	}
}
