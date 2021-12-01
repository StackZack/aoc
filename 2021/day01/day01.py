from csv import reader
from queue import Queue
import os


def count_depth_increases_by_window(depthList):
    count = 0
    prevWindow = None
    q = Queue(maxsize=3)
    for index, depth in enumerate(depthList, start=0):
        if len(depthList) - index == 2:
            break
        if prevWindow is None:
            prevWindow = depth
            q.put(depth)
            continue

        if len(list(q.queue)) == 3:
            q.get()
        q.put(depth)
        windowSum = sum(list(q.queue))

        if windowSum > prevWindow:
            count += 1
        prevWindow = windowSum
    return count


def count_depth_increases(depthList):
    prevDepth = None
    count = 0
    for depth in depthList:
        if prevDepth is None:
            prevDepth = depth
            continue
        if depth > prevDepth:
            count += 1
        prevDepth = depth
    return count


def read_depth_file(filePath):
    data = []
    with open(
        os.path.join(
            os.path.dirname(__file__),
            filePath,
        ),
        "r",
    ) as f:
        csv_reader = reader(f)
        [data.append(int(row[0])) for row in csv_reader]
    return data


if __name__ == "__main__":
    depthList = read_depth_file("input.csv")
    numOfIncreases = count_depth_increases(depthList)
    print(f"There were {numOfIncreases} increases in depth.")
    numOfIncreasesByWindow = count_depth_increases_by_window(depthList)
    print(
        "There were {} increases in depth in the sliding window."
        .format(numOfIncreasesByWindow)
    )
