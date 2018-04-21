# Apply a callback to an array: Apply a callback to an array
# 配列のすべての要素に関数を適用するという問題。
# 未確認だが、以下のコードはgawkでしか動かない気がする。

function do_something(arg) {
  return sprintf("execute do_something(arg=%s)", arg)
}

BEGIN {
  split("a b c d e", array)

  for (i = 1; i <= length(array); i++) {
    array[i] = do_something(array[i])
  }

  for (i = 1; i <= length(array); i++) {
    print(array[i])
  }
  # execute do_something(arg=a)
  # execute do_something(arg=b)
  # execute do_something(arg=c)
  # execute do_something(arg=d)
  # execute do_something(arg=e)
}

