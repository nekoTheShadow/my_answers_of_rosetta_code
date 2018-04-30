/*
 * Exceptions/Catch an exception thrown in a nested call: http://rosettacode.org/wiki/Exceptions/Catch_an_exception_thrown_in_a_nested_call
 * 1. ユーザ定義例外U0とU1を定義する
 * 2. foo関数を定義する。foo関数はbar関数を2回呼び出す
 * 3. bar関数を定義する。bar関数はbaz関数を呼び出す。
 * 4. baz関数を定義する。baz関数は呼び出されるごとに動作を変え、1回目はU0例外を、2回目はU1例外を創出する。
 * 5. foo関数ではU0関数しかキャッチしないとする。
 * ---> 1.-6.を実装したときどのようなことが発生するだろうか?
 * 問題の趣旨は以上の通り。「U0はプログラム内で処理されるが、U1は処理されず、プログラム自体が異常終了する」というのが答え。
 * 実際にコーディングする必要がない程度の問題ではあるが、C#でユーザ定義例外やtry-catchをまじめに使った経験がなかったため、
 * その練習の意味を込めて解いてみた(´・ω・｀)
 * /

using System;
using System.Linq;

class U0 : Exception { }
class U1 : Exception { }

class Program
{
    static void Main(string[] args)
    {
        Foo();

        /*
        catch U0 Exception

        ハンドルされていない例外: U1: 種類 'U1' の例外がスローされました。
           場所 Program.Baz() 場所 C:\Users\hnaka\Documents\programming\csharp\rosetta\Program.cs:行 50
           場所 Program.Bar() 場所 C:\Users\hnaka\Documents\programming\csharp\rosetta\Program.cs:行 35
           場所 Program.Foo() 場所 C:\Users\hnaka\Documents\programming\csharp\rosetta\Program.cs:行 24
           場所 Program.Main(String[] args) 場所 C:\Users\hnaka\Documents\programming\csharp\rosetta\Program.cs:行 15
        続行するには何かキーを押してください. . .
        */
    }

    static void Foo()
    {
        foreach (int i in Enumerable.Range(0, 2))
        {
            try
            {
                Bar();
            }
            catch (U0)
            {
                Console.WriteLine("catch U0 Exception");
            }
        }
    }

    static void Bar()
    {
        Baz();
    }

    private static int bazCounter = 0;

    static void Baz()
    {
        if (bazCounter == 0)
        {
            bazCounter++;
            throw new U0();
        }

        if (bazCounter == 1)
        {
            throw new U1();
        }
    }
}