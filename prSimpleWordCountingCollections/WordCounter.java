package prSimpleWordCountingCollections;

import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WordCounter {
	private Set<WordInText> words;
	
	public WordCounter() {
		this.words = new TreeSet<WordInText>();
	}
	
	protected void include(String word) {
		try {
			WordInText wit = find(word);
			wit.increment();
		} catch (NoSuchElementException e) {
			words.add(new WordInText(word));
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
		WordInText w = new WordInText(word);
		Iterator<WordInText> iter = words.iterator();		
		boolean found = false;
		while (iter.hasNext() && !found) {
			WordInText wit = iter.next();
			if (w.equals(wit)) {
				found = true;
				w = wit;
				}
		}		
		if(!found) {
			throw new NoSuchElementException();
		}
		return w;
	}
	
	public String toString(){
		StringJoiner sj = new StringJoiner(", ", "[", "]");
		for (WordInText w : words) {
			sj.add(w.toString());
		}
		return String.valueOf(sj);
	}
	
	public void presentWords(String file) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(file);
		presentWords(pw);
	}
	
	public void presentWords(PrintWriter pw) {
		for(WordInText w : words) {
			pw.println(w.toString());
		}
	}
}
