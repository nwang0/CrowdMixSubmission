package socialNetworkLite.command;

import socialNetworkLite.Timeline;
import socialNetworkLite.TimelineRepository;
import socialNetworkLite.UserCommand;

public class FollowCommand implements UserCommand {

	private final TimelineRepository postingBoardRepository;
	private final String userName;
	private final String anotherUserName;
	
	public FollowCommand(TimelineRepository postingBoardRepository,
			String userName, String anotherUserName) {
		this.postingBoardRepository = postingBoardRepository;
		this.userName = userName;
		this.anotherUserName = anotherUserName;
	}

	@Override
	public void execute() {
		Timeline board = postingBoardRepository.getOrCreateBoard(userName);
		board.follow(anotherUserName);
	}

	//Test access only
	String getUserName() {
		return userName;
	}

	//Test access only
	String getAnotherUserName() {
		return anotherUserName;
	}

}
