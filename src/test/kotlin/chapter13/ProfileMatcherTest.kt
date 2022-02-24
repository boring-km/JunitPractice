package chapter13

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.*
import java.util.function.BiConsumer
import java.util.stream.Collectors


class ProfileMatcherTest {
    private lateinit var question: BooleanQuestion
    private lateinit var criteria: Criteria
    private lateinit var matcher: ProfileMatcher
    private lateinit var matchingProfile: Profile
    private lateinit var nonMatchingProfile: Profile
    private lateinit var listener: MatchListener
    
    @Before
    fun create() {
        question = BooleanQuestion(1, "")
        criteria = Criteria()
        criteria.add(Criterion(matchingAnswer(), Weight.MustMatch))
        matchingProfile = createMatchingProfile("matching")
        nonMatchingProfile = createNonMatchingProfile("nonMatching")
    }

    private fun createMatchingProfile(name: String): Profile {
        val profile = Profile(name)
        profile.add(matchingAnswer())
        return profile
    }

    private fun createNonMatchingProfile(name: String): Profile {
        val profile = Profile(name)
        profile.add(nonMatchingAnswer())
        return profile
    }

    @Before
    fun createMatcher() {
        matcher = ProfileMatcher()
    }

    @Before
    fun createMatchListener() {
        listener = mock(MatchListener::class.java)
    }

    @Test
    fun processNotifiesListenerOnMatch() {
        matcher.add(matchingProfile)
        val set = matchingProfile.getMatchSet(criteria)

        matcher.process(listener, set)

        verify(listener).foundMatch(matchingProfile, set)
    }

    @Test
    fun collectsMatchSets() {
        matcher.add(matchingProfile)
        matcher.add(nonMatchingProfile)

        val sets = matcher.collectMatchSets(criteria)

        assertThat(
            sets.stream()
                .map { set: MatchSet -> set.profileId }.collect(Collectors.toSet()),
            CoreMatchers.equalTo(
                HashSet(
                    listOf(
                        matchingProfile.getId(), nonMatchingProfile.getId()
                    )
                )
            )
        )
    }

    private fun matchingAnswer(): Answer {
        return Answer(question, Bool.TRUE)
    }

    private fun nonMatchingAnswer(): Answer {
        return Answer(question, Bool.FALSE)
    }

    @Test
    fun gathersMatchingProfiles() {
        // 리스너가 수신하는 MatchSet 객체들의 프로파일 ID 목록을 저장할 문자열 Set 객체를 생성한다.
        val processedSets = Collections.synchronizedSet(HashSet<String>())

        // process() 메서드의 프로덕션 버전을 대신하는 함수
        val processFunction = BiConsumer { _: MatchListener, set: MatchSet ->
            // 각 콜백에서 MatchSet 객체의 프로파일 ID를 processedSets 변수에 추가
            processedSets.add(set.profileId)
        }

        // 테스트용 MatchSet 객체 생성
        val matchSets = createMatchSets(100)

        // processFunction() 구현을 넘긴다.
        // 실제로는 criteria로 테스트를 해야하지만, 내부의 로직은 matchSets의 내용을 그대로 MutableList<MatchSet>에 담아서 리턴한다.
        matcher.findMatchingProfiles(criteria, listener, matchSets, processFunction)

        // ExecutorService 객체를 가져와 모든 스레드의 실행이 완료될 때까지 기다림
        while (!matcher.executor.isTerminated);

        // processedSets 컬렉션이 테스트에서 생성된 모든 MatchSet 객체의 ID와 매칭되는지 검증한다.
        assertThat(processedSets, equalTo(matchSets.stream().map(MatchSet::profileId).collect(Collectors.toSet())))
    }

    private fun createMatchSets(count: Int): MutableList<MatchSet> {
        val sets = arrayListOf<MatchSet>()
        for (i in 0 until count) {
            sets.add(MatchSet(i.toString(), null, null))
        }
        return sets
    }
}