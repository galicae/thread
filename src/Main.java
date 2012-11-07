import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class Main {
	public static void main(String[] args) throws IOException {
		String dssp = "/home/proj/biosoft/PROTEINS/CATHSCOP/DSSP/";

		String pdb = "/home/proj/biosoft/PROTEINS/CATHSCOP/STRUCTURES";
		File pdbDir = new File(pdb);

		Collection<File> pdbFiles = FileUtils.listFiles(pdbDir,
				TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		Runner runner = new Runner();
		Iterator<File> iter = pdbFiles.iterator();
		File[] fileArray = new File[pdbFiles.size()];
		int ind = 0;

		while (iter.hasNext()) {
			fileArray[ind] = iter.next();
			ind++;
		}

		for (int i = 0; i < pdbFiles.size(); i++) {
			String dsFile = FilenameUtils.removeExtension(fileArray[i]
					.getName());
			String resultString = runner.run(dssp + dsFile + ".dssp",
					fileArray[i].getAbsolutePath());
			System.out.println(i);
			File resultFile = new File("sscc/" + dsFile + ".sscc");
			FileUtils.writeStringToFile(resultFile, resultString);
		}
	}
}
