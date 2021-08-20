package chapter01

class ScoreCollection {
    private var scores: MutableList<Scoreable> = arrayListOf()

    fun add(scoreable: Scoreable) {
        scores.add(scoreable)
    }

    fun arithmeticMean(): Int {
        val total = scores.stream().mapToInt(Scoreable::getScore).sum()
        return total / scores.size
    }
}