/*
 * Evolutionary algorithm: http://rosettacode.org/wiki/Evolutionary_algorithm
 * 文字列"METHINKS IT IS LIKE A WEASEL"をtarget、targetと同じ長さで、大文字+空白からなる文字列をparentとする。
 * while target != parent
 *   parentをC個複製して、それぞれにmutate関数を適用する。
 *      mutate: 引数として与えられた文字列をランダムに置換する。
 *   C個の複製とparentのうち、fitness関数の評価値がもっとも高いものを新しいparentとする。
 *      fitness関数: 与えられた文字列とtargetの「距離」を求める。
 * 
 * parentを適当に設定しても、以上の処理を行うと、最終的にはtargetに近づく。
 * ここでパラメータとして、parentの複製数C、mutate関数において、どの程度文字列をランダム置換するのかを示すmutateRateを用意する。
 * これを適当に変えながら実験していく。まずCを大きくすると、あるところまではCを大きくしただけparentに近づくスピードが速くなるが、
 * ある時点で頭打ちになるらしい。次にmutateRateを大きくしていく。mutateRateが大きいほど、入れ替わりが激しいということなので、
 * mutateRateを大きくすればするほど、targetへに早く近づく――と思いきや、必ずしもそうではなく、むしろ大きすぎるとかえって
 * targetになかなか到達しない。一方mutateRateを小さくしすぎる(=変化があまり起きない)と、これはこれでtargetに到達しづらくなる。
 * 要は適切なmutateRateがあるらしいということが分かる(´・ω・｀) わたしの鳥頭では、これ以上の本問の意図はよくわかりません(´・ω・｀)
 */

using System;
using System.Collections.Generic;
using System.Linq;

class EvolutionaryAlgorithm
{
    private static double mutateRate = 0.1;
    private static int C = 100;

    private static string target = "METHINKS IT IS LIKE A WEASEL";
    private static string uppers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";

    private static Random random = new Random();

    private static double Fitness(string parent)
    {
        double numerator = target.Zip(parent, (c1, c2) => c1 == c2 ? 1 : 0).Sum();
        return numerator / parent.Length;
    }

    private static string Mutate(string parent)
    {
        return string.Join("", parent.Select(c => random.NextDouble() <= mutateRate ? NextChar() : c));
    }

    private static char NextChar()
    {
        return uppers[random.Next(uppers.Length)];

    }

    public static void Main(string[] args)
    {
        var parent = "ABCDEFGHIJKLMNOPQRSTUVWXYZAB";
        int generation = 0;
        Console.WriteLine($"{generation} {parent}");

        while (parent != target)
        {
            var candidates = new List<string> { parent };
            candidates.AddRange(Enumerable.Range(0, C).Select(n => Mutate(parent)));

            parent = candidates.OrderByDescending(candidate => Fitness(candidate)).First();
            generation++;

            Console.WriteLine($"{generation} {parent}");
        }
    }
}
