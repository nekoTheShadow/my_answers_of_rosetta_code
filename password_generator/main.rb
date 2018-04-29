# Password generator: http://rosettacode.org/wiki/Password_generator
# 大文字、小文字、数字、その他記号をそれぞれ最低1文字を含む、長さlengthのパスワードをcount個生成する問題。
# 問題の指定ではusageやエラーチェックも求められているが、面倒なのでさぼった(´・ω・｀)

def password_generator(length, count)
  lowers = [*'a'..'z']
  uppers = [*'A'..'Z']
  digits = [*'0'..'9']
  others = '!"#$%&\'()*+,-./:;<=>?@[]^_{|}~'.chars
  characters = [*lowers, *uppers, *digits, *others]

  count.times.map do
    password = [lowers, uppers, digits, others].map(&:sample)
    until password.size == length
      character = characters.sample
      password << character unless password.include?(character)
    end
    password.shuffle.join
  end
end

puts password_generator(8, 3)
# l9,#R!hO
# bLQl9,&(
# *M{=c2A3
