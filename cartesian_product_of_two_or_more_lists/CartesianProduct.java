import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Cartesian product of two or more lists: http://rosettacode.org/wiki/Cartesian_product_of_two_or_more_lists
 * 2�ȏ�̃��X�g�������ɂƂ��āA�f�J���g�ς����߂���B����͈���2�ł�3�ł��쐬���A���������Ȃ̂Ŏ��ۓI�ȃA���S���Y����ς��Ă݂��B
 * J�Ȃ�ava�ɂ�3�ȏ�̈������Ƃ郉���_�����p�ӂ���Ă��Ȃ��̂ŁA���삵���B
 */
public class CartesianProduct {

	public static void main(String[] args) {
		prodct(List.of(1, 2), List.of("A", "B"), (x, y) -> { 
			return String.format("%d-%s", x, y);
		}).forEach(System.out::println);
		// 1-A
		// 1-B
		// 2-A
		// 2-B

		product(List.of(1, 2), List.of("A", "B"), List.of(3, 4), (x, y, z) -> {
			return String.format("%d-%s-%d", x, y, z);
		}).forEach(System.out::println);
		// 1-A-3
		// 1-A-4
		// 1-B-3
		// 1-B-4
		// 2-A-3
		// 2-A-4
		// 2-B-3
		// 2-B-4
	}
	
	public static <T, U, R> Stream<R> prodct(List<T> ts, List<U> us, BiFunction<T, U, R> function) {
		return ts.stream().flatMap(t -> us.stream().map(u -> function.apply(t, u)));
	}
	
	public static <A, B, C, R> Stream<R> product(List<A> as, List<B> bs, List<C> cs, TriFunction<A, B, C, R> function) {
		Stream.Builder<R> builder = Stream.builder();
		for (var a : as) {
			for (var b : bs) {
				for (var c : cs) {
					builder.accept(function.apply(a, b, c));
				}
			}
		}
		return builder.build();		
	}
	
	@FunctionalInterface
	interface TriFunction<A, B, C, R> {
	    R apply(A a, B b, C c);
	}
	
	/* ========== �e�X�g ========== */
	
	// 2�̃f�J���g��; �ʏ�P�[�X�B
	private static void test1() {
		var ts = List.of("A", "B");
		var us = List.of(1, 2);
		var actual = prodct(ts, us, (t, u) -> String.format("%s%d", t, u)).collect(Collectors.toList());
		var expected = Arrays.asList("A1", "A2", "B1", "B2");
		assert actual.equals(expected);
	}
	
	// 2�̃f�J���g��; �������Е��͋�B
	private static void test2() {
		var ts = List.of("A", "B");
		var us = Collections.emptyList();
		var actual = prodct(ts, us, (t, u) -> null).collect(Collectors.toList());
		var expected = Collections.emptyList();
		assert actual.equals(expected);
	}
	
	// 3�̃f�J���g��; �ʏ�P�[�X�B
	private static void test3() {
		var as = List.of(1, 2);
		var bs = List.of("A");
		var cs = List.of(3.0, 4.0);
		var actual = product(as, bs, cs, (a, b, c) -> String.format("%d-%s-%.2f", a, b, c)).collect(Collectors.toList());
		var excepted = List.of("1-A-3.00", "1-A-4.00", "2-A-3.00", "2-A-4.00");
		assert actual.equals(excepted);
	}
}
