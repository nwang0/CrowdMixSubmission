package socialNetworkLite.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import socialNetworkLite.TextInput;
import socialNetworkLite.TextOutput;
import socialNetworkLite.UserCommand;
import socialNetworkLite.command.UserCommandFactory;

@Component
public class InteractiveConsoleSocialNetwork {

	private TextOutput output;
	private TextInput input;
	private UserCommandFactory commandFactory;

	public void run() {
		while(true) {
			output.print("> ");
			String line = input.readLine();
			UserCommand command = commandFactory.create(line);
			command.execute();
		}
	}

	@Autowired
	public void setOutput(TextOutput output) {
		this.output = output;
	}

	@Autowired
	public void setInput(TextInput input) {
		this.input = input;
	}

	@Autowired
	public void setCommandFactory(UserCommandFactory commandFactory) {
		this.commandFactory = commandFactory;
	}
}
