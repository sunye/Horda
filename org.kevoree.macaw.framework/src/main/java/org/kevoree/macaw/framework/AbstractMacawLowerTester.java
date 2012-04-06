package org.kevoree.macaw.framework;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;
import org.kevoree.framework.message.StdKevoreeMessage;
import org.macaw.AdapterWrapper;
import org.macaw.messages.MethodResult;

import java.io.Serializable;

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
				@MsgElem(name = "id", className = Integer.class, optional = false), // an id to identify the request
				@MsgElem(name = "tests", className = String.class, optional = false), // an array of the test names the component must execute
				@MsgElem(name = "parameters", className = Serializable[].class, optional = false),
				@MsgElem(name = "timeout", className = Integer.class) // the timeout for the execution

		}),
		@MessageType(name = "response", elems = {
				@MsgElem(name = "id", className = Integer.class, optional = false),
				@MsgElem(name = "test", className = String.class, optional = false),
				@MsgElem(name = "parameters", className = Serializable[].class, optional = false),
				@MsgElem(name = "timeout", className = Integer.class),
				@MsgElem(name = "result", className = MethodResult.class, optional = false) // the JUnit TestResult of the execution // TODO fix class name type
		})
})
@ComponentFragment
@Requires({
		@RequiredPort(name = "responseTest", type = PortType.MESSAGE, messageType = "response")
})
@Provides({
		@ProvidedPort(name = "requestTest", type = PortType.MESSAGE, messageType = "request")
})
@DictionaryType({
		@DictionaryAttribute(name = "AdapterClass", optional = false, dataType = String.class)
})
public abstract class AbstractMacawLowerTester extends AbstractComponentType {

	protected abstract void executeRequest (StdKevoreeMessage message);

	protected AdapterWrapper adapter;

	@Start
	public void start () throws Throwable {
		Class clazz = Class.forName(this.getDictionary().get("AdapterClass").toString());
		adapter = new AdapterWrapper(clazz);
		adapter.setup();
	}

	@Stop
	public void stop() throws Throwable {
		adapter.cleanup();
	}

	@Port(name = "requestTest")
	public void request (Object message) {
		if (message instanceof StdKevoreeMessage) { // TODO add check about content of the Kevoree message
			executeRequest((StdKevoreeMessage) message);
		}
	}

	public void sendResponse (StdKevoreeMessage message) { // TODO add check about content of the Kevoree message
		getPortByName("responseTest", MessagePort.class).process(message);
	}

}
