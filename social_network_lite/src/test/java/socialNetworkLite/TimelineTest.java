package socialNetworkLite;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import socialNetworkLite.Posting;
import socialNetworkLite.Timeline;
import socialNetworkLite.TimelineFactory;
import socialNetworkLite.TimelineRepository;
import socialNetworkLite.timelineRepository.InMemoryTimelineRepository;

public class TimelineTest {

	private TimelineRepository timelineRepository;

	@Before
	public void setUp() {
		this.timelineRepository = getRepository();
	}

	@Test
	public void testGetSinglePosting() {
		Timeline timeline = timelineRepository.getOrCreateBoard("Alice");
		DateTime timestamp = new DateTime();
		timeline.post("I love the weather today", timestamp);

		List<Posting> collected = collectPostings(timeline);

		assertEquals(1, collected.size());
		assertEquals(
				new Posting("Alice", "I love the weather today", timestamp),
				collected.get(0));
	}

	@Test
	public void testGetMultiplePostInOrder() {
		Timeline timeline = timelineRepository.getOrCreateBoard("Bob");
		DateTime timestamp1 = new DateTime();
		timeline.post("Damn! We lost!", timestamp1);
		DateTime timestamp2 = timestamp1.plusMinutes(1);
		timeline.post("Good game though.", timestamp2);

		List<Posting> collected = collectPostings(timeline);

		assertEquals(2, collected.size());
		assertEquals(new Posting("Bob", "Good game though.", timestamp2),
				collected.get(0));
		assertEquals(new Posting("Bob", "Damn! We lost!", timestamp1),
				collected.get(1));
	}

	@Test
	public void testAggregatePostings() {
		Timeline timelineAlice = timelineRepository.getOrCreateBoard("Alice");
		Timeline timelineBob = timelineRepository.getOrCreateBoard("Bob");
		Timeline timelineCharlie = timelineRepository
				.getOrCreateBoard("Charlie");

		DateTime timestamp1 = new DateTime();
		timelineAlice.post("I love the weather today", timestamp1);
		DateTime timestamp2 = new DateTime();
		timelineBob.post("Damn! We lost!", timestamp2);
		DateTime timestamp3 = timestamp2.plusMinutes(1);
		timelineBob.post("Good game though.", timestamp3);

		DateTime timestamp4 = timestamp3.plusMinutes(1);
		timelineCharlie.post(
				"I'm in New York today! Anyone want to have a coffee?",
				timestamp4);

		
		timelineCharlie.follow("Alice");

		List<Posting> collected = collectWall(timelineCharlie);

		assertEquals(2, collected.size());
		assertEquals(new Posting("Charlie",
				"I'm in New York today! Anyone want to have a coffee?",
				timestamp4), collected.get(0));
		assertEquals(new Posting("Alice", "I love the weather today",
				timestamp1), collected.get(1));

		
		timelineCharlie.follow("Bob");

		collected = collectWall(timelineCharlie);
		assertEquals(4, collected.size());
		
		assertEquals(new Posting("Charlie",
				"I'm in New York today! Anyone want to have a coffee?",
				timestamp4), collected.get(0));

		assertEquals(new Posting("Bob", "Good game though.", timestamp3),
				collected.get(1));
		assertEquals(new Posting("Bob", "Damn! We lost!", timestamp2),
				collected.get(2));
		assertEquals(new Posting("Alice", "I love the weather today",
				timestamp1), collected.get(3));
	}

	private List<Posting> collectPostings(Timeline timeline) {
		StubPostingCollector collector = new StubPostingCollector();
		timeline.getPostings(collector);
		List<Posting> collected = collector.getCollected();
		return collected;
	}

	private List<Posting> collectWall(Timeline timeline) {
		StubPostingCollector collector = new StubPostingCollector();
		timeline.getWall(collector);
		List<Posting> collected = collector.getCollected();
		return collected;
	}

	private TimelineRepository getRepository() {
		InMemoryTimelineRepository inMemoryTimelineRepository = new InMemoryTimelineRepository();
		TimelineFactory timelineFactory = new TimelineFactory();
		timelineFactory.setTimelineRepository(inMemoryTimelineRepository);
		inMemoryTimelineRepository.setTimelineFactory(timelineFactory);
		return inMemoryTimelineRepository;
	}
}
