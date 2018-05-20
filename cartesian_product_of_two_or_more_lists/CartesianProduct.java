import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Cartesian product of two or more lists: http://rosettacode.org/wiki/Cartesian_product_of_two_or_more_lists
 * 2つ以上のリストを引数にとって、デカルト積を求める問題。今回は引数2つ版と3つ版を作成し、せっかくなので実際的なアルゴリズムを変えてみた。
 * Jなおavaには3つ以上の引数をとるラムダ式が用意されていないので、自作した。
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
	
	/* ========== テスト ========== */
	
	// 2つのデカルト積; 通常ケース。
	private static void test1() {
		var ts = List.of("A", "B");
		var us = List.of(1, 2);
		var actual = prodct(ts, us, (t, u) -> String.format("%s%d", t, u)).collect(Collectors.toList());
		var expected = Arrays.asList("A1", "A2", "B1", "B2");
		assert actual.equals(expected);
	}
	
	// 2つのデカルト積; ただし片方は空。
	private static void test2() {
		var ts = List.of("A", "B");
		var us = Collections.emptyList();
		var actual = prodct(ts, us, (t, u) -> null).collect(Collectors.toList());
		var expected = Collections.emptyList();
		assert actual.equals(expected);
	}
	
	// 3つのデカルト積; 通常ケース。
	private static void test3() {
		var as = List.of(1, 2);
		var bs = List.of("A");
		var cs = List.of(3.0, 4.0);
		var actual = product(as, bs, cs, (a, b, c) -> String.format("%d-%s-%.2f", a, b, c)).collect(Collectors.toList());
		var excepted = List.of("1-A-3.00", "1-A-4.00", "2-A-3.00", "2-A-4.00");
		assert actual.equals(excepted);
	}
}
