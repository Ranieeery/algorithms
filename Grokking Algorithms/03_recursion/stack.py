def salute(name):
    print("Hi, " + name + "!")
    salute2(name)
    print("Getting ready to say bye...")
    bye()


def salute2(name):
    print("How are you, " + name + "?")


def bye():
    print("OK, bye!")


salute("Raniery")
