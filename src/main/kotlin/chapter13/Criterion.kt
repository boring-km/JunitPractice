package chapter13

class Criterion(
    val answer: Answer,
    val weight: Weight
){

    fun matches(answer: Answer): Boolean {
        return weight === Weight.DontCare || answer.match(this.answer)
    }
}