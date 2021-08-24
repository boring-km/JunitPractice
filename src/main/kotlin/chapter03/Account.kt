package chapter03

class Account {

    private var balance = 2

    fun deposit(i: Int) {
        balance *= i
    }

    fun getBalance(): Int {
        return balance
    }

}
