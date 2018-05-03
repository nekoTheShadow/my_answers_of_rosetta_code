# Lucas-Lehmer test: http://rosettacode.org/wiki/Lucas-Lehmer_test
# メルセンヌ数が素数かどうかを判定する「リュカ–レーマー・テスト」を実装するというもの。
# 実装自体は単純だが、巨大数を扱うため、線形時間が肥大化しがち。
# 解答例を見ていると、8時間プログラムを稼働させる(!!)などしているが、さすがにそこまでは待てないので、
# タイムアウト機構を設けた。逃げである(´・ω・｀)

require 'prime'
require 'timeout'

def lucas_lehmer_test(prime)
  # リュカ–レーマー・テストではpが奇素数であることが必要。
  # したがってp=2はテストの対象外だが、M(2)=2**2-1=3は素数。
  return true if prime == 2

  m = (1 << prime) - 1
  s = 4 
  (prime - 2).times{ s = (s ** 2 - 2) % m }
  s == 0
end

mersenne_primes = []
begin
  Timeout.timeout(60) do 
    Prime.each{|prime| mersenne_primes << prime if lucas_lehmer_test(prime)}
  end
rescue Timeout::Error
  puts "TIME OUT!!!"
end

p mersenne_primes
#=> [2, 3, 5, 7, 13, 17, 19, 31, 61, 89, 107, 127, 521, 607, 1279, 2203, 2281, 3217, 4253, 4423]