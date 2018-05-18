import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Combinations: http://rosettacode.org/wiki/Combinations
 * 組み合わせを生成する問題。Streamと再帰を利用したらJavaらしからぬコードが出来上がった(´・ω・｀)
 */
public class Combinations {
	public static void main(String[] args) {
		var list = IntStream.range(0, 5).boxed().collect(Collectors.toList());
		combinations(list, 3).map(Objects::toString).forEach(System.out::println);
		// [0, 1, 2]
		// [0, 1, 3]
		// [0, 1, 4]
		// [0, 2, 3]
		// [0, 2, 4]
		// [0, 3, 4]
		// [1, 2, 3]
		// [1, 2, 4]
		// [1, 3, 4]
		// [2, 3, 4]
	}
	
	public static <T> Stream<List<T>> combinations(List<T> list, int x) {
		int len = list.size();
		if (x == 0 || list.size() == 0) return Stream.empty();
		if (x == 1) return list.stream().map(List::of);
		if (len == x) return Stream.of(list);
		
		var car = list.get(0);
		var cdr = list.subList(1, len); 
		
		var s1 = combinations(cdr, x - 1).map(combination -> unshift(car, combination));
		var s2 = combinations(cdr, x);
		return Stream.concat(s1, s2);
	}
	
	private static <T> List<T> unshift(T car, List<T> cdr) {
		var list = new ArrayList<T>();
		list.add(car);
		list.addAll(cdr);
		return list;
	}
	
	public static void test() {
		var list = List.of(1, 2, 3, 4);
		var combinations = combinations(list, 3).collect(Collectors.toList());
		assert combinations.size() == 4;
		assert combinations.contains(List.of(1, 2, 3));
		assert combinations.contains(List.of(1, 2, 4));
		assert combinations.contains(List.of(1, 3, 4));
		assert combinations.contains(List.of(2, 3, 4));
	}
}
