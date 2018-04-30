#!/bin/bash

# Proper divisors: http://rosettacode.org/wiki/Proper_divisors
# 約数に関する問題。以下の3つのタスクを満たすこと。
# 1. 約数を列挙するルーチンを作成する
# 2. 1-10の約数をそれぞれ列挙する。
# 3. 1-20000のうち、約数の個数が最も多い整数を求める。
# 1.2.はbashで解答(bashらしいかどうかはさておき)
# 3.はawkに逃げてしまった(´・ω・｀)

proper_divisor() {
  [[ $1 -eq 1 ]] && return 0

  n=$1
  root=$(echo "sqrt(${n})" | bc)

  echo 1
  seq 2 $((${root} - 1)) | while read x; do
    [[ $(( ${n} % ${x} )) -eq 0 ]] && printf "%d\n%d\n" ${x} $((${n} / ${x}))
  done
  [[ $((${n} % ${root})) -eq 0 ]] && echo ${root}
}

seq 10 | while read n; do
  echo "${n}: $(proper_divisor ${n} | paste -sd' ')"
done
# 1:
# 2: 1 1
# 3: 1 1
# 4: 1 2
# 5: 1
# 6: 1 2
# 7: 1
# 8: 1 2
# 9: 1 3
# 10: 1 2 5

seq 20000 | awk '
  function count_propers(n,     root, count, i) {
    if (n == 1) return 0;

    root = sqrt(n)
    count = 1
    for (i = 2; i < root; i++) {
      if (n % i == 0) count += 2;
    }
    if (n % root == 0) count += 1;

    return count
  }

  {
    count = count_propers($0)
    if (max < count) max = count;
    digits[count] = digits[count] " " $0
  }

  END {
    print max ":" digits[max]
  }
' 
# 79: 15120 18480



