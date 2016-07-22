package org.twitter.util
import static org.junit.Assert.assertTrue
import spock.lang.Specification

import java.time.LocalDateTime

class TimerHelperSpec extends Specification {


  def "difference of the same date is 0 seconds"() {

    given: "the same dates"
    def  from = LocalDateTime.of(2016, 06, 12, 15, 45, 55);
    def  to = LocalDateTime.of(2016, 06, 12, 15, 45, 55);

    when: "Calculate the difference between the dates"
    def difference=TimeHelper.difference(from, to);

    then: "The difference should be (0 seconds ago)"
    " (0 seconds ago)".equals(difference);
  }
}
