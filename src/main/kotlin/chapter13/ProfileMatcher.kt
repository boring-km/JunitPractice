package chapter13

import java.util.concurrent.Executors
import java.util.stream.Collectors


class ProfileMatcher {
   private val profiles: MutableMap<String, Profile> = HashMap()
   fun add(profile: Profile) {
      profiles[profile.getId()] = profile
   }

   fun findMatchingProfiles(
      criteria: Criteria?, listener: MatchListener
   ) {
      val executor = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE)
      for (set in collectMatchSets(criteria)) {
         val runnable =
            Runnable { if (set.matches()) listener.foundMatch(profiles[set.profileId], set) }
         executor.execute(runnable)
      }
      executor.shutdown()
   }

   fun collectMatchSets(criteria: Criteria?) = profiles.values.stream()
      .map { profile: Profile ->
         profile.getMatchSet(
            criteria
         )
      }
      .collect(Collectors.toList())

   companion object {
      private const val DEFAULT_POOL_SIZE = 4
   }
}