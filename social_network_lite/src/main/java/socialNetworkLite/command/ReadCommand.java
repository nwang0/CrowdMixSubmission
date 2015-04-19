package socialNetworkLite.command;

import socialNetworkLite.TextOutput;
import socialNetworkLite.TimeSource;
import socialNetworkLite.Timeline;
import socialNetworkLite.TimelineRepository;
import socialNetworkLite.UserCommand;
import socialNetworkLite.postingCollector.PrintToOutput;

public class ReadCommand implements UserCommand {

	private final TimelineRepository postingBoardRepository;
	private final TextOutput output;
	private final TimeSource timeSource;
	private final String userName;

	public ReadCommand(TimelineRepository postingBoardRepository,
			TextOutput output, TimeSource timeSource, String userName) {
		super();
		this.postingBoardRepository = postingBoardRepository;
		this.output = output;
		this.timeSource = timeSource;
		this.userName = userName;
	}

	@Override
	public void execute() {
		Timeline board = postingBoardRepository.getOrCreateBoard(userName);
		PrintToOutput collector = new PrintToOutput(output,
				timeSource);
		board.getPostings(collector);
		collector.printToConsole();
	}

	
	//Test access only
	String getUserName() {
		return userName;
	}

}
