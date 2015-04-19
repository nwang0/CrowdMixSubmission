package socialNetworkLite;

import java.util.List;

import socialNetworkLite.Posting;
import socialNetworkLite.PostingCollector;

import com.google.common.collect.Lists;

public class StubPostingCollector implements PostingCollector {

	private final List<Posting> collected = Lists.newArrayList();

	@Override
	public void collect(Posting posting) {
		collected.add(posting);
	}

	public List<Posting> getCollected() {
		return collected;
	}
}