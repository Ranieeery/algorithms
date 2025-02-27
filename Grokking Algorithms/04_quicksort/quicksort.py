def quicksort(arr):
    if len(arr) < 2:
        return arr
    else:
        pivot = arr[0]
        min = [i for i in arr[1:] if i <= pivot]
        max = [i for i in arr[1:] if i > pivot]
        return quicksort(min) + [pivot] + quicksort(max)


print(quicksort([10, 5, 2, 19]))
