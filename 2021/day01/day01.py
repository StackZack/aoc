from csv import reader
import os


def get_count_of_depth_increases(depthList):
    prevDepth = None
    count = 0
    for depth in depthList:
        if prevDepth is None:
            prevDepth = depth
            pass
        if depth > prevDepth:
            count = count + 1
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
    numOfIncreases = get_count_of_depth_increases(depthList)
    print(f"There were {numOfIncreases} increases in depth.")
