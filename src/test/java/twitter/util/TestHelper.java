package twitter.util;

/**
 * Helper to simulate delays between commands
 * @author Jorge Manrique
 * @version 1.0
 */
public class TestHelper {

	
	public static void delayBetweenCommand() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
