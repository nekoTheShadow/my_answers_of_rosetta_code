import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Pig the dice game: http://rosettacode.org/wiki/Pig_the_dice_game
 * "Pig"�Ƃ����Q�[��������������B���[���͂����悻�ȉ��̒ʂ�B
 * 1. �v���C���[�͎����̎�Ԃɂ����āARoll��������Hold��I������B
 * 2. Roll��I�������ꍇ�A6���΂�U��B���̖ڂ�2-6�̏ꍇ�A���̎�Ԃ̓_���ɉ��Z����B�U�����ڂ�1�̏ꍇ�́A�����̎�Ԃ͏I���B
 * 3. Hold��I�������ꍇ�A����܂Œ��߂Ă����u��Ԃ̓_���v�������̓_���ɉ��Z����
 * 4. �Q���v���C���[�͎�Ԃ���サ�Ȃ���A1-3.���J��Ԃ��A��Ɏ����̓_����100�𒴂����v���C���[�̏����Ƃ���B
 *�@
 * ���̎w��ɂ͂Ȃ����A���[�U������ł���v���C���[�ƁA�ȒP�Ȏv�l���[�`����������CPU�����������B
 * ���[�����P���A�������ȒP�����A���ۂɗV��ł݂�ƁA�����[���Ƃ������A�Ȃ��Ȃ��M�����u�����������Q�[���ł͂���B
 * �����̃R�c�͈����ۂ��Ƃ������Ƃ��悭�킩��(�L�E�ցE�M)
 */
public class Pig {
	public static void main(String[] args) {
		var queue = new LinkedList<Player>();
		queue.add(new CPU());
		queue.add(new User());
		var dice = new Dice();
		
		while (true) {
			Player player = queue.removeFirst();
			int pooled = 0;
			print("=== START: %s ===", player.getName());
			
			while (true) {
				print("%s=%d, pooled=%d", player.getName(), player.getPoint(), pooled);
				if (player.isHold(pooled)) {
					print("%s choose to hold!", player.getName());
					break;
				}
				
				int value = dice.roll();
				print("dice ==> %d", value);
				if (value == 1) {
					print("this turn's point is flushed");
					pooled = 0;
					break;
				} else {
					pooled += value;
				}
			}
			player.add(pooled);
			queue.addLast(player);
			
			print("%s=%d", player.getName(), player.getPoint());
			if (player.getPoint() >= 100) {
				print("%s win!!!", player.getName());
				break;
			}
			
			print("=== END: %s ===", player.getName());
		}
	}
	
	private static void print(String format, Object ... args) {
		String line = String.format(format, args);
		System.out.println(line);
	}
}

class Dice {
	private Random random = new Random();
	
	public int roll() {
		return random.nextInt(6) + 1;
	}
}

abstract class Player {
	public abstract boolean isHold(int pooled);
	public abstract String getName();
	
	private int point = 0;
	
	public void add(int pooled) {
		point += pooled;
	}
	
	public int getPoint() {
		return point;
	}
}

/**
 * �R���s���[�^�����삷��Player. �����̓_���Ə�̓_���̍��v��30�𒴂���܂��΂�U�葱����B
 */
class CPU extends Player {
	@Override
	public boolean isHold(int pooled) {
		if (pooled + getPoint() >= 100) return true;
		return pooled >=  30;
	}

	@Override
	public String getName() {
		return "CPU";
	}
}

/**
 *�@�l�Ԃ��R���g���[�����郆�[�U�B�R���\�[������̓��͂��󂯕t���āA�΂�U�邩�ǂ�����I������B
 */
class User extends Player {	
	private Scanner stdin = new Scanner(System.in);
	
	@Override
	public boolean isHold(int pooled) {
		System.out.println("Do you hold? (y/n)");
		
		var answer = stdin.nextLine();
		return answer.toLowerCase().equals("y") ? true : false;
	}

	@Override
	public String getName() {
		return "You";
	}
}