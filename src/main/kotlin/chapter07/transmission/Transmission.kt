package chapter07.transmission

class Transmission(private val moveable: Moveable) {
    var gear: Gear? = null
        private set

    fun shift(gear: Gear) {
        // 현재 속도가 0 mph가 넘고 기어가 주차기어라면 기어 변속을 하지 않는다.
        if (moveable.currentSpeedInMph() > 0 && gear === Gear.PARK) return
        this.gear = gear
    }
}