/* Equilibrium index: http://rosettacode.org/wiki/Equilibrium_index
 * 整数配列Aが与えられる。A[i]をAから取り除くとAはふたつに分割される(A[0:i],A[i+1:])
 * 分割によってできたふたつの配列の和が一致するようなiをすべて求める。
 * ここでAが巨大な配列になる場合がある。つまり整数配列をメモリ上にすべて展開しないように実装すること。
 */

using System;
using System.Collections.Generic;
using System.Linq;

class EquilibriumIndex
{
    private static List<int> digits = new List<int>() { -7, 1, 5, 2, -4, 3, 0 };

    public static void Main(string[] args)
    {
        Solve(digits);
        // index: 3; left = right = -1
        // index: 6; left = right = 0
    }

    private static void Solve(IEnumerable<int> digits)
    {
        var sum = digits.Sum();
        var left = 0;
        var index = 0;
        foreach (var digit in digits)
        {
            var right = sum - left - digit;
            if (right == left)
            {
                Console.WriteLine($"index: {index}; left = right = {left}");
            }

            left += digit;
            index++;
        }
    }
}

