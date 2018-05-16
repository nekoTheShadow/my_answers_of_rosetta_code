"""
Babbage problem: http://rosettacode.org/wiki/Babbage_problem
269,696で終わる自然数のうち、最小の平方数は何か?
チャールズ・バベッジはこの問題の答えを9,947,269,696(=99,736^2)と予想した。
さてこの予想は正しいだろうか? 君の眼で確かめてみよう --というのが本問の趣旨。
===
端的に述べると、彼の予想は間違っている。答えは638269696=25264^2。
平方数判定はもう少しエレガントに書きたかった(´・ω・｀)
"""

import math

def is_square(x):
    sqrt = int(math.sqrt(x))
    return x == sqrt * sqrt


def main():
    x = 269696
    while not is_square(x):
        x += 1000000
    print(x, math.sqrt(x)) #=> 638269696 25264.0


def test():
    assert is_square(100)
    assert not is_square(99)

if __name__ == '__main__':
    test()
    main()