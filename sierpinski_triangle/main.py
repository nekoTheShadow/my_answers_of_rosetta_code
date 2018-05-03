"""
Sierpinski triangle: http://rosettacode.org/wiki/Sierpinski_triangle
「シェルピンスキーのギャスケット」のアスキーアートで書く問題。
日本語wikipedia「シェルピンスキーのギャスケット」によると、パスカルの三角形のうち、奇数を黒、偶数を白に塗り分けるといいらしい。
https://ja.wikipedia.org/wiki/シェルピンスキーのギャスケット
"""

def draw(order):
    board = [[0 for _ in range(order * 4)] for _ in range(order * 4)]
    board[0][0] = 1

    for x in range(len(board) - 1):
        for y in range(x + 1):
            board[x + 1][y] += board[x][y]
            board[x + 1][y + 1] += board[x][y]
    
    for i, row in enumerate(board):
        line = ' ' * (order * 4 - i - 1) + ' '.join(' ' if cell % 2 == 0 else '*' for cell in row)
        print(line)


if __name__ == '__main__':
    draw(4)
    draw(8)

#                *
#               * *
#              *   *
#             * * * *
#            *       *
#           * *     * *
#          *   *   *   *
#         * * * * * * * *
#        *               *
#       * *             * *
#      *   *           *   *
#     * * * *         * * * *
#    *       *       *       *
#   * *     * *     * *     * *
#  *   *   *   *   *   *   *   *
# * * * * * * * * * * * * * * * *
#                                *
#                               * *
#                              *   *
#                             * * * *
#                            *       *
#                           * *     * *
#                          *   *   *   *
#                         * * * * * * * *
#                        *               *
#                       * *             * *
#                      *   *           *   *
#                     * * * *         * * * *
#                    *       *       *       *
#                   * *     * *     * *     * *
#                  *   *   *   *   *   *   *   *
#                 * * * * * * * * * * * * * * * *
#                *                               *
#               * *                             * *
#              *   *                           *   *
#             * * * *                         * * * *
#            *       *                       *       *
#           * *     * *                     * *     * *
#          *   *   *   *                   *   *   *   *
#         * * * * * * * *                 * * * * * * * *
#        *               *               *               *
#       * *             * *             * *             * *
#      *   *           *   *           *   *           *   *
#     * * * *         * * * *         * * * *         * * * *
#    *       *       *       *       *       *       *       *
#   * *     * *     * *     * *     * *     * *     * *     * *
#  *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
# * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
