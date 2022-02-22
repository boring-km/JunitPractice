package chapter13


class ScoreCollection {
    private val scores: MutableList<Scoreable> = ArrayList()
    fun add(scoreable: Scoreable?) {
        requireNotNull(scoreable)
        scores.add(scoreable)
    }

    fun arithmeticMean(): Int {
        if (scores.size == 0) return 0
        // ...
        val total = scores.stream().mapToInt { obj: Scoreable -> obj.getScore() }.sum()
        return total / scores.size
    }
}