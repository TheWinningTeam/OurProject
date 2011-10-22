import sys
import math

def segments(p):
    return zip(p, p[1:] + [p[0]])


def area(p):
    tot = 0
    for ((x0, y0), (x1, y1)) in segments(p):
        tot += x0*y1 - x1*y0
    return 0.5 * abs(tot)

def to_cart(pt):
    pt = math.pi*pt
    return (math.sin(pt), math.cos(pt))

if __name__ == "__main__":
    points = []

    for i in sys.stdin.readlines():
        if i.rstrip() == '$':
            break
        if i.rstrip():
            points.append(float(i))

    print area(points)
