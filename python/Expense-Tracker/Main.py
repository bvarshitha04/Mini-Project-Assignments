import csv
import os

FILE = "expenses.csv"

# Create file if not exists
if not os.path.exists(FILE):
    with open(FILE, "w", newline="") as f:
        writer = csv.writer(f)
        writer.writerow(["Date", "Category", "Amount", "Description"])


def add_expense():
    try:
        date = input("Enter date (dd-mm-yyyy): ")
        category = input("Enter category: ")
        amount = float(input("Enter amount: "))
        desc = input("Enter description: ")

        with open(FILE, "a", newline="") as f:
            writer = csv.writer(f)
            writer.writerow([date, category, amount, desc])

        print("Expense added successfully!")

    except:
        print("Invalid input")


def show_expenses():
    with open(FILE, "r") as f:
        reader = csv.reader(f)
        for row in reader:
            print(row)


def total_expense():
    total = 0
    with open(FILE, "r") as f:
        reader = csv.DictReader(f)
        for row in reader:
            total += float(row["Amount"])

    print("Total Expense:", total)


def category_wise():
    data = {}
    with open(FILE, "r") as f:
        reader = csv.DictReader(f)
        for row in reader:
            cat = row["Category"]
            amt = float(row["Amount"])
            data[cat] = data.get(cat, 0) + amt

    print("Category-wise Spending:")
    for k, v in data.items():
        print(k, ":", v)


def highest_category():
    data = {}
    with open(FILE, "r") as f:
        reader = csv.DictReader(f)
        for row in reader:
            cat = row["Category"]
            amt = float(row["Amount"])
            data[cat] = data.get(cat, 0) + amt

    if data:
        max_cat = max(data, key=data.get)
        print("Highest Spending Category:", max_cat)


def monthly_summary():
    month = input("Enter month (mm): ")
    total = 0

    with open(FILE, "r") as f:
        reader = csv.DictReader(f)
        for row in reader:
            if row["Date"].split("-")[1] == month:
                total += float(row["Amount"])

    print("Monthly Expense:", total)


# MAIN LOOP
while True:
    print("\n1 Add Expense")
    print("2 Show Expenses")
    print("3 Total Expense")
    print("4 Category Wise")
    print("5 Highest Category")
    print("6 Monthly Summary")
    print("7 Exit")

    choice = input("Enter choice: ")

    if choice == "1":
        add_expense()
    elif choice == "2":
        show_expenses()
    elif choice == "3":
        total_expense()
    elif choice == "4":
        category_wise()
    elif choice == "5":
        highest_category()
    elif choice == "6":
        monthly_summary()
    elif choice == "7":
        print("Goodbye!")
        break
    else:
        print("Invalid choice")