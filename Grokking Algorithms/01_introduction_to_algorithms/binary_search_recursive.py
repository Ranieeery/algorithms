def binary_search(list, item, min, max):

    if min <= max:
        mid = (min + max) // 2
        guess = list[mid]

        if guess == item:
            return mid

        if guess > item:
            max = mid - 1
            return binary_search(list, item, min, max)
        else:
            min = mid + 1
            return binary_search(list, item, min, max)
    else:
        return None


my_list = [15, 27, 39, 77, 92, 108, 121, 135, 200, 432, 12, 8, 1, 0]
my_list.sort()

min = 0
max = len(my_list) - 1

print(my_list)
print(binary_search(my_list, 77, min, max))
