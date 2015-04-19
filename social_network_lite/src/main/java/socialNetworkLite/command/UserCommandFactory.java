package socialNetworkLite.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import socialNetworkLite.TextOutput;
import socialNetworkLite.TimeSource;
import socialNetworkLite.TimelineRepository;
import socialNetworkLite.UserCommand;

@Component
public class UserCommandFactory {

	private TextOutput output;
	private TimeSource timeSource;
	private TimelineRepository timelineRepository;

	public UserCommand create(String line) {
		if (line.contains("wall")) {
			String userName = line.split(" ")[0];
			return new WallCommand(timelineRepository, timeSource, output,
					userName);
		} else if (line.contains("->")) {
			String[] splits = line.split(" -> ");
			String userName = splits[0];
			String message = splits[1];
			return new PostCommand(timelineRepository, timeSource,
					userName, message);
		} else if (line.contains("follows")) {
			String[] splits = line.split(" follows ");
			String userName = splits[0];
			String anotherUserName = splits[1];
			return new FollowCommand(timelineRepository, userName,
					anotherUserName);
		} else {
			String userName = line;
			return new ReadCommand(timelineRepository, output, timeSource,
					userName);
		}
	}

	@Autowired
	public void setOutput(TextOutput output) {
		this.output = output;
	}

	@Autowired
	public void setTimeSource(TimeSource timeSource) {
		this.timeSource = timeSource;
	}

	@Autowired
	public void setTimelineRepository(
			TimelineRepository timelineRepository) {
		this.timelineRepository = timelineRepository;
	}
}
