package socialNetworkLite.textio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

import socialNetworkLite.TextInput;

@Component
public class StdInTextInput implements TextInput {

	private final BufferedReader reader;

	public StdInTextInput() {
		this.reader = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public String readLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
