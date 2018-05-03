# Leap year: http://rosettacode.org/wiki/Leap_year
# うるう年判定プログラムを実装する問題。
# 奇をてらった実装にしすぎたきらいがある(´・ω・｀)

BEGIN {
  # 4で割り切れる場合はうるう年
  # ただし100で割り切れる場合は平年
  # ただし400で割り切れる場合はうるう年
  true = 1; false = 0
  for (year =   1; year < 3000; year +=   1) is_leap_year[year] = false;
  for (year =   4; year < 3000; year +=   4) is_leap_year[year] = true;
  for (year = 100; year < 3000; year += 100) is_leap_year[year] = false;
  for (year = 400; year < 3000; year += 400) is_leap_year[year] = true;

  split("1900,1994,1996,1997,2000", years, ",")
  for (i = 1; i <= length(years); i++) {
    year = years[i]
    msg = is_leap_year[year] ? "is leap year" : "is not leap year"
    printf("%d %s \n", year, msg);
  }
  # 1900 is not leap year
  # 1994 is not leap year
  # 1996 is leap year
  # 1997 is not leap year
  # 2000 is leap year
}# 
