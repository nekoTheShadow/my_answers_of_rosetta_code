# Floyd-Warshall algorithm: http://rosettacode.org/wiki/Floyd-Warshall_algorithm
# ワーシャル・フロイド法を使って、全ペアの最短距離とその経路を求める。

edges = [
  [1, 3, -2],
  [2, 1,  4],
  [2, 3,  3],
  [3, 4,  2],
  [4, 2, -1]
]

n = 4
distances = Array.new(n) do |i|
  Array.new(n){|j| i == j ? 0 : Float::INFINITY}
end
nexts = Array.new(n){Array.new(n, 0)}
edges.each do |from, to, distance|
  distances[from - 1][to - 1] = distance
  nexts[from - 1][to - 1] = to - 1
end

nodes = [*0...distances.size]
nodes.product(nodes, nodes) do |mid, from, to|
  if distances[from][to] > distances[from][mid] + distances[mid][to]
    distances[from][to] = distances[from][mid] + distances[mid][to]
    nexts[from][to] = nexts[from][mid]
  end
end

nodes.product(nodes) do |x, y|
  next if x == y

  path = [x]
  path << nexts[path.last][y] until path.last == y
  puts "%d -> %d  %4d    %s" % [x + 1, y + 1, distances[x][y], path.map{|node| node + 1}.join(" -> ")]
end

__END__
1 -> 2    -1    1 -> 3 -> 4 -> 2
1 -> 3    -2    1 -> 3
1 -> 4     0    1 -> 3 -> 4
2 -> 1     4    2 -> 1
2 -> 3     2    2 -> 1 -> 3
2 -> 4     4    2 -> 1 -> 3 -> 4
3 -> 1     5    3 -> 4 -> 2 -> 1
3 -> 2     1    3 -> 4 -> 2
3 -> 4     2    3 -> 4
4 -> 1     3    4 -> 2 -> 1
4 -> 2    -1    4 -> 2
4 -> 3     1    4 -> 2 -> 1 -> 3