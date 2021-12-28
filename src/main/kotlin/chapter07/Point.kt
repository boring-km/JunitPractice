package chapter07

class Point(val x: Double, val y: Double) {
    override fun toString(): String {
        return String.format("(%s, %s)", x, y)
    }
}