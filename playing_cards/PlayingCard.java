import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Playing cards: http://rosettacode.org/wiki/Playing_cards 
 * いわゆる"トランプ"を実装する問題。このとき以下の4機能を満たすこと。
 * - 52種のカードからなる山札
 * - 山札をシャッフルする
 * - 山札から1枚抜き取る
 * - 山札の内容を表示する
 */
public class PlayingCard {
	public static void main(String[] args) {
		var deck = new Deck();
		System.out.println(deck);
		//=> ❤02 ♠07 ♣03 ♣08 ♠10 ♦12 ♣11 ♠05 ♠02 ♦09 ♣04 ♣09 ♠01 ❤11 ♠09 ❤06 ❤13 ♣01 ♦04 ❤08 ♠03 ♦03 ♦13 ♠12 ❤07 ❤12 ❤10 ♦08 ♦10 ♣12 ♦01 ♦06 ❤01 ♣10 ♣05 ♠08 ♦05 ❤04 ♦02 ❤05 ♦07 ♣06 ♣02 ♠04 ♠13 ♣13 ♦11 ❤03 ❤09 ♠11 ♠06 ♣07
		
		var card1 = deck.deal();
		System.out.println(card1);
		System.out.println(deck);
		//=> ❤02
		//=> ♠07 ♣03 ♣08 ♠10 ♦12 ♣11 ♠05 ♠02 ♦09 ♣04 ♣09 ♠01 ❤11 ♠09 ❤06 ❤13 ♣01 ♦04 ❤08 ♠03 ♦03 ♦13 ♠12 ❤07 ❤12 ❤10 ♦08 ♦10 ♣12 ♦01 ♦06 ❤01 ♣10 ♣05 ♠08 ♦05 ❤04 ♦02 ❤05 ♦07 ♣06 ♣02 ♠04 ♠13 ♣13 ♦11 ❤03 ❤09 ♠11 ♠06 ♣07

		deck.shuffle();
		System.out.println(deck);
		//=> ♠08 ♠04 ♦09 ♣02 ❤10 ♣10 ♦12 ♦08 ♦07 ♣09 ♠02 ❤08 ♣03 ❤06 ❤04 ♦05 ♠12 ❤01 ♣01 ❤05 ♣12 ♣08 ♦04 ♠13 ♣11 ♠06 ♣13 ♠03 ♣06 ♦13 ❤11 ♠10 ♦10 ❤09 ♦06 ♣05 ♠09 ♠05 ♦01 ♠11 ❤03 ♣07 ❤12 ♦11 ❤07 ♣04 ❤13 ♠07 ♦02 ♦03 ♠01
	
		var card2 = deck.deal();
		System.out.println(card2);
		System.out.println(deck);
		//=> ♠08
		//=> ♠04 ♦09 ♣02 ❤10 ♣10 ♦12 ♦08 ♦07 ♣09 ♠02 ❤08 ♣03 ❤06 ❤04 ♦05 ♠12 ❤01 ♣01 ❤05 ♣12 ♣08 ♦04 ♠13 ♣11 ♠06 ♣13 ♠03 ♣06 ♦13 ❤11 ♠10 ♦10 ❤09 ♦06 ♣05 ♠09 ♠05 ♦01 ♠11 ❤03 ♣07 ❤12 ♦11 ❤07 ♣04 ❤13 ♠07 ♦02 ♦03 ♠01
	}
}

class Deck {
	private LinkedList<Card> cards = new LinkedList<>();
	
	public Deck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
		this.shuffle();
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Card deal() {
		return cards.removeFirst();
	}
	
	@Override
	public String toString() {
		return cards.stream().map(card -> card.toString()).collect(Collectors.joining(" "));
	}
}


class Card {
	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return String.format("%s%02d", suit.toSymbol(), rank.intValue());
	}
	
}

enum Suit {
	HEART("❤"), 
	DIAMOND("♦"),
	SPADE("♠"),
	CLUB("♣");
	
	private String symbol;
	
	private Suit(String symbol) {
		this.symbol = symbol;
	}
	
	public String toSymbol() {
		return this.symbol;
	}
}

enum Rank {
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13);
	
	private int x;
	
	private Rank(int x) {
		this.x = x;
	}
	
	public int intValue() {
		return x;
	}
}
