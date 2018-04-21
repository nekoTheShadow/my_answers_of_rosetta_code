#!/bin/bash


# Palindrome detection https://rosettacode.org/wiki/Palindrome_detection
# 与えられた文字が回文かどうかを判定する。
# shell scriptらしからぬコードになってしまった(´・ω・｀)

is_palindromic () {
  paste -d' ' <(echo $1) <(echo $1 | rev) | awk '{print $1 " is " (($1==$2) ? "palindromic" : "not palindromic")}' 
}

is_palindromic "abc"
is_palindromic "aba"
