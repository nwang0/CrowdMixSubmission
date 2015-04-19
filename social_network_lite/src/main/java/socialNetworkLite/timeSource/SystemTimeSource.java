package socialNetworkLite.timeSource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import socialNetworkLite.TimeSource;

@Component
public class SystemTimeSource implements TimeSource {

	@Override
	public DateTime getCurrentTime() {
		return new DateTime();
	}

}
