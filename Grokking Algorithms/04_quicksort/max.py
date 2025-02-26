def max(arr):
    if len(arr) == 2:
        return arr[0] if arr[0] > arr[1] else arr[1]

    max_count = max(arr[1:])
    return arr[0] if arr[0] > max_count else max_count

print(max([3, 4, 12, 8, 49, 1]))