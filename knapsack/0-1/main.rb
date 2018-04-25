# Knapsack problem/0-1: http://rosettacode.org/wiki/Knapsack_problem/0-1
# タイトルの通り、0-1ナップサックを解き、最終的なvalueとweight、および袋に詰めた品物を出力する。
# この程度の入力であれば、ブルートフォースでも問題ないと思うが、動的計画法を利用した。

# 入力を扱いやすい形に加工する。
bound_weight = 400
names, weights, values = [""], [0], [0]
DATA.each_line(chomp: true) do |line|
  name, weight, value = line.split
  
  names << name
  weights << weight.to_i
  values << value.to_i
end

# 動的計画法; 経路復元のため、直前の重さを記録しておく。
dp = Array.new(names.size){Array.new(bound_weight + 1, 0)}
prev = Array.new(names.size){Array.new(bound_weight + 1, 0)}
(1...names.size).each do |i|
  (0..bound_weight).each do |j|
    next if dp[i - 1][j].zero?

    # names[i]を利用しない場合.
    if dp[i][j] < dp[i - 1][j]
      dp[i][j] = dp[i - 1][j]
      prev[i][j] = j
    end

    # names[i]を利用する場合.
    if j + weights[i] <= bound_weight && dp[i][j + weights[i]] < dp[i - 1][j] + values[i]
      dp[i][j + weights[i]] = dp[i - 1][j] + values[i]
      prev[i][j + weights[i]] = j
    end
  end

  # names[i]が親(?)になる場合.
  if dp[i][weights[i]] < values[i]
    dp[i][weights[i]] = values[i]
    prev[i][weights[i]] = 0
  end
end

max_value = dp.last.max
max_weight = dp.last.index(max_value)

# 経路復元; 現在の重さと直前の重さを比べて、変化があればitemをナップサックに入れたと判定する。
items = []
weight = max_weight
(names.size - 1).downto(1) do |i|
  last_weight = prev[i][weight]
  items << names[i] if last_weight != weight
  weight = last_weight
end

puts "weight: #{max_weight}"
puts "value : #{max_value}"
puts "-- items --"
items.reverse.each{|item| puts item}

# weight: 396
# value : 1030
# -- items --
# map
# compass
# water
# sandwich
# glucose
# banana
# suntan_cream
# waterproof_trousers
# waterproof_overclothes
# note-case
# sunglasses
# socks


__END__
map 9 150
compass 13 35
water 153 200
sandwich 50 160
glucose 15 60
tin 68 45
banana 27 60
apple 39 40
cheese 23 30
beer 52 10
suntan_cream 11 70
camera 32 30
T-shirt 24 15
trousers 48 10
umbrella 73 40
waterproof_trousers 42 70
waterproof_overclothes 43 75
note-case 22 80
sunglasses 7 20
towel 18 12
socks 4 50
book 30 10 