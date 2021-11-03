package chapter06


class ScoreCollection {
    private val scores: MutableList<Scoreable> = ArrayList()
    fun add(scoreable: Scoreable) {
        scores.add(scoreable)
    }

    fun arithmeticMean(): Int {
        if (scores.size == 0) return 0 // ArithmeticException 발생 가능성
        val total = scores.stream().mapToLong { obj: Scoreable -> obj.getScore().toLong() }.sum()
        return (total / scores.size).toInt()
    }
}

fun interface Scoreable {
    fun getScore(): Int
}