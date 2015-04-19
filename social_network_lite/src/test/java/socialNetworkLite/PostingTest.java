package socialNetworkLite;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import socialNetworkLite.Posting;

public class PostingTest {

	private final DateTime timestamp = new DateTime(2015, 04, 19, 10, 00, 00);

	@Test
	public void testTimestamp() {
		Posting posting = createPosting();
		assertEquals(timestamp, posting.getTimestamp());

	}

	@Test
	public void testPrintWithSeconds() {
		Posting posting = createPosting();
		DateTime fifteenSecondsLater = timestamp.plusSeconds(15);
		assertEquals("Nan - Test message. (15 seconds ago)",
				posting.toPrintable(fifteenSecondsLater));
	}
	
	@Test
	public void testPrintWithMinutes() {
		Posting posting = createPosting();
		DateTime fifteenSecondsLater = timestamp.plusMinutes(10).plusSeconds(15);
		assertEquals("Nan - Test message. (10 minutes ago)",
				posting.toPrintable(fifteenSecondsLater));
	}

	private Posting createPosting() {
		return new Posting("Nan", "Test message.", timestamp);
	}
}
