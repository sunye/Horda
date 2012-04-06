package org.kevoree.macaw.framework;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;
import org.kevoree.framework.message.StdKevoreeMessage;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 06/04/12
 * Time: 11:16
 *
 * @author Erwan Daubert
 * @version 1.0
 */
@MessageTypes({
		@MessageType(name = "request", elems = {
				@MsgElem(name = "id", className = String.class, optional = false), // an id to identify the request
				@MsgElem(name = "tests", className = String[].class, optional = false), // an array of the test names the component must execute
				@MsgElem(name = "timeout", className = Integer.class) // the timeout for the execution
		}),
		@MessageType(name = "response", elems = {
				@MsgElem(name = "id", className = String.class, optional = false),
				@MsgElem(name = "tests", className = String[].class, optional = false),
				@MsgElem(name = "timeout", className = Integer.class),
				@MsgElem(name = "result", className = Void.class, optional = false) // the JUnit TestResult of the execution // TODO fix class name type
		})
})
@ComponentFragment
@Requires({
		@RequiredPort(name = "responseTest", type = PortType.MESSAGE, messageType = "response")
})
@Provides({
		@ProvidedPort(name = "requestTest", type = PortType.MESSAGE, messageType = "request")
})
public abstract class AbstractMacawLowerTester extends AbstractComponentType {

	abstract void executeRequest (StdKevoreeMessage message);

	@Port(name = "requestTest")
	public void request (Object message) {
		if (message instanceof StdKevoreeMessage) {
			executeRequest((StdKevoreeMessage) message);
		}
	}

	public void sendResponse (StdKevoreeMessage message) {
		getPortByName("responseTest", MessagePort.class).process(message);
	}

}
