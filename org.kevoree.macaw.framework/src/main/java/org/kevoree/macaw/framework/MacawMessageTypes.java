package org.kevoree.macaw.framework;

import org.kevoree.annotation.ComponentFragment;
import org.kevoree.annotation.MessageType;
import org.kevoree.annotation.MessageTypes;
import org.kevoree.annotation.MsgElem;
import org.macaw.messages.MethodResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 16/04/12
 * Time: 13:48
 */



@MessageTypes({
        @MessageType(name = "macawQuery", elems = {
                @MsgElem(name = "testcases", className = String[].class, optional = false)

        }),
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
public interface MacawMessageTypes {
}
