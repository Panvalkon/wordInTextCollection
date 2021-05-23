package prSimpleWordCountingCollections;

import java.util.Set;
import java.util.TreeSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WordCounter {
	private Set<WordInText> words;
	
	public WordCounter() {
		this.words = new TreeSet<WordInText>();
	}
	
	public void include(String word) {
		WordInText w = new WordInText(word);
		Iterator<WordInText> iter = words.iterator();
		boolean found = false;
		while (iter.hasNext() && !found) {
			WordInText wit = iter.next();
			if (wit.equals(w)) {
				found = true;
				wit.increment();				
			}
		}
		if (!found) {
			words.add(w);
		}
	}
	
	private void includeAll(String lineOfWords, String del) {
		String[] arrOfWords = lineOfWords.split(del);
		for (String word : arrOfWords) {
			if (word != null && word != "") {
				include(word);
			}
		}
	}

	public void includeAll(String[] text, String del) {
		for (String line : text) {
			includeAll(line, del);
		}
	}

	public void includeAllFromFile(String filename, String del) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File(filename))) {
			readFile(sc, del);
		}
	}
	
	private void readFile(Scanner sc, String del) {
		while (sc.hasNextLine()) {
			includeAll(sc.nextLine(), del);
		}
	}
	
	public WordInText find(String word) {
		
		throw new NoSuchElementException();
	}
	
	public String toString(){
		return "sda";
	}
}
