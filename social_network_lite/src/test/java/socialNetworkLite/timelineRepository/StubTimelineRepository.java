package socialNetworkLite.timelineRepository;

import socialNetworkLite.Timeline;
import socialNetworkLite.TimelineRepository;

public class StubTimelineRepository
		implements
			TimelineRepository {
	@Override
	public Timeline getOrCreateBoard(String userName) {
		return null;
	}
}