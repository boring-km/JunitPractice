package chapter13

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*
import java.util.stream.Collectors


class ProfileMatcherTest {
    private lateinit var question: BooleanQuestion
    private lateinit var criteria: Criteria
    private lateinit var matcher: ProfileMatcher
    private lateinit var matchingProfile: Profile
    private lateinit var nonMatchingProfile: Profile
    
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
}