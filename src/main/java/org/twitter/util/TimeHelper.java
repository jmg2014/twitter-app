package org.twitter.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Jorge Manrique
 * @version 1.0
 */
public class TimeHelper {

  /**
   * This method return the time between two LocalDateTime if the difference is hours, it will
   * return only the difference in hours excluding minutes and seconds if the difference is minutes,
   * it will return only the difference in minutes excluding seconds if the difference is seconds,
   * it will return the difference in seconds.
   *
   * @param from initial time
   * @param to final time
   * @return time between initial and final time
   */
  public static String difference(LocalDateTime from, LocalDateTime to) {

    StringBuffer sb = new StringBuffer().append(" (");

    LocalDateTime tempDateTime = LocalDateTime.from(from);
    long hours = tempDateTime.until(to, ChronoUnit.HOURS);
    tempDateTime = tempDateTime.plusHours(hours);

    long minutes = tempDateTime.until(to, ChronoUnit.MINUTES);
    tempDateTime = tempDateTime.plusMinutes(minutes);

    long seconds = tempDateTime.until(to, ChronoUnit.SECONDS);

    if (hours > 1) {
      return sb.append(hours).append(" hours ago)").toString();
    } else if (hours == 1) {
      return sb.append(hours).append(" hour ago)").toString();
    } else if ((hours == 0) && (minutes > 1)) {
      return sb.append(minutes).append(" minutes ago)").toString();
    } else if ((hours == 0) && (minutes == 1)) {
      return sb.append(minutes).append(" minute ago)").toString();
    } else if (seconds == 1) {
      return sb.append(seconds).append(" second ago)").toString();
    } else {
      return sb.append(seconds).append(" seconds ago)").toString();
    }

  }
}
