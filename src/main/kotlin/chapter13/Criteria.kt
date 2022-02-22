package chapter13

class Criteria : MutableIterable<Criterion> {

    private val criteria = ArrayList<Criterion>()

    override fun iterator(): MutableIterator<Criterion> {
        return criteria.iterator()
    }

    fun add(criterion: Criterion) {
        criteria.add(criterion)
    }

    fun size(): Int {
        return criteria.size
    }

}
