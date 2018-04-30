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