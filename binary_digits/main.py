"""
Binary digits: http://rosettacode.org/wiki/Binary_digits
自然数が与えられるので、これを2進数に変換する問題。
組込み関数binを使うと楽ちん(´・ω・｀)
"""

def binary(x):
    return bin(x)[2:]

if __name__ == '__main__':
    print(binary(5))    #=> 101
    print(binary(50))   #=> 110010
    print(binary(9000)) #=> 10001100101000

    # unit test
    assert binary(5) == '101'
    assert binary(50) == '110010'
    assert binary(9000) == '10001100101000'

