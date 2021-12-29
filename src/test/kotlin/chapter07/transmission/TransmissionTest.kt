package chapter07.transmission

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class TransmissionTest {

    private lateinit var transmission: Transmission
    private lateinit var car: Car

    @Before
    fun 초기화() {
        car = Car()
        transmission = Transmission(car)
    }

    @Test
    fun 주행으로_기어를_바꾸고_35mph로_가속해도_기어는_주행기어다() {
        transmission.shift(Gear.DRIVE)
        car.accelerateTo(35)
        assertThat(transmission.gear, CoreMatchers.equalTo(Gear.DRIVE))
    }

    @Test
    fun 주행으로_기어를_바꾸고_30mph로_가속하면_기어를_주차로_바꿔도_기어는_주행기어다() {
        transmission.shift(Gear.DRIVE)
        car.accelerateTo(30)
        transmission.shift(Gear.PARK)
        assertThat(transmission.gear, CoreMatchers.equalTo(Gear.DRIVE))
    }

    @Test
    fun 기어를_주행으로_바꾸고_30mph로_가속할_때_브레이크_정지_후_기어를_주차로_바꾸면_기어는_주차기어다() {
        transmission.shift(Gear.DRIVE)
        car.accelerateTo(30)
        car.brakeToStop()
        transmission.shift(Gear.PARK)
        assertThat(transmission.gear, CoreMatchers.equalTo(Gear.PARK))
    }
}