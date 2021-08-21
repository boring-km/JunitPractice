package chapter02

class Profile(_name: String) {
    private val answers = HashMap<String, Answer>()
    private var score: Int = 0
    private var name: String

    init {
        name = _name
    }

    // 회사에게 받은 질문에 대한 답변을 저장
    fun add(answer: Answer) {
        answers[answer.questionText] = answer
    }

    fun matches(criteria: Criteria) : Boolean {
        score = 0

        var kill = false
        var anyMatches = false
        for (criterion: Criterion in criteria) {
            var answer = answers[criterion.answer.questionText]
            var match = criterion.weight == Weight.DontCare || answer!!.match(criterion.answer)
            if (!match && criterion.weight == Weight.MustMatch) {
                kill = true
            }
            if (match) {
                score += criterion.weight.value
            }
            anyMatches = anyMatches or match
        }
        if (kill)
            return false
        return anyMatches
    }
}
