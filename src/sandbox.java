import resources.AminoAcid;

public class sandbox {
	public static void main(String[] args) {
		
		String dssp = args[0];
		String pdb = args[1];
		GeneralReader pss = new GeneralReader();
		short[] f = pss.loadDSSPFile(dssp);

		StringBuilder result = new StringBuilder();
		AminoAcid[] amAr = pss.loadPDBFile(pdb);
		pss.calculateContacts(amAr);
		String[] structure = { "a", "b", "o" };

		int newStruct = 0;
		int i = 0;
		while (i < f.length) {
			if (f[i] != 2)
				newStruct++;
			int curr = f[i];
			while (f[i] != 2 && f[i] == curr) {
				if (i >= f.length)
					break;
				String strNewStruct = String.format("%02d", newStruct);
				result.append((amAr[i].getNameOneLetter() + " "
						+ structure[f[i]] + strNewStruct + "  "
						+ amAr[i].getLocalContacts() + "  " + amAr[i]
							.getGlobalContacts()) + "\n");
				i++;
			}
			if (f[i] != curr)
				continue;
			result.append(amAr[i].getNameOneLetter() + " " + structure[f[i]]
					+ "    " + amAr[i].getLocalContacts() + "  "
					+ amAr[i].getGlobalContacts() + "\n");
			i++;

		}

		System.out.println(result.toString());
	}
}
