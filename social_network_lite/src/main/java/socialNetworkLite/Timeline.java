package socialNetworkLite;

import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Timeline {

	private final TimelineRepository timelineRepository;

	private final Set<Timeline> followingUsers;
	private final Deque<Posting> postings;
	private final String owner;

	public Timeline(TimelineRepository timelineRepository, String owner) {
		this.timelineRepository = timelineRepository;
		this.owner = owner;
		this.followingUsers = Sets.newConcurrentHashSet();
		this.postings = new ConcurrentLinkedDeque<>();
	}

	public void follow(String anotherUserName) {
		Timeline user = timelineRepository.getOrCreateBoard(anotherUserName);
		this.followingUsers.add(user);
	}

	public void post(String message, DateTime now) {
		Posting newPosting = new Posting(owner, message, now);
		this.postings.addFirst(newPosting);
	}

	public void getWall(PostingCollector collector) {
		OrderedCollector orderedCollector = new OrderedCollector();

		for (Timeline each : followingUsers) {
			each.getPostings(orderedCollector);
		}
		getPostings(orderedCollector);

		orderedCollector.pushTo(collector);
	}

	public void getPostings(PostingCollector collector) {
		for (Posting each : postings) {
			collector.collect(each);
		}
	}

	private final class OrderedCollector implements PostingCollector {

		private final List<Posting> collected = Lists.newArrayList();

		@Override
		public void collect(Posting posting) {
			this.collected.add(posting);
		}

		public void pushTo(PostingCollector collector) {
			Collections.sort(collected, new OrderByTimestampReverse());
			for (Posting each : collected) {
				collector.collect(each);
			}
		}
	}

	private final class OrderByTimestampReverse implements Comparator<Posting> {

		@Override
		public int compare(Posting o1, Posting o2) {
			return o2.getTimestamp().compareTo(o1.getTimestamp());
		}
	}

}
