package prSimpleWordCountingCollections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordCounterSig extends WordCounter {
	private Set<String> notSignificant;

	public WordCounterSig(Collection<String> noSig) {
		super();
		this.notSignificant = new TreeSet<String>(noSig);
	}

	public WordCounterSig(String nonSigFile, String del) throws FileNotFoundException {
		super();
		notSignificant = new TreeSet<String>();
		readNonSigFile(nonSigFile, del);
	}

	@Override
	protected void include(String word) {
		boolean found = false;
		for (String s : notSignificant) {
			if (s.equalsIgnoreCase(word)) {
				found = true;
			}
		}
		if (!found) {
			super.include(word);
		}
	}

	private void readNonSigFile(String nonSigFile, String del) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File(nonSigFile))) {
			readNonSigWords(sc, del);
		}
	}

	private void readNonSigWords(Scanner sc, String del) {
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			readNonSigWords(line, del);
		}
	}

	private void readNonSigWords(String line, String del) {
		try (Scanner sc = new Scanner(line)) {
			sc.useLocale(Locale.ENGLISH);
			sc.useDelimiter(del);
			while (sc.hasNext()) {
				String s = sc.next();
				if (s != null && s != "")
					this.notSignificant.add(s.toUpperCase());
			}
		}
	}
}
