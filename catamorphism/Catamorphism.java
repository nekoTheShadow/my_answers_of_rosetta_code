import java.util.stream.IntStream;

/**
 * Catamorphism: http://rosettacode.org/wiki/Catamorphism
 * �J�^���[�t�B�Y���̊T�O�𗝉����邱�Ƃ���|�̖��B�v��fold�̂��Ƃ�Java8�ł�reduce�Ƃ��Ď�������Ă���B
 */
public class Catamorphism {
	public static void main(String[] args) {
		var total = IntStream.of(1, 2, 3, 4, 5).reduce(0, (sum, i) -> sum + i);
		System.out.println(total);
	}
}
