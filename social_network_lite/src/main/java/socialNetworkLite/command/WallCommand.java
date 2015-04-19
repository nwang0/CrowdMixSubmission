package socialNetworkLite.command;

import socialNetworkLite.TextOutput;
import socialNetworkLite.TimeSource;
import socialNetworkLite.Timeline;
import socialNetworkLite.TimelineRepository;
import socialNetworkLite.UserCommand;
import socialNetworkLite.postingCollector.PrintToOutput;

public class WallCommand implements UserCommand {

	private final TimelineRepository postingBoardRepository;

	private final TimeSource timeSource;
	private final TextOutput output;
	private final String userName;

	public WallCommand(TimelineRepository postingBoardRepository,
			TimeSource timeSource, TextOutput console, String userName) {
		this.postingBoardRepository = postingBoardRepository;
		this.timeSource = timeSource;
		this.output = console;
		this.userName = userName;
	}

	@Override
	public void execute() {
		Timeline board = postingBoardRepository.getOrCreateBoard(userName);
		PrintToOutput collector = new PrintToOutput(output, timeSource);
		board.getWall(collector);
		collector.printToConsole();
	}

	//Test access only
	String getUserName() {
		return userName;
	}

}
