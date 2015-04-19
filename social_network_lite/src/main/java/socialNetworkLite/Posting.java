package socialNetworkLite;

import java.util.Objects;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class Posting {

	private final String owner;
	private final String message;
	private final DateTime timestamp;

	public Posting(String owner, String message, DateTime timestamp) {
		this.owner = owner;
		this.message = message;
		this.timestamp = timestamp;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}

	public String toPrintable(DateTime asOfTime) {
		StringBuffer text = new StringBuffer();
		text.append(owner);
		text.append(" - ");
		text.append(message);
		text.append(" (");
		text.append(elasped(asOfTime));
		text.append(" ago)");
		return text.toString();
	}

	private String elasped(DateTime asOfTime) {
		Period elasped = new Period(timestamp, asOfTime);
		int inDays = elasped.toStandardDays().getDays();
		int inMinutes = elasped.toStandardMinutes().getMinutes();
		int inSeconds = elasped.toStandardSeconds().getSeconds();
		if (inDays == 0) {
			if (inMinutes == 0) {
				return inSeconds + " seconds";
			} else {
				return inMinutes + " minutes";
			}
		} else {
			return inDays + " days";
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Posting) {
			Posting another = (Posting) obj;
			return this.message.equals(another.message)
					&& this.owner.equals(another.owner)
					&& this.timestamp.equals(another.timestamp);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(owner, message, timestamp);
	}
	
	@Override
	public String toString() {
		StringBuffer text = new StringBuffer();
		text.append(owner);
		text.append(" - ");
		text.append(message);
		text.append(" (");
		text.append(timestamp);
		text.append(")");
		return text.toString();
	}
}
