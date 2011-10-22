import sys
import re

if __name__ == "__main__":
    min_len = int(raw_input())
    occu = int(raw_input())
    leng = int(raw_input())
    max_occ = (leng - min_len) + 1
    max_len = (leng - occu) + 1

    song = []
    for i in sys.stdin.readlines():
        if i.rstrip():
            song.append(int(i))
    song_str = ' '.join(map(str, song))

    patterns = {}
    for j in range(min_len, max_len):
        patterns[j] = []
        for i in range(len(song)):
            if i+j >= len(song):
                break
            patterns[j].append(song[i:i+j])

    print song_str

    matches = []
    total = 0
    for key,val in patterns.iteritems():
        for i in val:
            if i in matches:
                continue
            reg = r'(?=%s)' % ' '.join(map(str, i))
            print reg
            tmp = len(re.findall(reg, song_str))
            if(tmp >= occu):
                matches.append(i)
                total += tmp

    print matches, total
