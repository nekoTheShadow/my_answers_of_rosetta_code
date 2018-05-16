"""
Read a configuration file: http://rosettacode.org/wiki/Read_a_configuration_file
とあるフォーマットのファイルを読み込む問題。仕様がゆるふわの極みであるが、以下の通りに忖度した。
- 空行は無視する。
- #からはじまる行はコメント行とする。
- 基本的なフォーマット ---> UPPER value1,value2,...
    - valueが省略された場合はBooleanでTrueと解釈する。
        - ただしUPPERの前に;が付与された場合はFalse
    - valueはカンマ区切りで、複数値を扱うことができる(=配列型)。
        - ただしvalueが1つだけ(=カンマなし)の場合は配列ではなく文字列として扱う。
"""

if __name__ == '__main__':
    config = {}
    with open('config.file') as file:
        for raw_line in file:
            line = raw_line.strip()
            if len(line) == 0 or line.startswith('#'): continue # 空行orコメント行

            tokens = line.split(' ', 1)
            if len(tokens) == 1:
                config[tokens[0]] = True
            elif tokens[0] == ';':
                config[tokens[1]] = False
            else:
                values = tokens[1].split(',')
                config[tokens[0]] = values[0] if len(values) == 1 else values
    
    for key, value in config.items():
        name = key.lower()
        if isinstance(value, list):
            for i, v in enumerate(value):
                print(f'{name}[{i}] = {v}')
        else:
            print(f'{name} = {value}')

    # fullname = Foo Barber
    # favouritefruit = banana
    # needspeeling = True
    # seedsremoved = False
    # otherfamily[0] = Rhu Barber
    # otherfamily[1] =  Harry Barber

