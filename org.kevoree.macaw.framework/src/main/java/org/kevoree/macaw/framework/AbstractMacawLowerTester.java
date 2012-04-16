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
public abstract class AbstractMacawLowerTester extends AbstractComponentType implements MacawMessageTypes {

	protected abstract void executeRequest (StdKevoreeMessage message);

	protected AdapterWrapper adapter;

	@Start
	public void start () throws Throwable {
		Class clazz = getClass().getClassLoader().loadClass(this.getDictionary().get("AdapterClass").toString());
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
