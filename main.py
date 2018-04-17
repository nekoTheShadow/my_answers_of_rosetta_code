if __name__ == '__main__':
    with open('sample.fasta') as file:
        for line in file:
            second = line.strip()
            print(second)