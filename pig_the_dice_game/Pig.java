import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Pig the dice game: http://rosettacode.org/wiki/Pig_the_dice_game
 * "Pig"というゲームを実装する問題。ルールはおおよそ以下の通り。
 * 1. プレイヤーは自分の手番において、RollもしくはHoldを選択する。
 * 2. Rollを選択した場合、6目賽を振る。この目が2-6の場合、その手番の点数に加算する。振った目が1の場合は、自分の手番は終了。
 * 3. Holdを選択した場合、それまで貯めてきた「手番の点数」を自分の点数に加算する
 * 4. 参加プレイヤーは手番を交代しながら、1-3.を繰り返し、先に自分の点数が100を超えたプレイヤーの勝利とする。
 *　
 * 問題の指定にはないが、ユーザが操作できるプレイヤーと、簡単な思考ルーチンを持ったCPUを実装した。
 * ルールも単純、実装も簡単だが、実際に遊んでみると、奥が深いというか、なかなかギャンブル性が高いゲームではある。
 * 勝負のコツは引き際だということがよくわかる(´・ω・｀)
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
 * コンピュータが操作するPlayer. 自分の点数と場の点数の合計が30を超えるまで賽を振り続ける。
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
 *　人間がコントロールするユーザ。コンソールからの入力を受け付けて、賽を振るかどうかを選択する。
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