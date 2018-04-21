"""
Count the coins: http://rosettacode.org/wiki/Count_the_coins
1. ¢1, ¢5, ¢10, ¢25を組み合わせて$1(=¢100)を作る。その組み合わせの総数は?
2. 1.で利用できるコインに¢50,¢100を加えて、$1000(=¢1000,000)を作る。その組み合わせの総数は?
メモ化再帰で解きたかったのだが、動的計画法に逃げてしまった(´・ω・｀)
"""

import itertools

def solve(coins, money):
    ways = list(itertools.repeat(0, money + 1))
    ways[0] = 1

    for coin in coins:
        for idx in range(money - coin + 1):
            ways[idx + coin] += ways[idx]

    return ways[money]

if __name__ == '__main__':
    print(solve([25, 10, 5, 1], 1 * 100)) #=> 242
    print(solve([100, 50, 25, 10, 5, 1], 1000 * 100)) #=> 13398445413854501
