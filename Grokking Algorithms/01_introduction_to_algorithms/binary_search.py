def binary_search(list, item):

    min = 0
    max = len(list) - 1

    while min <= max:
        mid = (min + max) // 2
        guess = list[mid]

        if guess == item:
            return mid

        if guess > item:
            max = mid - 1
        else:
            min = mid + 1

    return None


my_list = [15, 27, 39, 77, 92, 108, 121, 135, 200, 432, 12, 8, 1, 0]
my_list.sort()

print(my_list)
print(binary_search(my_list, 77))
