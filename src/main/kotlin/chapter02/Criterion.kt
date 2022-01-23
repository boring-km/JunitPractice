package chapter02

import chapter01.Scoreable

// 표준, 기준
class Criterion(val answer: Answer, val weight: Weight) : Scoreable {
    private var score = 0

    fun setScore(score: Int) {
        this.score = score
    }

    override fun getScore(): Int {
        return score
    }

    fun matches(answer: Answer?) =
        weight == Weight.DontCare || answer!!.match(answer)
}