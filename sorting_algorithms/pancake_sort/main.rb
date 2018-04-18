# Sorting algorithms/Pancake sort: http://www.rosettacode.org/wiki/Sorting_algorithms/Pancake_sort
# 重複のない整数列をパンケーキソートでソートする問題。
# ただし最短手順を見つけるという類ではなく、以下の手順で本当にソートできるのかを確かめることが目的?
# 1. 未ソート領域で最大の値を探索し、そのインデックスをxとする。
# 2.1 最大の値が右端の場合: 左端から「未ソート領域の右端」の領域をソートする。
# 2.2 最大の値が右端以外の場合: 左端からxの領域をflip=reverseする。


goal = (1..9).to_a
start = goal.shuffle

lsts = [start]
until (lst = lsts.last) == goal
  max_rank, max_idx = (0...lst.size).map{|idx| [goal.index(lst[idx]), idx]}.max_by{|rank, idx| rank == idx ? -1 : rank}
  pivot = (max_idx == 0) ? max_rank : max_idx
  lsts << [*lst[0..pivot].reverse, *lst[pivot+1..-1]]
end

lsts.each{|lst| p lst}
# [8, 4, 2, 9, 6, 1, 3, 7, 5]
# [9, 2, 4, 8, 6, 1, 3, 7, 5]
# [5, 7, 3, 1, 6, 8, 4, 2, 9]
# [8, 6, 1, 3, 7, 5, 4, 2, 9]
# [2, 4, 5, 7, 3, 1, 6, 8, 9]
# [7, 5, 4, 2, 3, 1, 6, 8, 9]
# [6, 1, 3, 2, 4, 5, 7, 8, 9]
# [5, 4, 2, 3, 1, 6, 7, 8, 9]
# [1, 3, 2, 4, 5, 6, 7, 8, 9]
# [3, 1, 2, 4, 5, 6, 7, 8, 9]
# [2, 1, 3, 4, 5, 6, 7, 8, 9]
# [1, 2, 3, 4, 5, 6, 7, 8, 9]
