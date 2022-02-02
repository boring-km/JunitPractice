package chapter06

import java.util.stream.Collectors


class Person {
    private val characteristics: MutableList<Question> = ArrayList()
    fun add(characteristic: Question) {
        characteristics.add(characteristic)
    }

    fun getCharacteristics(): List<Question> {
        return characteristics
    }

    fun withCharacteristic(questionPattern: String?): List<Question> {
        return characteristics.stream().filter { c: Question ->
            c.text.endsWith(
                questionPattern!!
            )
        }.collect(Collectors.toList())
    }
}