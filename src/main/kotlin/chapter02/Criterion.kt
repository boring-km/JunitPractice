package chapter02

import chapter01.Scoreable

// 표준, 기준
class Criterion(val answer: Answer, weight: Weight) : Scoreable {
    internal val weight: Weight
    private var score = 0

    fun setScore(score: Int) {
        this.score = score
    }

    override fun getScore(): Int {
        return score
    }

    init {
        this.weight = weight
    }
}