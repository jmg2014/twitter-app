package twitter.util;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import twitter.util.TimeHelper;

public class TimerHelperTest {

	@Test
	public void test() {

		// 0 second
		LocalDateTime from = LocalDateTime.of(2016, 06, 12, 15, 45, 55);
		LocalDateTime to = LocalDateTime.of(2016, 06, 12, 15, 45, 55);
		assertTrue(" (0 seconds ago)".equals(TimeHelper.difference(from, to)));
		

		// 1 seconds
		from = LocalDateTime.of(2016, 06, 12, 15, 45, 55);
		to = LocalDateTime.of(2016, 06, 12, 15, 45, 56);
		assertTrue(" (1 second ago)".equals(TimeHelper.difference(from, to)));
		

		// 5 seconds
		from = LocalDateTime.of(2016, 06, 12, 15, 45, 50);
		to = LocalDateTime.of(2016, 06, 12, 15, 45, 55);
		assertTrue(" (5 seconds ago)".equals(TimeHelper.difference(from, to)));
		

		// 1 minute
		from = LocalDateTime.of(2016, 06, 12, 15, 45, 50);
		to = LocalDateTime.of(2016, 06, 12, 15, 46, 50);
		assertTrue(" (1 minute ago)".equals(TimeHelper.difference(from, to)));
		

		// 5 minutes
		from = LocalDateTime.of(2016, 06, 12, 15, 45, 50);
		to = LocalDateTime.of(2016, 06, 12, 15, 50, 50);
		assertTrue(" (5 minutes ago)".equals(TimeHelper.difference(from, to)));
		

		// 1 hour
		from = LocalDateTime.of(2016, 06, 12, 15, 45, 50);
		to = LocalDateTime.of(2016, 06, 12, 16, 45, 50);
		assertTrue(" (1 hour ago)".equals(TimeHelper.difference(from, to)));
		

		// 5 hours
		from = LocalDateTime.of(2016, 06, 12, 15, 45, 50);
		to = LocalDateTime.of(2016, 06, 12, 20, 45, 50);
		assertTrue(" (5 hours ago)".equals(TimeHelper.difference(from, to)));
		

	}

}
