package chapter07

class Bearing(var value: Int) {
    companion object {
        const val MAX = 359
    }

    init {
        if (value < 0 || value > MAX) throw BearingOutOfRangeException()
    }

    fun angleBetween(bearing: Bearing): Int {
        return value - bearing.value
    }
}

class BearingOutOfRangeException: Exception()
