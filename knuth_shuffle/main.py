"""
Knuth shuffle: http://rosettacode.org/wiki/Knuth_shuffle
クヌースのシャッフル(フィッシャー–イェーツのシャッフル)を実装する。
シャッフルアルゴリズムにおいては「偏り」がないことがとりわけ重要であるから、
モンテカルロ法により「偏り」がないことも同時に検証した。
"""

import random, collections

def shuffle(lst):
    length = len(lst)
    for x in range(length):
        y = random.randrange(x, length)
        lst[x], lst[y] = lst[y], lst[x]

if __name__ == '__main__':
    table = collections.defaultdict(int)
    for _ in range(100000):
        lst = [10, 20, 30]
        shuffle(lst)
        table[tuple(lst)] += 1
    
    for tpl, count in table.items():
        print(tpl, count)
    
    # (30, 20, 10) 16427
    # (20, 30, 10) 16801
    # (20, 10, 30) 16682
    # (10, 20, 30) 16757
    # (30, 10, 20) 16594
    # (10, 30, 20) 16739

