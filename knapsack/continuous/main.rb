# Knapsack problem/Continuous: http://rosettacode.org/wiki/Knapsack_problem/Continuous
# 連続ナップサック問題というらしい(´・ω・｀) 
# 指定された重量を超えないように価値を最大化するが、このとき与えられた品物は好きに分けてよい。
# たいそうな名前が付けられているので、少し身構えるが、単に"コスパがよい"ものから選んでいけばよいだけである。
# Rubyには有理数クラス(Rational)が用意されているので、これを利用した。

# 入力を利用しやすい形に加工する。このとき単位当たりの価値が高いものの順番に並べておく。
items = DATA.map{|line|
  name, weight, value = line.split
  [name, Rational(weight), value.to_i]
}.sort_by{|name, weight, value| value.quo(weight) * -1}

# 単位あたりの価値が高いものから選んでいく。
capacity = 15
total_value = 0
answers = []
items.each do |name, weight, value|
  if capacity <= weight
    answers << [name, capacity]
    total_value += value.quo(weight) * capacity
    break
  end

  answers << [name, weight]
  total_value += value
  capacity -= weight
end

# 答えを出力する。
puts "value: #{total_value.to_f}"
answers.each do |name, weight|
  puts "#{name} -> #{weight.to_f}"
end
# value: 349.3783783783784
# salami -> 3.0
# ham -> 3.6
# brawn -> 2.5
# greaves -> 2.4
# welt -> 3.5

__END__
beef 3.8 36
pork 5.4 43
ham 3.6 90
greaves 2.4 45
flitch 4.0 30
brawn 2.5 56
welt 3.7 67
salami 3.0 95
sausage 5.9 98 