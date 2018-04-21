package rosetta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Move-to-front algorithm: https://rosettacode.org/wiki/Move-to-front_algorithm
 * 問題の指定にしたがってMTFを実装するだけ。
 * String.charsがStream<Character>ではなくIntStreamを返すせいでえらい目にあった(´・ω・｀)
 */
public class MoveToFrontAlgorithm {
	public static void main(String[] args) {
		test();
		System.out.println(encode("broood"));
		System.out.println(decode(Arrays.asList(1, 17, 15, 0, 0, 5)));
		// [1, 17, 15, 0, 0, 5]
		// broood
	}
	
	private static void test() {
		assert encode("broood").equals(Arrays.asList(1, 17, 15, 0, 0, 5)) : "fail to encode";
		assert decode(Arrays.asList(1, 17, 15, 0, 0, 5)).equals("broood") : "fail to decode";
	}
	
	private static List<Integer> encode(String str) {
		var symbolTable = getSymbolicTable();
		var idxs = new ArrayList<Integer>();
		for (char ch : str.toCharArray()) {
			int idx = symbolTable.indexOf(ch);
			idxs.add(idx);
			symbolTable.add(0, symbolTable.remove(idx));
		}
		return idxs;
	}
	
	private static String decode(List<Integer> idxs) {
		var symbolTable = getSymbolicTable();
		var str = new StringBuilder();
		idxs.stream().forEach(idx -> {
			var ch = symbolTable.remove(idx.intValue());
			symbolTable.add(0, ch);
			str.append(ch);
		});
		return str.toString();
	}
	
	private static List<Character> getSymbolicTable() {
		List<Character> chars = new ArrayList<>();
		for (char ch : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
			chars.add(ch);
		}
		return chars;
	}
}
