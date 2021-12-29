package chapter07.transmission

class Car : Moveable {
    private var mph = 0

    override fun currentSpeedInMph(): Int {
        return mph
    }

    fun accelerateTo(mph: Int) {
        this.mph = mph
    }

    fun brakeToStop() {
        mph = 0
    }
}