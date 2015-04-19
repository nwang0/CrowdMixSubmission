package socialNetworkLite;

public interface TimelineRepository {

	public Timeline getOrCreateBoard(String userName);

}
