package socialNetworkLite.postingCollector;

import java.util.List;

import socialNetworkLite.Posting;
import socialNetworkLite.PostingCollector;
import socialNetworkLite.TextOutput;
import socialNetworkLite.TimeSource;

import com.google.common.collect.Lists;

public class PrintToOutput implements PostingCollector {

	private final TextOutput console;

	private final TimeSource timeSource;

	private final List<Posting> collected;

	public PrintToOutput(TextOutput console, TimeSource timeSource) {
		this.console = console;
		this.timeSource = timeSource;
		this.collected = Lists.newArrayList();
	}

	@Override
	public void collect(Posting posting) {
		collected.add(posting);
	}

	public void printToConsole() {
		for (Posting each : collected) {
			String line = each.toPrintable(timeSource.getCurrentTime());
			console.printLine(line);
		}
	}

}
