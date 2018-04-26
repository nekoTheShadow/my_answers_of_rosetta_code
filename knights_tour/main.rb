# Knight's tour: http://rosettacode.org/wiki/Knight%27s_tour
# 入力として与えられるAlgebraic notation(=AN)をスタート地点として、「騎士の巡歴」問題を解く。
# ボードの1辺nが6を超えだしたあたりで、解答に時間がかかりすぎるのだが、
# 「ワーンスドロフの規則」なる貪欲法の1種を利用すると、計算量を抑えることができる――とのこと。
# この問題を解くにあたってggって知った(´・ω・｀)
# cf. http://www.geocities.jp/m_hiroi/puzzle/knight2.html

@n = 8
@moves = [[-2, -1], [-1, -2], [-2, 1], [-1, 2], [2, -1], [1, -2], [2, 1], [1, 2]]

# 座標の遍歴を見て、次に飛ぶことができる座標の一覧を求める。
def find_nxts(history)
  x, y = history[-1]
  @moves.each_with_object([]) do |(dx, dy), nxts|
    s, t = nxt = [x + dx, y + dy]
    nxts << nxt if 0 <= s && s < @n && 0 <= t && t < @n && !history.include?(nxt)
  end
end

# === main ===

# 入力であるANを加工する。
an = "a1"
start = [@n - an[1].to_i, an[0].ord - "a".ord]

# 騎士の巡歴; 「ワーンスドロフの規則」を利用する。
history = [start]
history << find_nxts(history).min_by{|nxt| find_nxts([*history, nxt]).size} until history.size == @n * @n

# 座標の遍歴を二次元リストにマッピングする。
board = Array.new(@n){Array.new(@n)}
history.each.with_index(1){|(x, y), i| board[x][y] = i}

# 二次元リストを加工して出力する。
rows = board.map do |row|
  row.map{|cell| sprintf("%02d", cell)}.join(" ")
end
puts rows.join("\n")
# 50 19 22 05 40 09 24 07
# 21 04 49 42 23 06 39 10
# 18 51 20 55 46 41 08 25
# 03 48 45 58 43 56 11 38
# 52 17 54 47 62 37 26 35
# 31 02 59 44 57 34 63 12
# 16 53 32 29 14 61 36 27
# 01 30 15 60 33 28 13 64