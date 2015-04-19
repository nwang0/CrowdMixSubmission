package socialNetworkLite.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import socialNetworkLite.UserCommand;
import socialNetworkLite.textio.StubTextOutput;
import socialNetworkLite.timeSource.StubTimeSource;
import socialNetworkLite.timelineRepository.StubTimelineRepository;

public class UserCommandFactoryTest {

	private UserCommandFactory factory;

	@Before
	public void setUp() {
		this.factory = new UserCommandFactory();
		factory.setTimelineRepository(new StubTimelineRepository());
		factory.setOutput(new StubTextOutput());
		factory.setTimeSource(new StubTimeSource());
	}

	@Test
	public void testPost() {
		UserCommand command = factory.create("Nan -> Text message.");
		assertTrue(command instanceof PostCommand);
		PostCommand post = (PostCommand) command;
		assertEquals("Nan", post.getUserName());
		assertEquals("Text message.", post.getMessage());
	}
	
	@Test
	public void testWall() {
		UserCommand command = factory.create("Nan wall");
		assertTrue(command instanceof WallCommand);
		WallCommand wall = (WallCommand) command;
		assertEquals("Nan", wall.getUserName());
	}
	
	@Test
	public void testRead() {
		UserCommand command = factory.create("Nan");
		assertTrue(command instanceof ReadCommand);
		ReadCommand read = (ReadCommand) command;
		assertEquals("Nan", read.getUserName());
	}
	
	@Test
	public void testFollow() {
		UserCommand command = factory.create("Nan follows Wang");
		assertTrue(command instanceof FollowCommand);
		FollowCommand follow = (FollowCommand) command;
		assertEquals("Nan", follow.getUserName());
		assertEquals("Wang", follow.getAnotherUserName());
	}
}
