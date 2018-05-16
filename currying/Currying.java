import java.util.function.Function;

/**
 * Currying: http://rosettacode.org/wiki/Currying
 * �J���[��������������Ƃ������Blambda���T�|�[�g���ꂽJava8���炷��Ɗy���ł���B
 */
public class Currying {
	public static void main(String[] args) {
		Function<Integer, Function<Integer, Integer>> curriedPlus = (x -> y -> x + y);
		System.out.println(curriedPlus.apply(1).apply(2)); //=> 3
	}
}
