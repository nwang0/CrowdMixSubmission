package socialNetworkLite.textio;

import org.springframework.stereotype.Component;

import socialNetworkLite.TextOutput;

@Component
public class StdOutTextOutput implements TextOutput {

	@Override
	public void printLine(String text) {
		System.out.println(text);
	}

	@Override
	public void print(String text) {
		System.out.print(text);
	}

}
