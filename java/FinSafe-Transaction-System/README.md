# FinSafe Transaction System 💰

Welcome to my Java banking mini-project! This is a simple banking system that safely handles money deposits and withdrawals without letting accounts go below zero.

### What it does:
- **Manage Accounts:** Creates bank accounts with a starting balance.
- **Deposit & Withdraw:** Add or remove money easily.
- **Safety First:** If someone tries to withdraw more money than they have, the system stops them and throws a custom error so the bank doesn't lose money!

### How I built it:
- **Language:** Core Java.
- **OOP Concepts:** Built using clean Object-Oriented design with an `Account` class.
- **Custom Exceptions:** I wrote a custom `InsufficientFundsException` to safely catch and handle bad transactions instead of crashing the program. 

### Screenshots
*(Screenshots of the terminal output coming soon!)*
![Output Placeholder](screenshots/output.png)

### How to run it yourself:
1. Open your terminal in this folder.
2. Compile the code: `javac *.java`
3. Run it: `java Main`
