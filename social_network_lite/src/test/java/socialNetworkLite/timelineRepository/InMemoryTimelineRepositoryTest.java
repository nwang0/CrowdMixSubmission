package socialNetworkLite.timelineRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import socialNetworkLite.Timeline;
import socialNetworkLite.TimelineFactory;

public class InMemoryTimelineRepositoryTest {

	private InMemoryTimelineRepository repository;
	
	@Before
	public void setUp() {
		repository = new InMemoryTimelineRepository();
		TimelineFactory timelineFactory = new TimelineFactory();
		timelineFactory.setTimelineRepository(repository);
		repository.setTimelineFactory(timelineFactory);
	}
	
	@Test
	public void testCreateNew() {
		Timeline newTimeline = repository.getOrCreateBoard("Nan");
		assertNotNull(newTimeline);
	}
	
	@Test
	public void testGetExisting() {
		Timeline newTimeline = repository.getOrCreateBoard("Nan");
		Timeline existing = repository.getOrCreateBoard("Nan");
		assertEquals(newTimeline, existing);
	}
}
