# Anagrams: https://rosettacode.org/wiki/Anagrams
# 与えられたテキストファイルからアナグラムを探し、もっとも同種が多いものを出力する。

require 'open-uri'

anagrams = Hash.new{|hash, key| hash[key] = []}
open('http://www.puzzlers.org/pub/wordlists/unixdict.txt') do |txt|
  txt.each_line(chomp: true) do |line|
    anagrams[line.chars.sort.join] << line
  end
end

max_len = anagrams.values.map(&:size).max
anagrams.values.each do |values|
  p values if values.size == max_len
end
# ["abel", "able", "bale", "bela", "elba"]
# ["alger", "glare", "lager", "large", "regal"]
# ["angel", "angle", "galen", "glean", "lange"]
# ["caret", "carte", "cater", "crate", "trace"]
# ["elan", "lane", "lean", "lena", "neal"]
# ["evil", "levi", "live", "veil", "vile"]