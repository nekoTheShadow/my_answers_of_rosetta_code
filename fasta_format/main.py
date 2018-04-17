"""
FASTA format: http://www.rosettacode.org/wiki/FASTA_format
FASTA形式のasciiフラットファイルを以下のような形式で出力する。
[header]: [body]
実際のFASTAは数ギガバイトになる場合があるので、可能な限りメモリは節約すること。
"""


import io
import sys

def format_fasta(input, output):
    is_first_line = True
    for raw_line in input:
        line = raw_line.rstrip()

        is_header = False
        if line.startswith('>'):
            line = line[1:] + ': '
            is_header = True
        
        if not is_first_line and is_header:
            output.write('\n')
        output.write(line)

        is_first_line = False

    output.write('\n')


def test():
    test_cases = [
        ["ABC", "ABC\n"],
        [">header\nvalue", "header: value\n"],
        [">ABC", "ABC: \n"],
    ]

    for lines, expected in test_cases:
        input = io.StringIO(lines)
        output = io.StringIO()
        format_fasta(input, output)

        actual = output.getvalue()
        assert expected == actual, 'expected {0}, but actual {1}'.format(expected, actual)


if __name__ == '__main__':
    with open('sample.fasta') as file:
        format_fasta(file, sys.stdout)
    # Rosetta_Example_1: THERECANBENOSPACE
    # Rosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED
    
    