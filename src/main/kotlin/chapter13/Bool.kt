package chapter13

enum class Bool(private val value: Int) {

    False(0), True(1);

    companion object {
        const val FALSE = 0
        const val TRUE = 1
    }

    fun getValue(): Int {
        return value
    }
}
