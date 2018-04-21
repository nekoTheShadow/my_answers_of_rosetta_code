"""
Ordered words: https://rosettacode.org/wiki/Ordered_words
たとえばabbeyやdirtyのように、単語それ自体がアルファベット順にソートされているかどうかを判定する。
問題としては、与えられた単語群から、上記の条件を満たすものを探し、
なかでも単語長が最も長いものを列挙することが求められている。
"""

import urllib.request

if __name__ == '__main__':
    ordered_words = []
    with urllib.request.urlopen('http://www.puzzlers.org/pub/wordlists/unixdict.txt') as response:
        body = response.read().decode("utf-8")
        for word in body.split('\n'):
            if ''.join(sorted(word)) == word:
                ordered_words.append(word)

    max_len = max(map(len, ordered_words))
    for word in ordered_words:
        if len(word) == max_len:
            print(word)
    
    # abbott
    # accent
    # accept
    # access
    # accost
    # almost
    # bellow
    # billow
    # biopsy
    # chilly
    # choosy
    # choppy
    # effort
    # floppy
    # glossy
    # knotty  
    # 