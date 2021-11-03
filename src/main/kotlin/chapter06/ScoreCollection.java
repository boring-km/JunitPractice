package chapter06;

import java.util.*;

public class ScoreCollection {
   private List<Scoreable> scores = new ArrayList<>();
   
   public void add(Scoreable scoreable) {
      if (scoreable == null) throw new IllegalArgumentException();   // 입력 범위를 분명하게 하기
      scores.add(scoreable);
   }
   
   public int arithmeticMean() {
      if (scores.size() == 0) return 0; // ArithmeticException 발생 가능성
      int total = scores.stream().mapToInt(Scoreable::getScore).sum();
      return total / scores.size();
   }
}


