package chapter13

enum class Weight(val value: Int) {
    MustMatch(Int.MAX_VALUE), VeryImportant(5000), Important(1000), WouldPrefer(100), DontCare(0);

}