import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
Semordnilap: https://rosettacode.org/wiki/Semordnilap
前から読んでも後ろから読んでも意味が通じる単語を"Semordnilap"というらしく、
与えられた単語群からこの"Semordnilap"を探し、5つ例示することが問題の意図。
実をいうと、Java7しか動かない環境でこのコードを書いたので、まったくモダンな感じのないJavaになってしまった(´・ω・｀)
*/
public class Main {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.puzzlers.org/pub/wordlists/unixdict.txt");
		URLConnection connection = url.openConnection();
		Set<String> words = new HashSet<>();
		List<String> answers = new ArrayList<>();
		try (BufferedReader body = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String word;
			while ((word = body.readLine()) != null) {
				if (words.contains(reversed(word))) {
					answers.add(word);
				}
				words.add(word);
			}
		}

		System.out.printf("find %s pairs %n", answers.size());
		System.out.println("--- 5 examples ---");
		for (int i = 0; i < 5; i++) {
			String word = answers.get(i);
			System.out.printf("%s - %s %n", word, reversed(word));
		}

		// find 158 pairs
		// --- 5 examples ---
		// ca - ac
		// dab - bad
		// diva - avid
		// dna - and
		// drab - bard
	}

	public static String reversed(String word) {
		return new StringBuilder(word).reverse().toString();
	}
}
