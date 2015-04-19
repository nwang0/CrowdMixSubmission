package socialNetworkLite.timeSource;

import org.joda.time.DateTime;

import socialNetworkLite.TimeSource;

public class StubTimeSource implements TimeSource {

	private DateTime now;
	
	public void setNow(DateTime now) {
		this.now = now;
	}

	@Override
	public DateTime getCurrentTime() {
		return now;
	}

}
