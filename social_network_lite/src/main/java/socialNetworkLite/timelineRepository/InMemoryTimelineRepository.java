package socialNetworkLite.timelineRepository;

import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import socialNetworkLite.Timeline;
import socialNetworkLite.TimelineFactory;
import socialNetworkLite.TimelineRepository;

import com.google.common.collect.Maps;

@Component
public class InMemoryTimelineRepository implements TimelineRepository {

	private final ConcurrentMap<String, Timeline> timelines = Maps
			.newConcurrentMap();

	private TimelineFactory timelineFactory;

	@Override
	public Timeline getOrCreateBoard(String userName) {
		Timeline newTimeline = timelineFactory.create(userName);
		Timeline existingTimeline = timelines
				.putIfAbsent(userName, newTimeline);
		if (existingTimeline != null) {
			return existingTimeline;
		} else {
			return newTimeline;
		}
	}

	@Autowired
	public void setTimelineFactory(TimelineFactory timelineFactory) {
		this.timelineFactory = timelineFactory;
	}

}
