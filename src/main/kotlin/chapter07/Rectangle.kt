package chapter07

class Rectangle {
    private var origin: Point
    private var opposite: Point

    constructor(origin: Point, oppositeCorner: Point) {
        this.origin = origin
        opposite = oppositeCorner
    }

    constructor(origin: Point) {
        this.origin = origin
        opposite = this.origin
    }

    fun area(): Int {
        return (Math.abs(origin.x - opposite.x) *
                Math.abs(origin.y - opposite.y)).toInt()
    }

    fun setOppositeCorner(opposite: Point) {
        this.opposite = opposite
    }

    fun origin(): Point {
        return origin
    }

    fun opposite(): Point {
        return opposite
    }

    override fun toString(): String {
        return "Rectangle(origin $origin opposite $opposite)"
    }
}