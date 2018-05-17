import java.util.stream.IntStream;

/**
 * Catamorphism: http://rosettacode.org/wiki/Catamorphism
 * カタモーフィズムの概念を理解することが趣旨の問題。要はfoldのことでJava8ではreduceとして実装されている。
 */
public class Catamorphism {
	public static void main(String[] args) {
		var total = IntStream.of(1, 2, 3, 4, 5).reduce(0, (sum, i) -> sum + i);
		System.out.println(total);
	}
}
