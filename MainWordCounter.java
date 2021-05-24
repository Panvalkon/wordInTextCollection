
import java.io.FileNotFoundException;

import prSimpleWordCountingCollections.WordCounter;

public class MainWordCounter {

	public static void main(String[] args) {
		String[] data = { "Esta es la primera frase del ejemplo", "y esta es la segunda frase" };
		WordCounter wc = new WordCounter();
		try {
			wc.includeAllFromFile("quijote.txt", "[ .,;:]+");
		} catch (FileNotFoundException e) {
			System.out.println("File is not found");
		}
		System.out.println(wc.toString());

		WordCounter wc2 = new WordCounter();
		wc2.includeAll(data, "[ ]");
		System.out.println(wc2.toString());

		try {
			System.out.println("Startring print write here");
			wc.presentWords("out.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
