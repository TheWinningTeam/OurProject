import sys
import re

alpha_hash  = {
    'a':0,
    'b':1,
    'c':2,
    'd':3,
    'e':4,
    'f':6,
    'g':6,
    'h':7,
    'i':8,
    'j':9,
    'k':10,
    'l':11,
    'm':12,
    'n':13,
    'o':14,
    'p':15,
    'q':16,
    'r':17,
    's':18,
    't':19,
    'u':20,
    'v':21,
    'w':22,
    'x':23,
    'y':24,
    'z':25,
}

def set_member(word, array):
    for char in word:
        array[alpha_hash[char]] = 1

def check_member(word, array):
    for char in word:
        if array[alpha_hash[char.lower()]] == 0:
            return 0
    return 1

if __name__ == "__main__":
    array = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

    set_words = raw_input().strip().split()
    check_words = raw_input().strip().split()

    for word in set_words:
        set_member(word, array)

    matches = 0
    for word in check_words:
        matches += check_member(word, array)
    print matches
