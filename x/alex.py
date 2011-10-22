import sys
import re

if __name__ == "__main__":
    len_dict = int(raw_input().strip())
    in_words = raw_input().strip().split()
    num_cases = int(raw_input().strip())
    pots = []

    for i in range(num_cases):
        pots.append(raw_input().strip())

    word_buckets = {}
    for word in in_words:
        s = tuple(set(word))
        if s in word_buckets:
            word_buckets[s].append(word)
        else:
            word_buckets[s] = [word]

    for word in pots:
        s = tuple(set(word))
        if s in word_buckets:
            print " ".join(word_buckets[s])
        else:
            print 'NONE'
