package chapter13

import java.util.concurrent.Executors
import java.util.function.BiConsumer
import java.util.stream.Collectors


class ProfileMatcher {
   private val profiles: MutableMap<String, Profile> = HashMap()
   fun add(profile: Profile) {
      profiles[profile.getId()] = profile
   }

   val executor = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE)

   fun findMatchingProfiles(
      criteria: Criteria,
      listener: MatchListener,
      matchSets: MutableList<MatchSet>,
      processFunction: BiConsumer<MatchListener, MatchSet>
   ) {
      for (set in matchSets) {
         val runnable = Runnable { processFunction.accept(listener, set) }
         executor.execute(runnable)
      }
      executor.shutdown()
   }

   fun findMatchingProfiles(criteria: Criteria, listener: MatchListener) {
      findMatchingProfiles(
         criteria, listener, collectMatchSets(criteria), ::process
      )
   }

   // 비동기로 실행할 코드
   fun process(listener: MatchListener, set: MatchSet) {
      if (set.matches()) listener.foundMatch(profiles[set.profileId], set)
   }

   fun collectMatchSets(criteria: Criteria?): MutableList<MatchSet> = profiles.values.stream()
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