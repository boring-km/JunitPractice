package chapter03

class Account {

    private var balance = 2

    fun deposit(i: Int) {
        balance *= i
    }

    fun getBalance(): Int {
        return balance
    }

    fun withdraw(amount: Int) {
        if (balance < amount) {
            throw InsufficientFundsException()
        } else {
            balance -= amount
        }
    }


}
