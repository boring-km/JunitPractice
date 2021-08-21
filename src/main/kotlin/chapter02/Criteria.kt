package chapter02

import java.util.*

// Criterion의 복수형; 표준, 기준들
class Criteria : Iterable<Criterion?> {
    private val criteria: MutableList<Criterion> = ArrayList<Criterion>()
    fun add(criterion: Criterion) {
        criteria.add(criterion)
    }

    override fun iterator(): Iterator<Criterion> {
        return criteria.iterator()
    }

    fun arithmeticMean(): Int {
        return 0
    }

    fun geometricMean(numbers: IntArray): Double {
        val totalProduct = Arrays.stream(numbers).reduce(1) { product: Int, number: Int -> product * number }
        return Math.pow(totalProduct.toDouble(), 1.0 / numbers.size)
    }
}