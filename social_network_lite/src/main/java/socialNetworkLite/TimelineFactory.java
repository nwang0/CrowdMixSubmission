package socialNetworkLite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimelineFactory {

	private TimelineRepository timelineRepository;

	public Timeline create(String userName) {
		return new Timeline(timelineRepository, userName);
	}

	@Autowired
	public void setTimelineRepository(TimelineRepository timelineRepository) {
		this.timelineRepository = timelineRepository;
	}

}
