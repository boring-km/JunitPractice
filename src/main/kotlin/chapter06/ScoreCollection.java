package chapter06;

import java.util.*;

public class ScoreCollection {
   private List<Scoreable> scores = new ArrayList<>();
   
   public void add(Scoreable scoreable) {
      scores.add(scoreable);
   }
   
   public int arithmeticMean() {
      int total = scores.stream().mapToInt(Scoreable::getScore).sum();
      return total / scores.size();
   }
}


@FunctionalInterface
interface Scoreable {
   int getScore();
}