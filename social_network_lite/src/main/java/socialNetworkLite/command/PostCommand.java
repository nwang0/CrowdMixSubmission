package socialNetworkLite.command;

import socialNetworkLite.TimeSource;
import socialNetworkLite.Timeline;
import socialNetworkLite.TimelineRepository;
import socialNetworkLite.UserCommand;

public class PostCommand implements UserCommand {

	private final TimelineRepository timelineRepository;
	private final TimeSource timeSource;

	private final String userName;
	private final String message;

	public PostCommand(TimelineRepository timelineRepository,
			TimeSource timeSource, String userName, String message) {
		this.timelineRepository = timelineRepository;
		this.timeSource = timeSource;
		this.userName = userName;
		this.message = message;
	}

	@Override
	public void execute() {
		Timeline board = timelineRepository.getOrCreateBoard(userName);
		board.post(message, timeSource.getCurrentTime());
	}

	// Test access only
	String getUserName() {
		return userName;
	}

	// Test access only
	String getMessage() {
		return message;
	}

}
