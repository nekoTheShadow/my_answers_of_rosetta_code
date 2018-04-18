package rosetta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ���̃^�C�g���̒ʂ�Sleep Sort���������Ȃ����Ƃ������B
 * �}���`�X���b�h�������̓}���`�v���Z�X�𗘗p����̂��悳���B 
 */
public class SleepSort {
	public static void main(String[] args) {
		var before = IntStream.range(1, 10).boxed().collect(Collectors.toList());
		Collections.shuffle(before);
		
		var after = new CopyOnWriteArrayList<Integer>();
		var threads = new ArrayList<Thread>();
		for (int digit : before) {
			var thread = new Thread() {
				public void run() {
					try {
						Thread.sleep((long) digit * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					after.add(digit);
				}
			};
			thread.start();
			threads.add(thread);
		}
		

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(before);
		System.out.println(after);
		// [6, 5, 7, 9, 4, 1, 8, 2, 3]
		// [1, 2, 3, 4, 5, 6, 7, 8, 9]
	}
}
