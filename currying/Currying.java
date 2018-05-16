import java.util.function.Function;

/**
 * Currying: http://rosettacode.org/wiki/Currying
 * カリー化を実装化せよという問題。lambdaがサポートされたJava8からすると楽勝である。
 */
public class Currying {
	public static void main(String[] args) {
		Function<Integer, Function<Integer, Integer>> curriedPlus = (x -> y -> x + y);
		System.out.println(curriedPlus.apply(1).apply(2)); //=> 3
	}
}
