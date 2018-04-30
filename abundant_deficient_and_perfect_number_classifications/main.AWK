# Abundant, deficient and perfect number classifications: http://rosettacode.org/wiki/Abundant,_deficient_and_perfect_number_classifications
# 1-20,000の間にある、不足数/完全数/過剰数の個数を求める。

function p(n,     sum, root, i) {
  if (n == 1) return 0;

  sum = 1
  root = sqrt(n)
  for (i = 2; i < root; i++) {
    if (n % i == 0) sum += i + n / i;
  }
  if (n % root == 0) sum += root;

  return sum
}

function classify(n,    x) {
  x = p(n)
  return x < n ? "deficient" : (x == n ? "perfect" : "abundant")
}

BEGIN {
  for (n = 1; n <= 20000; n++) couter[classify(n)] += 1;
  for (class in couter) print class, couter[class];
}

# perfect 4
# deficient 15043
# abundant 4953