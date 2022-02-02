package chapter06

enum class Bool(val value: Int) {
    False(0), True(1);

    companion object {
        const val FALSE = 0
        const val TRUE = 1
    }
}