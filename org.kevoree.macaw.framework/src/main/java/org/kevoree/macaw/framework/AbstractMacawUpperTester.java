package org.kevoree.macaw.framework;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;
import org.kevoree.framework.message.StdKevoreeMessage;
import org.macaw.messages.MethodResult;

import java.io.Serializable;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 06/04/12
 * Time: 10:52
 *
 * @author Erwan Daubert
 * @version 1.0
 */

@ComponentFragment
@Requires({
		@RequiredPort(name = "response", type = PortType.MESSAGE, messageType = "response", optional = false),
		@RequiredPort(name = "requestTest", type = PortType.MESSAGE, messageType = "request")
})
@Provides({
		@ProvidedPort(name = "request", type = PortType.MESSAGE, messageType = "request"),
		@ProvidedPort(name = "responseTest", type = PortType.MESSAGE, messageType = "response")
})
public abstract class AbstractMacawUpperTester extends AbstractComponentType implements MacawMessageTypes {

	protected abstract void initRequest (StdKevoreeMessage message);

	protected abstract void initResponse (StdKevoreeMessage message);

	@Port(name = "request")
	public void request (Object message) {
		if (message instanceof StdKevoreeMessage) {
			StdKevoreeMessage msg = (StdKevoreeMessage) message;
			initRequest(msg);
			getPortByName("request", MessagePort.class).process(msg);
		}
	}

	@Port(name = "responseTest")
	public void response (Object message) {
		if (message instanceof StdKevoreeMessage) {
			StdKevoreeMessage msg = (StdKevoreeMessage) message;
			initResponse(msg);
			getPortByName("response", MessagePort.class).process(msg);

		}
	}
}
