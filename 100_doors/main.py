"""
100 doors: http://www.rosettacode.org/wiki/100_doors
  0. 1~100の名前が付いた100個のドアがあり、最初はすべて閉じている。
  1. 1の倍数のドアの状態をすべて変更する(閉じている場合は開き、開いている場合は閉じる)。
  2. 2の倍数のドアの状態をすべて変更する。
  3. 3の倍数のドアの状態をすべて変更する。
                 (中略)
100. 100の倍数のドアの状態をすべて変更する。
"""

if __name__ == '__main__':
    size = 100
    is_open = [False for _ in range(size + 1)]
    for n in range(1, size + 1):
        for i in range(n, size + 1, n):
            is_open[i] = not is_open[i]
    
    print(*(i for i in range(1, size + 1) if is_open[i]))
    # => 1 4 9 16 25 36 49 64 81 100
    